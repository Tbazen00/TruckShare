package com.csc340.truckshare.webapp.controllers;

import com.csc340.truckshare.webapp.models.*;
import com.csc340.truckshare.webapp.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/user")

public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    ListingService listingService;
    @Autowired
    ConvService convService;

    @GetMapping("/login") // the login page
    public String userLogin(Model model){
        model.addAttribute("user", new User());
        return "user-login";
    }

    @GetMapping("/signup") // aka create
    public String userSignup(Model model){
        model.addAttribute("user", new User());

        return "user-signup";
    }
    //TODO refuse empty input
    @PostMapping("/create")
    public String createUser(@ModelAttribute("user") User user, Model model) {
        // User: isAdmin? userPassword? getUsername? firstName? lastName?
        if (userService.getUserByUserName(user.getUsername())!=null) {
            model.addAttribute("exists", "User exists");
            return "user-signup";
        }
        else {
            user.setUserType(1);
            return "redirect:/user/user-id/" + userService.saveUser(user);
        }
    }

    @PostMapping("/auth")
    public String authUser(@ModelAttribute("user") User user, Model model) {
        User authUser = userService.getUserByUserName(user.getUsername());
        if (authUser!=null) {
            if (user.getPassword().equals(authUser.getPassword())){
                return "redirect:/user/user-id/" + authUser.getUserId();
            }
            else {
                model.addAttribute("invalid", "Invalid Password");
                return "user-login";
            }
        }
        else {
            model.addAttribute("invalid", "User Not Found");
            return "user-login";
        }
    }

    @GetMapping("/user-id/{userId}")
    public String userPage(@PathVariable int userId, Model model) {
        User user = userService.getUserByUserId(userId);
        model.addAttribute("user", user);
        List<Listing> allListing = listingService.getAllListings();
        if(user.getUserType()!=2){
            allListing.removeIf(listing -> listing.getStatus() == 1);
        }
        model.addAttribute("listingList", allListing);
        return "user-homepage";
    }

    @GetMapping("/guest")
    public String guestPage(Model model) {
        User guest = new User();
        guest.setUserId(0);
        guest.setUserType(0);
        model.addAttribute("user", guest);
        model.addAttribute("listingList", listingService.getAllListings());
        return "user-homepage";
    }

    @GetMapping("/logout")
    public String userLogout(Model model){
        model.addAttribute("user", new User());
        model.addAttribute("logout", "logout");
        return "user-login";
    }
}
