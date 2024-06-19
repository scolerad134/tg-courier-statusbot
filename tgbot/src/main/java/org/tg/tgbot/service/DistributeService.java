package org.tg.tgbot.service;


import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.tg.tgbot.command.CommandContainer;
import org.tg.tgbot.config.RabbitConfig;
import org.tg.tgbot.dto.MessageDto;
import org.tg.tgbot.repository.MessageBuffer;

import java.util.Objects;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Service
public class DistributeService {

    CommandContainer commandContainer;
    MessageBuffer messageBuffer;
//    RabbitConfig rabbitConfig;
    ApplicationContext applicationContext;

    static String PREFIX_BEFORE_COMMAND = "/";

    public void manageUserMessage(MessageDto message) {

        if(message.getMessage().startsWith(PREFIX_BEFORE_COMMAND)) {
            ThreadPoolTaskExecutor executor = (ThreadPoolTaskExecutor) applicationContext.getBean("getAsyncExecutor");

            commandContainer.getCommand(message.getMessage(), message.getNickName()).execute();
        } else {
            System.out.println("manage user message: " + message.getMessage() + " " + Thread.currentThread().getName());

            messageBuffer.setMessage(message.getMessage());
        }
    }
}
