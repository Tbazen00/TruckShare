package com.csc340.truckshare.webapp.repositories;

import com.csc340.truckshare.webapp.models.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository
        extends JpaRepository<Message, Integer> {
    @Query(value = "select * from message where conv_Id = ?1", nativeQuery = true)
    List<Message> queryMsgByConvId(int convId);
}
