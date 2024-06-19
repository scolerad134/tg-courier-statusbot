package org.tg.tgbot.rabbitService;


public interface AmqpProducer {
    void sendTextMessage(String message);
}
