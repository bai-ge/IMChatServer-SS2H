package com.baige.service.impl;

import com.baige.dao.impl.ChatMessageDAOImpl;
import com.baige.pojo.ChatMessageEntity;
import com.baige.service.IChatMessageService;

import java.util.Map;

public class ChatMessageServiceImpl implements IChatMessageService {
    ChatMessageDAOImpl chatMessageDAO;

    public ChatMessageDAOImpl getChatMessageDAO() {
        if(chatMessageDAO == null){
            chatMessageDAO = new ChatMessageDAOImpl();
        }
        return chatMessageDAO;
    }


    @Override
    public void sendMessage(ChatMessageEntity chatMessage, Map<String, Object> responseMsgMap) {

    }

    @Override
    public void findMsgRelate(int uid, int friendId, Map<String, Object> responseMsgMap) {

    }

    @Override
    public void findMsgRelateAfterTime(int uid, int friendId, long time, Map<String, Object> responseMsgMap) {

    }

    @Override
    public void findMsgRelateBeforeTime(int uid, int friendId, long time, Map<String, Object> responseMsgMap) {

    }

    @Override
    public void findMsg(int uid, Map<String, Object> responseMsgMap) {

    }

    @Override
    public void findMsgAfterTime(int uid, long time, Map<String, Object> responseMsgMap) {

    }

    @Override
    public void findMsgBeforeTime(int uid, long time, Map<String, Object> responseMsgMap) {

    }

    @Override
    public void readMsgBeforeTime(int uid, long time, Map<String, Object> responseMsgMap) {

    }

    @Override
    public void readMsgBeforeTime(int uid, int friendId, long time, Map<String, Object> responseMsgMap) {

    }
}
