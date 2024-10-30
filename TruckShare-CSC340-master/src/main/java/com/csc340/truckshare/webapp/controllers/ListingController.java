package com.csc340.truckshare.webapp.controllers;

import com.csc340.truckshare.webapp.models.*;
import com.csc340.truckshare.webapp.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequestMapping("/listing")


public class ListingController {


    @Autowired
    ListingService listingService;
    @Autowired
    UserService userService;
    @Autowired
    ConvService conversationService;

    //Get details of a particular listing by listing ID and user ID.
    @GetMapping("/listing-id/{listingId}/user-id/{userId}") // A particular listing
    public String findListingById(@PathVariable int listingId, @PathVariable int userId, Model model) {
        model.addAttribute("listing", listingService.getListingById(listingId));
        model.addAttribute("user", userService.getUserByUserId(userId));
        return "listing-detail";
    }

    //Get all listings for a particular user by user ID.
    @GetMapping("/user-id/{id}") // All listings for a particular user
    public String findListingByUserId(@PathVariable int id, Model model){
        model.addAttribute("listings", listingService.queryByUserId(id));
        model.addAttribute("user", userService.getUserByUserId(id));
        return "user-listings";
    }

    //Get all listings reserved by a particular user.
    @GetMapping("/reservations/user-id/{id}")
    public String findListingReservedByUserId(@PathVariable int id, Model model) {
        List<Listing> allListing = listingService.getAllListings();
        allListing.removeIf(listing -> listing.getReserveUserId()!=id);
        model.addAttribute("listings", allListing);
        model.addAttribute("user", userService.getUserByUserId(id));
        return "user-reservations";
    }

    //Get the form for creating a new listing for a user.
    @GetMapping("/form/user/{id}")
    public String listingForm(@PathVariable int id, Model model)
    {
        Listing newListing = new Listing();
        newListing.setUserId(id);
        newListing.setStatus(0);
        model.addAttribute("listing", newListing);
        model.addAttribute("user", userService.getUserByUserId(id));
        return "listing-create";
    }

    //Get the form for editing an existing listing.
    @GetMapping("/form/{id}/user/{userId}")
    public String existingForm(@PathVariable int id, @PathVariable int userId, Model model) {
        model.addAttribute("listing", listingService.getListingById(id));
        model.addAttribute("user", userService.getUserByUserId(userId));
        return "listing-update";
    }

    //Save a new listing.
    @PostMapping("/update")
    public String saveListing(@ModelAttribute("listing") Listing listing){ //userId passed by front end
        return "redirect:/listing/listing-id/" +listingService.updateListing(listing)
            + "/user-id/" + listing.getUserId();
    }

    @PostMapping("/create")
    public String createListing(@ModelAttribute("listing") Listing listing){
        listing.setUsername(
                userService.getUserByUserId(
                                listing.getUserId()).
                        getUsername());
        return "redirect:/listing/listing-id/" + listingService.createListing(listing)
                + "/user-id/" + listing.getUserId();
    }

    //Upload an image for a listing.
    @PostMapping("/uploadImage")
    public ResponseEntity<String> uploadImage(@RequestParam("image") MultipartFile image, @RequestParam("listingId") int listingId) {
        try {
            Listing listing = listingService.getListingById(listingId);
            listing.setImage(image.getBytes());
            listingService.updateListing(listing);
            return ResponseEntity.ok("Image uploaded successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Image upload failed: " + e.getMessage());
        }
    }

    //Reserve a listing.
    @GetMapping("/reserve/{listingId}/user-id/{userId}")
    public String reserveListing(@PathVariable int listingId, @PathVariable int userId) {
        Listing listing = listingService.getListingById(listingId);
        listing.setStatus(1);
        listing.setReserveUserId(userId);
        listingService.updateListing(listing);
        return "redirect:/listing/listing-id/"+listingId+"/user-id/"+userId;
    }

    //Mark a listing as complete.
    @GetMapping("/complete/{listingId}/user-id/{userId}")
    public String completeListing(@PathVariable int listingId, @PathVariable int userId) {
        Listing listing = listingService.getListingById(listingId);
        listing.setStatus(2);
        listingService.updateListing(listing);
        return "redirect:/listing/listing-id/"+listingId+"/user-id/"+userId;
    }

    //Delete a listing.
    @GetMapping("/delete/{id}/user/{userId}/confirmed")
    public String deleteListing(@PathVariable int id, @PathVariable int userId){
        listingService.deleteListing(id);
        return "redirect:/listing/user-id/{userId}";
    }

    //Confirm deletion of a listing.
    @GetMapping("/delete/{id}/user/{userId}")
    public String deleteConfirmation(@PathVariable int id, @PathVariable int userId, Model model) {
        model.addAttribute("listing", listingService.getListingById(id));
        model.addAttribute("user", userService.getUserByUserId(userId));
        return "listing-delete-confirmation";
    }

}

