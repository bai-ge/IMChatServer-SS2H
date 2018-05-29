package com.baige.action;

import com.baige.common.Parm;
import com.baige.common.State;
import com.baige.pojo.ChatMessageEntity;
import com.baige.pojo.UsersEntity;
import com.baige.service.impl.ChatMessageServiceImpl;
import com.baige.service.impl.UserServiceImpl;
import com.baige.util.Tools;


public class ChatMessageAction extends BaseAction {
    private int id;
    private Integer senderId;
    private Integer receiveId;
    private Long sendTime;
    private String context;
    private Integer contextType;
    private Integer contextState;
    private String remark;

    private String senderName;
    private String receiveName;

    private String verification;
    private Integer uid;
    private Integer friendId;

    private UserServiceImpl userService;

    private ChatMessageServiceImpl chatMessageService;

    public UserServiceImpl getUserService() {
        if(userService == null){
            userService = new UserServiceImpl();
        }
        return userService;
    }

    public ChatMessageServiceImpl getChatMessageService() {
        if(chatMessageService == null){
            chatMessageService = new ChatMessageServiceImpl();
        }
        return chatMessageService;
    }

    /**
     * senderId
     * receiveId
     * verification
     * context
     * contextType 可有可无
     *
     * @return
     */
    public String sendMsg() {
        if (!Tools.isEmpty(getVerification()) && !Tools.isEmpty(context) && senderId != 0 && receiveId != 0) {
            UsersEntity user = getUserService().checkUser(senderId, getVerification());
            if (user != null) {
                ChatMessageEntity chatMessage = init();
                getResponseMsgMap().clear();
                getChatMessageService().sendMessage(chatMessage, getResponseMsgMap());
            } else {
                getResponseMsgMap().clear();
                getResponseMsgMap().put(Parm.CODE, Parm.CODE_INVALID);
                getResponseMsgMap().put(Parm.MEAN, "验证失败");
            }
        } else {
            getResponseMsgMap().clear();
            getResponseMsgMap().put(Parm.CODE, Parm.CODE_UNKNOWN);
            getResponseMsgMap().put(Parm.MEAN, "参数错误");
        }
        return SUCCESS;
    }

    /**
     * senderId
     * receiveId (可忽略)
     * verification
     *
     * @return
     */
    public String findMsg() {
        if (!Tools.isEmpty(getVerification()) && senderId != null) {
            UsersEntity user = getUserService().checkUser(senderId, getVerification());
            if (user != null) {
                getResponseMsgMap().clear();
                if (getReceiveId() == null) {
                    getChatMessageService().findMsg(senderId, getResponseMsgMap());
                } else {
                    getChatMessageService().findMsgRelate(senderId, receiveId, getResponseMsgMap());
                }
            } else {
                getResponseMsgMap().clear();
                getResponseMsgMap().put(Parm.CODE, Parm.CODE_INVALID);
                getResponseMsgMap().put(Parm.MEAN, "验证失败");
            }
        } else {
            getResponseMsgMap().clear();
            getResponseMsgMap().put(Parm.CODE, Parm.CODE_UNKNOWN);
            getResponseMsgMap().put(Parm.MEAN, "参数错误");
        }
        return SUCCESS;
    }

    /**
     * senderId
     * receiveId (可忽略)
     * verification
     * sendTime
     *
     * @return
     */
    public String findMsgAfterTime() {
        if (!Tools.isEmpty(getVerification()) && senderId != null) {
            UsersEntity user = getUserService().checkUser(senderId, getVerification());
            if (user != null) {
                getResponseMsgMap().clear();
                if (getReceiveId() == null) {
                    getChatMessageService().findMsgAfterTime(senderId, sendTime, getResponseMsgMap());
                } else {
                    getChatMessageService().findMsgRelateAfterTime(senderId, receiveId, sendTime, getResponseMsgMap());
                }
            } else {
                getResponseMsgMap().clear();
                getResponseMsgMap().put(Parm.CODE, Parm.CODE_INVALID);
                getResponseMsgMap().put(Parm.MEAN, "验证失败");
            }
        } else {
            getResponseMsgMap().clear();
            getResponseMsgMap().put(Parm.CODE, Parm.CODE_UNKNOWN);
            getResponseMsgMap().put(Parm.MEAN, "参数错误");
        }
        return SUCCESS;
    }

    /**
     * senderId
     * receiveId (可忽略)
     * verification
     * sendTime
     *
     * @return
     */
    public String findMsgBeforeTime() {
        if (!Tools.isEmpty(getVerification()) && senderId != null) {
            UsersEntity user = getUserService().checkUser(senderId, getVerification());
            if (user != null) {
                getResponseMsgMap().clear();
                if (getReceiveId() == null) {
                    getChatMessageService().findMsgBeforeTime(senderId, sendTime, getResponseMsgMap());
                } else {
                    getChatMessageService().findMsgRelateBeforeTime(senderId, receiveId, sendTime, getResponseMsgMap());
                }
            } else {
                getResponseMsgMap().clear();
                getResponseMsgMap().put(Parm.CODE, Parm.CODE_INVALID);
                getResponseMsgMap().put(Parm.MEAN, "验证失败");
            }
        } else {
            getResponseMsgMap().clear();
            getResponseMsgMap().put(Parm.CODE, Parm.CODE_UNKNOWN);
            getResponseMsgMap().put(Parm.MEAN, "参数错误");
        }
        return SUCCESS;
    }

    /**
     * uid
     * friendId
     * verification
     * sendTime
     *
     * @return
     */
    public String readMsgBeforeTime() {
        if (!Tools.isEmpty(getVerification()) && uid != null) {
            UsersEntity user = getUserService().checkUser(uid, getVerification());
            if (user != null) {
                getResponseMsgMap().clear();
                if(getFriendId() == null){
                    getChatMessageService().readMsgBeforeTime(uid, sendTime, getResponseMsgMap());
                }else{
                    getChatMessageService().readMsgBeforeTime(uid, friendId, sendTime, getResponseMsgMap());
                }
            } else {
                getResponseMsgMap().clear();
                getResponseMsgMap().put(Parm.CODE, Parm.CODE_INVALID);
                getResponseMsgMap().put(Parm.MEAN, "验证失败");
            }
        } else {
            getResponseMsgMap().clear();
            getResponseMsgMap().put(Parm.CODE, Parm.CODE_UNKNOWN);
            getResponseMsgMap().put(Parm.MEAN, "参数错误");
        }
        return SUCCESS;
    }


    private ChatMessageEntity init() {
        ChatMessageEntity chatMessage = new ChatMessageEntity();
        chatMessage.setId(id);
        chatMessage.setSenderId(senderId);
        chatMessage.setReceiveId(receiveId);
        chatMessage.setSendTime(System.currentTimeMillis());
        if (getSendTime() != null) {
            chatMessage.setSendTime(getSendTime());
        }
        chatMessage.setContext(context);
        chatMessage.setContextType(Parm.MSG_TYPE_TEXT);
        if (contextType != null) {
            chatMessage.setContextType(contextType);
        }
        chatMessage.setContextState(State.UNREAD_STATE);
        if(contextState != null){
            chatMessage.setContextType(contextState);
        }
        chatMessage.setRemark(remark);
        if (Tools.isEmpty(remark)) {
            chatMessage.setRemark(Tools.ramdom());
        }
        System.out.println(chatMessage.toString());
        return chatMessage;
    }


    public Integer getSenderId() {
        return senderId;
    }

    public void setSenderId(Integer senderId) {
        this.senderId = senderId;
    }

    public Integer getReceiveId() {
        return receiveId;
    }

    public void setReceiveId(Integer receiveId) {
        this.receiveId = receiveId;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public Integer getContextType() {
        return contextType;
    }

    public void setContextType(Integer contextType) {
        this.contextType = contextType;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Long getSendTime() {
        return sendTime;
    }

    public void setSendTime(Long sendTime) {
        this.sendTime = sendTime;
    }

    public Integer getContextState() {
        return contextState;
    }

    public void setContextState(Integer contextState) {
        this.contextState = contextState;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getReceiveName() {
        return receiveName;
    }

    public void setReceiveName(String receiveName) {
        this.receiveName = receiveName;
    }

    public String getVerification() {
        return verification;
    }

    public void setVerification(String verification) {
        this.verification = verification;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getFriendId() {
        return friendId;
    }

    public void setFriendId(Integer friendId) {
        this.friendId = friendId;
    }
}
