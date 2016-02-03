package com.example;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SendMessage {
    @JsonProperty("Type")
    private String type;
    @JsonProperty("Message")
    private String message;

    public SendMessage(String msg) {
        message = msg;
        type = "fail";
    }

    public boolean equals(SendMessage obj) {
        return ((this.type.equals(obj.type))
             && (this.message.equals(obj.message)));

    }
}
