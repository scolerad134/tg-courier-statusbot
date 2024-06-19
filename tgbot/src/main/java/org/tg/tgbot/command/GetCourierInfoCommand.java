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
public class GetCourierInfoCommand implements Command {

    AmqpProducer amqpProducer;
    MessageBuffer messageBuffer;
    CourierService courierService;


    @Override
    public void execute() {
        amqpProducer.sendTextMessage("Введите никнейм курьера в Телеграм");
        Courier courier = courierService.getCourier(messageBuffer.getMessage());
        if (courier != null) {
            amqpProducer.sendTextMessage(courier.toString());
        } else {
            amqpProducer.sendTextMessage("Курьер не найден!");
        }
    }
}
