package com.csc340.truckshare.webapp.repositories;

import com.csc340.truckshare.webapp.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository
        extends JpaRepository<User, Integer> {
    // check, are we selecting from User? is it where getUsername?
    @Query(value = "select * from user where username = ?1", nativeQuery = true)
    User findByUsername(String username);
}