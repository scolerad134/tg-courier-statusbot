package org.tg.dispatcher.rabbitService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import org.tg.dispatcher.service.SendMessageService;

import java.util.List;


@Log4j2
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Service
public class RabbitConsumerService implements AmqpConsumer{

    SendMessageService sendMessageService;

    final static String CATCH_MESSAGE = "queue.dispatcher";
    final static String CATCH_COURIERS = "queue.dispatcher.couriers";

    @RabbitListener(queues = CATCH_MESSAGE)
    public void getMessage(String content) {
        sendMessageService.sendMessage(content);
        System.out.println("Received message: " + content);
    }

//    @RabbitListener(queues = CATCH_COURIERS)
//    public void getCouriers(List<CourierDto> couriers) {
//        outputModelService.printInfoCouriers(couriers);
//    }
}
