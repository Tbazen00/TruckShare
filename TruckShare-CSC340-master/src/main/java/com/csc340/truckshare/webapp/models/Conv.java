package com.csc340.truckshare.webapp.models;

import jakarta.persistence.*;

import java.util.List;

// Entity annotation marks this class as a JPA entity
@Entity
// Specifies the table name in the database
@Table(name = "conversation")

public class Conv {

    // Primary key annotation, with auto-generated value
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int convId;

    private int sourceUserId; // ID of the user who initiated the conversation
    private int targetUserId; // ID of the user who is the target of the conversation

    private String sourceUsername;
    private String targetUsername;

    private int listingId;

    private String title; // Title of the conversation

    private Message lastMessage;

    private List<Integer> msgId;

    private boolean supportNeeded; // Flag indicating if support is needed

    // Default constructor
    public Conv() {
    }

    public Conv(int convId, int sourceUserId, int targetUserId,
                String sourceUsername, String targetUsername, int listingId,
                List<Integer> msgId, String title, boolean supportNeeded) {
        this.convId = convId;
        this.sourceUserId = sourceUserId;
        this.targetUserId = targetUserId;
        this.sourceUsername = sourceUsername;
        this.targetUsername = targetUsername;
        this.listingId = listingId;
        this.title = title;
        this.msgId = msgId;
        this.supportNeeded = supportNeeded;
    }

    public int getConvId() {
        return convId;
    }

    public void setConvId(int convId) {
        this.convId = convId;
    }

    public int getSourceUserId() {
        return sourceUserId;
    }

    public void setSourceUserId(int sourceUserId) {
        this.sourceUserId = sourceUserId;
    }

    public int getTargetUserId() {
        return targetUserId;
    }

    public void setTargetUserId(int targetUserId) {
        this.targetUserId = targetUserId;
    }

    public String getSourceUsername(){return sourceUsername;}

    public void setSourceUsername(String sourceUsername){this.sourceUsername = sourceUsername;}

    public String getTargetUsername(){return targetUsername;}

    public void setTargetUsername(String targetUsername){this.targetUsername = targetUsername;}

    public int getListingId(){return listingId;}

    public void setListingId(int listingId){this.listingId = listingId;}

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Integer> getMsgId(){return msgId;}

    public Integer getMsgId(int index){return msgId.get(index);}

    public void addMsgId(Integer msgId){this.msgId.add(msgId);}

    public void removeMsgId(int index){this.msgId.remove(index);}

    public boolean isSupportNeeded() {
        return supportNeeded;
    }

    public void setSupportNeeded(boolean supportNeeded) {
        this.supportNeeded = supportNeeded;
    }
}