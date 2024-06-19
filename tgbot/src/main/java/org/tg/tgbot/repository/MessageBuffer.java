package org.tg.tgbot.repository;


import org.springframework.stereotype.Component;



@Component
public class MessageBuffer {

    private volatile String message;

    public void setMessage(String message) {
        System.out.println("MessageBuffer.setMessage: " + message + " " + Thread.currentThread().getName());
        if (this.message == null) {
            this.message = message;
        }
    }

    public String getMessage() {
        System.out.println("MessageBuffer.getMessage: " + message + " "+ Thread.currentThread().getName());
        System.out.println();
        while(message == null) {
//            System.out.println("m");
        }
        System.out.println("after while: " + message + " " + Thread.currentThread().getName());
        String response = this.message;
        this.message = null;
        return response;
    }

}
