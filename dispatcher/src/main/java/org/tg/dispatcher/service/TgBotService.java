package org.tg.dispatcher.service;

import jakarta.annotation.PostConstruct;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import org.telegram.telegrambots.meta.api.objects.commands.scope.BotCommandScopeDefault;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.tg.dispatcher.config.BotConfig;
import org.tg.dispatcher.dto.MessageDto;
import org.tg.dispatcher.rabbitService.AmqpProducer;
import org.tg.dispatcher.rabbitService.RabbitProducerService;


import java.util.ArrayList;
import java.util.List;


@Log4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Service
public class TgBotService extends TelegramLongPollingBot {

    @NonFinal
    @Getter
    long chatId;

    AmqpProducer producer;
    BotConfig config;

    public TgBotService(@Value("${bot.token}") String botToken, BotConfig config,
                        @Value("#{'${bot.admins}'.split(',')}") List<String> admins, RabbitProducerService producer) {
        super(botToken);
        this.config = config;
        this.producer = producer;

        printFirstAdminMenu();
    }


    @PostConstruct
    public void init() {
        config.setTgBotService(this);
    }

    @Override
    public String getBotUsername() {
        return config.getBotName();
    }


    @Override
    public void onUpdateReceived(Update update) {
        if(update.hasMessage() && update.getMessage().hasText()) {
            chatId = update.getMessage().getChatId();
            producer.sendMessage(new MessageDto(update.getMessage().getFrom().getUserName(),update.getMessage().getText()));
            String text = update.getMessage().getText();
            log.debug(text);
        }

    }





    public void printFirstAdminMenu() {
        List<BotCommand> commands = new ArrayList<>();
        commands.add(new BotCommand("/admin", "запускает работу бота"));
        commands.add(new BotCommand("/start", "запускает работу бота"));
        commands.add(new BotCommand("/create_courier", "запускает работу бота"));
        commands.add(new BotCommand("/couriers", "запускает работу бота"));
        commands.add(new BotCommand("/create_order", "запускает работу бота"));


        try {
            execute(new SetMyCommands(commands, new BotCommandScopeDefault(), null));
        } catch (TelegramApiException e) {
            log.error(e.getMessage());
        }
    }

    public void printFirstUserMenu() {
        List<BotCommand> commands = new ArrayList<>();
        commands.add(new BotCommand("/user", "запускает работу бота"));

        try {
//            execute(new SetMyCommands(commands,new BotCommandScopeDefault(),null));
            execute(new SetMyCommands());
        } catch (TelegramApiException e) {
            log.error(e.getMessage());
        }
    }


}
