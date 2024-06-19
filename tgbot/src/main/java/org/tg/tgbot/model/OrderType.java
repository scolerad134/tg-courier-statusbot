package org.tg.tgbot.model;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum OrderType {

    FRIDGE("холодильник"),
    NUKE("микроволновки");
    private final String type;

}
