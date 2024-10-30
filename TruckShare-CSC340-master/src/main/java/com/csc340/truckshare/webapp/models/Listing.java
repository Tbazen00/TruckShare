package com.csc340.truckshare.webapp.models;
import jakarta.persistence.*;

import java.util.Date;

@Entity // Specifies that the class is an entity and is mapped to a database table
@Table(name = "listing") // Specifies the name of the database table to be used for mapping
public class Listing {

    @Id   // Specifies the primary key of an entity
    @GeneratedValue(strategy = GenerationType.AUTO) // Provides the specification of generation strategies for the primary keys
    private int listingId; // Unique identifier for each listing

    private int userId; // Identifier for the user associated with this listing

    private int reserveUserId;

    private int convId;

    private String username;

    private String title; // Title of the listing

    private String details; // Detailed description of the listing

    @Temporal(TemporalType.DATE) // Specifies that the date should be stored in a specific temporal type (DATE)
    private Date targetDate; // Target date for the listing

    private int status; // 0=available, 1=reserved, 2=completed

    private byte[] image ;

    private int isNew;

    // Default constructor
    public Listing() {
    }

    // Parameterized constructor to initialize all fields
    public Listing(int listingId, int userId, int reserveUserId, int convId,
                   String username, String title, String details, Date targetDate,
                   int status, int isNew) {
        this.listingId = listingId; // Sets the listingId
        this.userId = userId; // Sets the userId
        this.reserveUserId = reserveUserId;
        this.convId = convId;
        this.username = username;
        this.title = title; // Sets the title
        this.details = details; // Sets the details
        this.targetDate = targetDate; // Sets the targetDate
        this.status = status; // Sets the status
        this.image = image;
        this.isNew = isNew;
    }

    public int getListingId() {
        return listingId;
    }

    public void setListingId(int listingId) {
        this.listingId = listingId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getReserveUserId(){return reserveUserId;}

    public void setReserveUserId(int reserveUserId){this.reserveUserId = reserveUserId;}

    public int getConvId() {return convId;}

    public void setConvId(int convId) {this.convId = convId;}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Date getTargetDate() {
        return targetDate;
    }

    public void setTargetDate(Date targetDate) {
        this.targetDate = targetDate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public int getNewness(){return isNew;}

    public void setNewness(int isNew){this.isNew = isNew;}
}