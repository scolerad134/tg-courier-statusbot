package org.tg.tgbot.rabbitService;


import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.tg.tgbot.config.RabbitConfig.EXCHANGER;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@EnableRabbit
@Service
public class RabbitMQProducer implements AmqpProducer {

    RabbitTemplate rabbitTemplate;

    final static String SEND_MESSAGE_TO_CLIENT = "textMessage.routing.key";

    public void sendTextMessage(String message) {
        rabbitTemplate.convertAndSend(EXCHANGER,SEND_MESSAGE_TO_CLIENT, message);
    }


}
