package com.baige.service.impl;

import com.baige.common.Parm;
import com.baige.connect.msg.PushHelper;
import com.baige.dao.impl.ChatMessageDAOImpl;
import com.baige.dao.impl.UserDAOImpl;
import com.baige.exception.SqlException;
import com.baige.pojo.ChatMessageEntity;
import com.baige.pojo.UsersEntity;
import com.baige.service.IChatMessageService;
import com.baige.util.JsonTools;

import java.util.List;
import java.util.Map;

public class ChatMessageServiceImpl implements IChatMessageService {
    ChatMessageDAOImpl chatMessageDAO;

    UserDAOImpl userDAO;

    public ChatMessageDAOImpl getChatMessageDAO() {
        if(chatMessageDAO == null){
            chatMessageDAO = new ChatMessageDAOImpl();
        }
        return chatMessageDAO;
    }

    public UserDAOImpl getUserDAO() {
        if(userDAO == null){
            userDAO = new UserDAOImpl();
        }
        return userDAO;
    }

    @Override
    public void sendMessage(ChatMessageEntity chatMessage, Map<String, Object> responseMsgMap) {
        try {
            getChatMessageDAO().doSave(chatMessage);
            //TODO 推送
            UsersEntity from = getUserDAO().doGetById(chatMessage.getSenderId());
            UsersEntity to = getUserDAO().doGetById(chatMessage.getReceiveId());
            PushHelper.push(from.getDeviceId(), to.getDeviceId(), JsonTools.getJSON(chatMessage), Parm.CHAT);

            responseMsgMap.put(Parm.CODE, Parm.CODE_SUCCESS);
            responseMsgMap.put(Parm.MEAN, "发送成功");
            responseMsgMap.put(Parm.CHAT, chatMessage);
        } catch (SqlException e) {
            e.printStackTrace();
            responseMsgMap.put(Parm.CODE, Parm.CODE_FAIL);
            responseMsgMap.put(Parm.MEAN, e.getMessage());
        }
    }

    @Override
    public void findMsgRelate(int uid, int friendId, Map<String, Object> responseMsgMap) {
        try {
            List<ChatMessageEntity> chatMessages = getChatMessageDAO().findMsgRelate(uid, friendId);
            //TODO 推送
            if (chatMessages != null && chatMessages.size() > 0) {
                responseMsgMap.put(Parm.CODE, Parm.CODE_SUCCESS);
                responseMsgMap.put(Parm.MEAN, "查找成功");
                responseMsgMap.put(Parm.CHAT_LIST, chatMessages);
            } else {
                responseMsgMap.put(Parm.CODE, Parm.CODE_BLANK);
                responseMsgMap.put(Parm.MEAN, "查找成功");
            }
        } catch (SqlException e) {
            e.printStackTrace();
            responseMsgMap.put(Parm.CODE, Parm.CODE_FAIL);
            responseMsgMap.put(Parm.MEAN, e.getMessage());
        }
    }

    @Override
    public void findMsgRelateAfterTime(int uid, int friendId, long time, Map<String, Object> responseMsgMap) {
        try {
            List<ChatMessageEntity> chatMessages = getChatMessageDAO().findMsgRelateAfterTime(uid, friendId, time);
            //TODO 推送
            if (chatMessages != null && chatMessages.size() > 0) {
                responseMsgMap.put(Parm.CODE, Parm.CODE_SUCCESS);
                responseMsgMap.put(Parm.MEAN, "查找成功");
                responseMsgMap.put(Parm.CHAT_LIST, chatMessages);
            } else {
                responseMsgMap.put(Parm.CODE, Parm.CODE_BLANK);
                responseMsgMap.put(Parm.MEAN, "查找成功");
            }
        } catch (SqlException e) {
            e.printStackTrace();
            responseMsgMap.put(Parm.CODE, Parm.CODE_FAIL);
            responseMsgMap.put(Parm.MEAN, e.getMessage());
        }
    }

    @Override
    public void findMsgRelateBeforeTime(int uid, int friendId, long time, Map<String, Object> responseMsgMap) {
        try {
            List<ChatMessageEntity> chatMessages = getChatMessageDAO().findMsgRelateBeforeTime(uid, friendId, time);
            //TODO 推送
            if (chatMessages != null && chatMessages.size() > 0) {
                responseMsgMap.put(Parm.CODE, Parm.CODE_SUCCESS);
                responseMsgMap.put(Parm.MEAN, "查找成功");
                responseMsgMap.put(Parm.CHAT_LIST, chatMessages);
            } else {
                responseMsgMap.put(Parm.CODE, Parm.CODE_BLANK);
                responseMsgMap.put(Parm.MEAN, "查找成功");
            }
        } catch (SqlException e) {
            e.printStackTrace();
            responseMsgMap.put(Parm.CODE, Parm.CODE_FAIL);
            responseMsgMap.put(Parm.MEAN, e.getMessage());
        }
    }

    @Override
    public void findMsg(int uid, Map<String, Object> responseMsgMap) {
        try {
            List<ChatMessageEntity> chatMessages = getChatMessageDAO().findMsg(uid);
            //TODO 推送
            if (chatMessages != null && chatMessages.size() > 0) {
                responseMsgMap.put(Parm.CODE, Parm.CODE_SUCCESS);
                responseMsgMap.put(Parm.MEAN, "查找成功");
                responseMsgMap.put(Parm.CHAT_LIST, chatMessages);
            } else {
                responseMsgMap.put(Parm.CODE, Parm.CODE_BLANK);
                responseMsgMap.put(Parm.MEAN, "查找成功");
            }
        } catch (SqlException e) {
            e.printStackTrace();
            responseMsgMap.put(Parm.CODE, Parm.CODE_FAIL);
            responseMsgMap.put(Parm.MEAN, e.getMessage());
        }
    }

    @Override
    public void findMsgAfterTime(int uid, long time, Map<String, Object> responseMsgMap) {
        try {
            List<ChatMessageEntity> chatMessages = getChatMessageDAO().findMsgAfterTime(uid, time);
            //TODO 推送
            if (chatMessages != null && chatMessages.size() > 0) {
                responseMsgMap.put(Parm.CODE, Parm.CODE_SUCCESS);
                responseMsgMap.put(Parm.MEAN, "查找成功");
                responseMsgMap.put(Parm.CHAT_LIST, chatMessages);
            } else {
                responseMsgMap.put(Parm.CODE, Parm.CODE_BLANK);
                responseMsgMap.put(Parm.MEAN, "查找成功");
            }
        } catch (SqlException e) {
            e.printStackTrace();
            responseMsgMap.put(Parm.CODE, Parm.CODE_FAIL);
            responseMsgMap.put(Parm.MEAN, e.getMessage());
        }
    }

    @Override
    public void findMsgBeforeTime(int uid, long time, Map<String, Object> responseMsgMap) {
        try {
            List<ChatMessageEntity> chatMessages = getChatMessageDAO().findMsgBeforeTime(uid, time);
            //TODO 推送
            if (chatMessages != null && chatMessages.size() > 0) {
                responseMsgMap.put(Parm.CODE, Parm.CODE_SUCCESS);
                responseMsgMap.put(Parm.MEAN, "查找成功");
                responseMsgMap.put(Parm.CHAT_LIST, chatMessages);
            } else {
                responseMsgMap.put(Parm.CODE, Parm.CODE_BLANK);
                responseMsgMap.put(Parm.MEAN, "查找成功");
            }
        } catch (SqlException e) {
            e.printStackTrace();
            responseMsgMap.put(Parm.CODE, Parm.CODE_FAIL);
            responseMsgMap.put(Parm.MEAN, e.getMessage());
        }
    }

    @Override
    public void readMsgBeforeTime(int uid, long time, Map<String, Object> responseMsgMap) {
        try {
            boolean success = getChatMessageDAO().readBeforeTime(uid, time);
            responseMsgMap.put(Parm.CODE, Parm.CODE_SUCCESS);
            responseMsgMap.put(Parm.MEAN, "更新成功");
        } catch (SqlException e) {
            e.printStackTrace();
            responseMsgMap.put(Parm.CODE, Parm.CODE_FAIL);
            responseMsgMap.put(Parm.MEAN, e.getMessage());
        }
    }

    @Override
    public void readMsgBeforeTime(int uid, int friendId, long time, Map<String, Object> responseMsgMap) {
        try {
            boolean success = getChatMessageDAO().readBeforeTime(uid, friendId, time);
            responseMsgMap.put(Parm.CODE, Parm.CODE_SUCCESS);
            responseMsgMap.put(Parm.MEAN, "更新成功");
        } catch (SqlException e) {
            e.printStackTrace();
            responseMsgMap.put(Parm.CODE, Parm.CODE_FAIL);
            responseMsgMap.put(Parm.MEAN, e.getMessage());
        }
    }
}
