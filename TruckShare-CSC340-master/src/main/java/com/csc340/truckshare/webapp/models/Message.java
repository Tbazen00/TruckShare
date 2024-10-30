package com.csc340.truckshare.webapp.models;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "message")

public class Message implements Serializable {

    // Primary key annotation, with auto-generated value
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int msgId;

    private int sourceUserId; // ID of the user who sent the message
    private int targetUserId;// ID of the user who is the recipient of the message

    //time will be implemented later

    private int convId;

    private String payload; // The content of the message

    // Default constructor
    public Message(){}

    // Parameterized constructor
    public Message(int msgId, int sourceUserId, int targetUserId, int convId, String payload) {
        this.msgId = msgId;
        this.sourceUserId = sourceUserId;
        this.convId = convId;
        this.payload = payload;
    }

    public int getMsgId() {
        return msgId;
    }

    public int getSourceUserId() {
        return sourceUserId;
    }

    public void setSourceUserId(int sourceUserId) {
        this.sourceUserId = sourceUserId;
    }

    public int getConvId() {return convId;}

    public void setConvId(int convId) {this.convId = convId;}

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }
}
