package org.tg.dispatcher.rabbitService;


public interface AmqpConsumer {
    void getMessage(String content);
}
