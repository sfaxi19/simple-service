package com.example;

public class SendMessage {
    public String type;
    public String message;

    public SendMessage(String msg) {
        message = msg;
        type = "fail";
    }

    public boolean equals(SendMessage obj) {
        return ((obj.type.equals(this.type))
             && (obj.message.equals(this.message)));
    }
}
