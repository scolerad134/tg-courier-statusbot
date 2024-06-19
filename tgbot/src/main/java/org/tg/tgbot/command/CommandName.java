package org.tg.tgbot.command;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CommandName {

    START("/start"),

    GET_ALL_COURIER("/couriers"),
    CREATE_COURIER("/create_courier"),
    COURIER_INFO("/courier"),

    STAT("/stat"),
    HELP("/help");

    private final String commandName;

}
