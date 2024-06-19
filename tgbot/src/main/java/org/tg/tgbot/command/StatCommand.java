package org.tg.tgbot.command;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.tg.tgbot.rabbitService.AmqpProducer;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@AdminCommand
public class StatCommand implements Command {

    AmqpProducer messageService;

    static String ADMIN_COMMAND = "!admin";

    @Override
    public void execute() {
        messageService.sendTextMessage(ADMIN_COMMAND);
    }
}
