package com.csc340.truckshare.webapp.services;

import com.csc340.truckshare.webapp.models.Message;
import com.csc340.truckshare.webapp.repositories.ConvRepository;
import com.csc340.truckshare.webapp.repositories.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {
    @Autowired
    MessageRepository messageRepository;
    ConvRepository convRepository;

    public List<Message> getMsgForConv(int convId) {
        return messageRepository.queryMsgByConvId(convId);
    }

    public void createMessage(Message message) {
        messageRepository.save(message);
    }

    public void deleteMsg(int msgId) {
        messageRepository.deleteById(msgId);
    }
}
