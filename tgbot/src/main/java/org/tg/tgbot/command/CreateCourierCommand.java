package org.tg.tgbot.command;


import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;
import org.tg.tgbot.model.Courier;
import org.tg.tgbot.rabbitService.AmqpProducer;
import org.tg.tgbot.repository.MessageBuffer;
import org.tg.tgbot.service.CourierService;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@AdminCommand
@Component
public class CreateCourierCommand implements Command {

    CourierService courierService;
    AmqpProducer amqpProducer;
    MessageBuffer messageBuffer;

    @Override
    public void execute() {
        Courier courier = new Courier();
        amqpProducer.sendTextMessage("Введите ник курьера в Telegram");
        System.out.println("введите ник" + " " + Thread.currentThread().getName());
            courier.setNickName(messageBuffer.getMessage());
        amqpProducer.sendTextMessage("Введите имя курьера");
        System.out.println("введите имя" + " " + Thread.currentThread().getName());
            courier.setName(messageBuffer.getMessage());
        amqpProducer.sendTextMessage("Курьер создан!");
        System.out.println("количество потоков" + " " + Thread.activeCount());

        courierService.save(courier);
    }
}
