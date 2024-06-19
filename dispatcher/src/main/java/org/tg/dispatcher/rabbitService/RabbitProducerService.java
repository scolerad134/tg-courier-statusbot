package org.tg.dispatcher.rabbitService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import org.tg.dispatcher.dto.MessageDto;

import static org.tg.dispatcher.config.RabbitConfig.EXCHANGER;


@Log4j2
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@EnableRabbit
@Service
public class RabbitProducerService implements AmqpProducer{

    RabbitTemplate rabbitTemplate;
    static String FETCH_UPDATE_BOT = "routing.key";


    public void sendMessage(MessageDto message) {
        rabbitTemplate.convertAndSend(
                EXCHANGER,
                FETCH_UPDATE_BOT,
                message
        );

//        log.info("Send update to bot: {}", update.getMessage().getText());
    }


}
