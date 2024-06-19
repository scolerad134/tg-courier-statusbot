package org.tg.tgbot.rabbitService;

import org.springframework.messaging.handler.annotation.Payload;
import org.tg.tgbot.dto.MessageDto;

public interface AmqpConsumer {
    void receive(MessageDto message);
}
