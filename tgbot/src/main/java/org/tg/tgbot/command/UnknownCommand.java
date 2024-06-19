package org.tg.tgbot.command;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;
import org.tg.tgbot.rabbitService.AmqpProducer;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Component
public class UnknownCommand implements Command {

    AmqpProducer rabbitMQProducer;
    static String UNKNOWN_MESSAGE = "Unknown command";

    @Override
    public void execute() {
        rabbitMQProducer.sendTextMessage(UNKNOWN_MESSAGE);
    }
}
