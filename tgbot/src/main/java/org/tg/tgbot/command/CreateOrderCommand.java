package org.tg.tgbot.command;


import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;
import org.tg.tgbot.model.*;
import org.tg.tgbot.rabbitService.AmqpProducer;
import org.tg.tgbot.repository.MessageBuffer;
import org.tg.tgbot.service.CourierService;
import org.tg.tgbot.service.OrderService;
import org.tg.tgbot.service.UserService;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@AdminCommand
@Component
public class CreateOrderCommand implements Command {

    AmqpProducer amqpProducer;
    MessageBuffer messageBuffer;
    CourierService courierService;
    UserService userService;
    OrderService orderService;

    @Override
    public void execute() {
        Order order = new Order();
        amqpProducer.sendTextMessage("Введите название товара");
            order.setName(messageBuffer.getMessage());
        amqpProducer.sendTextMessage("Введите категорию товара");
            order.setType(validTypeOrder());
        amqpProducer.sendTextMessage("Введите статус заказа");
            order.setStatus(validStatusOrder());
        amqpProducer.sendTextMessage("Введите стоимость заказа");
            order.setPrice(validPriceOrder());
        amqpProducer.sendTextMessage("Введите количество/массу единиц товара");
            order.setQuantity(validQuantityOrder());
        amqpProducer.sendTextMessage("Введите ник курьера в Telegram или поставте прочерк: \"-\"");
            order.setCourier(validCourier());
        amqpProducer.sendTextMessage("Ведите ник клиента");
            order.setUser(validUser());
        try {
            orderService.save(order);
            amqpProducer.sendTextMessage("Заказ создан!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            amqpProducer.sendTextMessage("Заказ не создан!");
        }


    }

    private String validStatusOrder() {
        while(true) {

           String message =  messageBuffer.getMessage();
           for(OrderStatus status : OrderStatus.values()) {
               if(message.equals(status.getStatus())) {
                   return message;
               }
           }
           amqpProducer.sendTextMessage("Введите корректный статус!");
        }
    }

    private String validTypeOrder() {
        while(true) {

            String message =  messageBuffer.getMessage();
            for(OrderType type : OrderType.values()) {
                if(message.equals(type.getType())) {
                    return message;
                }
            }
            amqpProducer.sendTextMessage("Введите корректное название категории!");
        }
    }

    private double validPriceOrder() {
        while(true) {
            try {
                return Double.parseDouble(messageBuffer.getMessage());
            } catch (NumberFormatException e) {
                amqpProducer.sendTextMessage("Введите корректное значение стоимсоти!");
            }
        }
    }

    private double validQuantityOrder() {
        while(true) {
            try {
                return Double.parseDouble(messageBuffer.getMessage());
            } catch (NumberFormatException e) {
                amqpProducer.sendTextMessage("Введите корректное значение количества!");
            }
        }
    }

    private Courier validCourier() {
        while(true) {

            String name =  messageBuffer.getMessage();
            Courier courier = courierService.getCourier(name);

            if (name.equals("-")) {
                System.out.println("valid courier");
                return null;
            } else if(courier == null) {
                amqpProducer.sendTextMessage("Курьер с таким ником не существует!" + "\n" + "Попробуйте еще раз");
            }  else {
                return courier;
            }
        }
    }

    private User validUser() {

        String name = messageBuffer.getMessage();
        User user = userService.getUserByNick(name);
        if(user == null) {
            amqpProducer.sendTextMessage("Создался новый клиент с ником, но без имени");
            userService.save(User.builder().nickName(name).build());
            return userService.getUserByNick(name);
        } else {
            return user;
        }
    }


}
