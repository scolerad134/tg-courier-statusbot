package org.tg.tgbot.model;

import lombok.Getter;

@Getter
public enum OrderStatus {
    AWAIT("доставляется"),
    NEW("новый заказ"),
    CANCEL("отменен"),
    ISSUED("выдано"),
    REFUND("возврат");

    private final String status;

    OrderStatus(String statusName) {
        this.status = statusName;
    }


}
