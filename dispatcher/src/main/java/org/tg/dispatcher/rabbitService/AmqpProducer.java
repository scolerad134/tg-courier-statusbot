package org.tg.dispatcher.rabbitService;

import org.tg.dispatcher.dto.MessageDto;

public interface AmqpProducer {
    void sendMessage(MessageDto message);
}
