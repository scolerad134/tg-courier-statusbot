package org.tg.dispatcher.service;

import org.springframework.stereotype.Component;

@Component
public interface SendMessageService {

    void sendMessage(String text);
}
