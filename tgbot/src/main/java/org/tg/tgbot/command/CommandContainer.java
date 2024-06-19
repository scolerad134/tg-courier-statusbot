package org.tg.tgbot.command;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

import static org.tg.tgbot.command.CommandName.*;


@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Component
public class CommandContainer {

    HashMap<String, Command> commands;
    Command unknownCommand;
    @Value("${bot.admins}")
    List<String> admins;


    public CommandContainer(
            List<String> admins,
            UnknownCommand unknownCommand,
            CreateCourierCommand createCourierCommand,
            GetCouriersCommand getCouriersCommand,
            GetCourierInfoCommand getCourierInfoCommand,
            CreateOrderCommand createOrderCommand,
            CreateUserCommand createUserCommand) {

        this.commands = new HashMap<>();
        this.admins = admins;
        this.unknownCommand = unknownCommand;

        commands.put(CREATE_COURIER.getCommandName(), createCourierCommand);
        commands.put(GET_ALL_COURIER.getCommandName(), getCouriersCommand);
        commands.put(COURIER_INFO.getCommandName(), getCourierInfoCommand);
        commands.put(CREATE_ORDER.getCommandName(), createOrderCommand);
        commands.put(CREATE_USER.getCommandName(), createUserCommand);


    }

    public Command getCommand(String commandName, String username) {
        Command command = commands.getOrDefault(commandName,unknownCommand);
        if(command.getClass().getAnnotation(AdminCommand.class) != null) {
            if (admins.contains(username)) {
                return commands.get(commandName);
            }
            else {
                return unknownCommand;
            }
        }
        return command;
    }
}
