package com.baige.dao;

import com.baige.exception.SqlException;
import com.baige.pojo.ChatMessageEntity;

import java.util.List;

public interface ChatMessageDAO {

    String ID = "id";
    String SENDER_ID = "senderId";
    String RECEIVE_ID = "receiveId";
    String SEND_TIME = "sendTime";
    String CONTEXT = "context";
    String CONTEXT_TYPE = "contextType";
    String CONTEXT_STATE = "contextState";
    String REMARK = "remark";

    List<ChatMessageEntity> findMsgRelate(int uid, int friendId) throws SqlException;

    List<ChatMessageEntity> findMsgRelateAfterTime(int uid, int friendId, long time) throws SqlException;

    List<ChatMessageEntity> findMsgRelateBeforeTime(int uid, int friendId, long time) throws SqlException;

    List<ChatMessageEntity> findMsg(int uid) throws SqlException;

    List<ChatMessageEntity> findMsgAfterTime(int uid, long time) throws SqlException;

    List<ChatMessageEntity> findMsgBeforeTime(int uid, long time) throws SqlException;

    boolean readBeforeTime(int uid, long time) throws SqlException;

    boolean readBeforeTime(int uid, int friendId, long time) throws SqlException;

}
