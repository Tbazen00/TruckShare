package com.csc340.truckshare.webapp.services;

import com.csc340.truckshare.webapp.models.*;
import com.csc340.truckshare.webapp.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Stream;

@Service
public class ConvService {
    @Autowired
    ConvRepository convRepository; // Repository for accessing conversation data
    @Autowired
    MessageRepository messageRepository; // Repository for accessing message data
    @Autowired
    ListingRepository listingRepository; // Repository for accessing listing data
    @Autowired
    UserRepository userRepository; // Repository for accessing user data

    @Autowired
    UserService userService;
    @Autowired
    ListingService listingService;

    // Method to get conversations by user ID
    public List<Conv> getConvByUserId(int userId){
        return Stream.concat(
                        (convRepository.queryBySourceUserId(userId).stream()),
                        (convRepository.queryByTargetUserId(userId).stream()))
                .toList(); // Query conversations by source user ID
    }

    // Method to get a conversation by its ID
    public Conv getConvById(int convId) {
        return convRepository.findById(convId).orElse(null); // Find conversation by ID or return null
    }

    // Method to save a conversation
    public void saveConv(Conv conversation) {
        convRepository.save(conversation); // Save the conversation to the repository
    }

    public Conv initConv(int userId, int userId2, int listingId) {
        Conv conv = new Conv();
        Listing listing = listingService.getListingById(listingId);
        conv.setSourceUserId(userId);
        conv.setTargetUserId(userId2);
        conv.setSourceUsername(userService.getUserByUserId(userId).getUsername());
        conv.setTargetUsername(userService.getUserByUserId(userId2).getUsername());
        conv.setListingId(listing.getListingId());
        return conv;
    }

    // Method to delete a conversation by its ID
    //TODO verify functionality
    public void deleteConv(int convId) {
        // Check if message repository is not null
        if (this.messageRepository != null) {
            // Query messages by conversation ID and delete them
            List<Message> msgToDelete = messageRepository.queryMsgByConvId(convId);
            for (Message message : msgToDelete) {
                messageRepository.deleteById(message.getMsgId()); // Delete each message by its ID
            }
        }
        convRepository.deleteById(convId); // Delete the conversation by its ID
    }
}
