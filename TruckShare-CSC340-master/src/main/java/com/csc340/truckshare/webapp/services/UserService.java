package com.csc340.truckshare.webapp.services;

import com.csc340.truckshare.webapp.models.User;
import com.csc340.truckshare.webapp.repositories.ConvRepository;
import com.csc340.truckshare.webapp.repositories.ListingRepository;
import com.csc340.truckshare.webapp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    ListingRepository listingRepository;
    @Autowired
    ConvRepository convRepository;

    public User getUserByUserName(String username) {
        return userRepository.findByUsername(username);
    }

    public int saveUser(User user) {
        userRepository.save(user);
        return user.getUserId();
    }

    public User getUserByUserId(int userId) {
        return userRepository.findById(userId).orElse(null);
    }

}
