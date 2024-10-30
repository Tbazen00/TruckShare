package com.csc340.truckshare.webapp.controllers;

import com.csc340.truckshare.webapp.models.*;
import com.csc340.truckshare.webapp.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/conv")


public class ConvController {
    @Autowired
    UserService userService;
    @Autowired
    ListingService listingService;
    @Autowired
    ConvService convService;
    @Autowired
    MessageService messageService;

    //List of conversations of a particular user
    @GetMapping("/user-id/{userId}")
    public String getConvByUserId(@PathVariable int userId, Model model) {
        model.addAttribute("convList", convService.getConvByUserId(userId));
        model.addAttribute("user", userService.getUserByUserId(userId));
        return "user-conv";
    }

    //Retrieve a conversation between two users about a specific listing
    @GetMapping("/user-1/{userId}/user-2/{userId2}/listing/{listingId}")
    public String getConvByUserPair(@PathVariable int userId, @PathVariable int userId2,
                                    @PathVariable int listingId,
                                    Model model){
        Listing listing = listingService.getListingById(listingId);
        Conv conv;

        // Check if listing has an existing conversation
        if (listing.getConvId() == 0) {
            // Initialize a new conversation
            conv = convService.initConv(userId, userId2, listingId);
            convService.saveConv(conv);
            listing.setConvId(conv.getConvId());
            listingService.updateListing(listing);
            model.addAttribute("convAttr",
                    conv);
            model.addAttribute("user",
                    userService.getUserByUserId(userId));
            model.addAttribute("listing",
                    listingService.getListingById(listingId));
        }
        else {
            // Check if conversation exists
            if (convService.getConvById(listing.getConvId()) == null){
                // Initialize a new conversation
                conv = convService.initConv(userId, userId2, listingId);
                convService.saveConv(conv);
                listing.setConvId(conv.getConvId());
                listingService.updateListing(listing);
            }
            else{
                // Retrieve existing conversation
                conv = convService.getConvById(listing.getConvId());
                convService.saveConv(conv);
                listing.setConvId(conv.getConvId());
                listingService.updateListing(listing);
                model.addAttribute("msgList",
                        messageService.getMsgForConv(conv.getConvId()));
            }
            model.addAttribute("convAttr",
                    conv);
            model.addAttribute("listing",
                    listingService.getListingById(listingId));
            model.addAttribute("user",
                    userService.getUserByUserId(userId));
        }
        return "conv-message";
    }

    //Retrieve a conversation by conversation ID and user ID
    @GetMapping("/conv-id/{convId}/user/{userId}")
    public String getConvById(@PathVariable int convId, @PathVariable int userId, Model model) {
        Conv conv = convService.getConvById(convId);
        model.addAttribute("listing",
                listingService.getListingById(conv.getListingId()));
        model.addAttribute("convAttr", conv);
        model.addAttribute("msgList",
                messageService.getMsgForConv(conv.getConvId()));
        model.addAttribute("user", userService.getUserByUserId(userId));
        return "conv-message";
    }

    //Delete Conversation
    @GetMapping("/delete/{id}/user/{userId}/confirmed")
    public String deleteConversation(@PathVariable int id, @PathVariable int userId) {
        convService.deleteConv(id);
        return "redirect:/conv/user-id/{userId}";
    }

    //Confirm before full deletion of conversation
    @GetMapping("/delete/{id}/user/{userId}")
    public String deleteConfirmation(@PathVariable int id, @PathVariable int userId, Model model) {
        model.addAttribute("convAttr", convService.getConvById(id));
        model.addAttribute("user", userService.getUserByUserId(userId));
        return "conv-delete-confirmation";
    }

}