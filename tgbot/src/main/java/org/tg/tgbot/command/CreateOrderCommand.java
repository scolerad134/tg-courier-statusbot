package org.tg.tgbot.command;


import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;
import org.tg.tgbot.service.OrderService;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@AdminCommand
@Component
public class CreateOrderCommand implements Command {

    OrderService orderService;
    @Override
    public void execute() {

    }
}
