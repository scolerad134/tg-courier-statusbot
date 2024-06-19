package org.tg.tgbot.command;


import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;
import org.tg.tgbot.model.Courier;
import org.tg.tgbot.rabbitService.AmqpProducer;
import org.tg.tgbot.service.CourierService;

import java.util.stream.Collectors;


@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@AdminCommand
@Component
public class GetCouriersCommand implements Command {

    CourierService courierService;
    AmqpProducer rabbitMQProducer;


    @Override
    public void execute() {
        rabbitMQProducer.sendTextMessage("Список курьеров:\n"
                + courierService.getCouriers().stream().map(Courier::toString).collect(Collectors.joining("\n"))
        );
    }
}
