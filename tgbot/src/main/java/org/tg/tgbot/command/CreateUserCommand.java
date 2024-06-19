package org.tg.tgbot.command;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;
import org.tg.tgbot.model.User;
import org.tg.tgbot.rabbitService.AmqpProducer;
import org.tg.tgbot.repository.MessageBuffer;
import org.tg.tgbot.service.UserService;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@AdminCommand
@Component
public class CreateUserCommand implements Command {

    AmqpProducer amqpProducer;
    UserService userService;
    MessageBuffer messageBuffer;

    @Override
    public void execute() {
        User user = new User();
        amqpProducer.sendTextMessage("Введите ник в Telegram клиента");
            user.setNickName(messageBuffer.getMessage());
        amqpProducer.sendTextMessage("Введите имя клиента");
            user.setName(messageBuffer.getMessage());

        try {
            userService.save(user);
            amqpProducer.sendTextMessage("Клиент создан!");
        } catch (Exception e) {
            amqpProducer.sendTextMessage("Клиент не создан!");
        }
    }
}
