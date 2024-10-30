package com.csc340.truckshare.webapp.repositories;

import com.csc340.truckshare.webapp.models.Listing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ListingRepository
        extends JpaRepository <Listing, Integer> {
        @Query(value = "select * from listing where user_Id =?1", nativeQuery = true)
        List<Listing> queryByUserId(int userId);
}
