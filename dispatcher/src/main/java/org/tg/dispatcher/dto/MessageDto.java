package org.tg.dispatcher.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class MessageDto {

    private String nickName;
    private String message;

    public MessageDto(@JsonProperty("nickName") String nickName, @JsonProperty("message") String message) {
        this.nickName = nickName;
        this.message = message;
    }
}
