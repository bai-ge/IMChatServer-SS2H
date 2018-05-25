package com.baige.service;

import com.baige.pojo.ChatMessageEntity;

import java.util.Map;

public interface IChatMessageService {

    void sendMessage(ChatMessageEntity chatMessage, Map<String, Object> responseMsgMap);

    void findMsgRelate(int uid, int friendId, Map<String, Object> responseMsgMap);

    void findMsgRelateAfterTime(int uid, int friendId, long time, Map<String, Object> responseMsgMap);

    void findMsgRelateBeforeTime(int uid, int friendId, long time, Map<String, Object> responseMsgMap);

    void findMsg(int uid, Map<String, Object> responseMsgMap);

    void findMsgAfterTime(int uid, long time, Map<String, Object> responseMsgMap);

    void findMsgBeforeTime(int uid, long time, Map<String, Object> responseMsgMap);

    void readMsgBeforeTime(int uid, long time, Map<String, Object> responseMsgMap);

    void readMsgBeforeTime(int uid, int friendId, long time, Map<String, Object> responseMsgMap);
}
