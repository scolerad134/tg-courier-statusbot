package org.tg.tgbot.rabbitService;


import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.tg.tgbot.dto.MessageDto;
import org.tg.tgbot.repository.MessageBuffer;
import org.tg.tgbot.service.DistributeService;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@EnableRabbit
@Service
public class RabbitMQListener implements AmqpConsumer{

    DistributeService distributeService;

   final static String FETCH_UPDATE_DATE = "queue.service";

    @Async("getAsyncExecutor")
    @RabbitListener(queues = FETCH_UPDATE_DATE)
    public void receive(@Payload MessageDto message) {
        System.out.println("Threads: " + Thread.activeCount());
        System.out.println("Received message: " + message + " " + Thread.currentThread().getName());
        distributeService.manageUserMessage(message);
    }


}
