package com.baige.action;

import com.baige.common.Parm;
import com.baige.pojo.FriendsEntity;
import com.baige.pojo.UsersEntity;
import com.baige.service.IFriendService;
import com.baige.service.IUserService;
import com.baige.service.impl.FriendServiceImpl;
import com.baige.service.impl.UserServiceImpl;
import com.baige.util.Tools;


public class FriendAction extends BaseAction {

    private int id;
    private Integer userId;
    private Integer friendId;
    private String friendAlias;
    private Long relateTime;
    private Integer state;
    private Integer readState;
    private String remark;


    private FriendsEntity friend;

    private IFriendService friendService;

    private IUserService userService;

    private int uid;
    private String verification;

    private String alias;

    private String operation;


    public IFriendService getFriendService() {
        if(friendService == null){
            friendService = new FriendServiceImpl();
        }
        return friendService;
    }

    public IUserService getUserService() {
        if(userService == null){
            userService = new UserServiceImpl();
        }
        return userService;
    }

    /**
     * uid
     * verification
     * @return
     */
    public String searchFriends(){
        if(!Tools.isEmpty(verification)){
            UsersEntity user = getUserService().checkUser(getUid(), getVerification());
            if(user != null){
                getResponseMsgMap().clear();
                 getFriendService().searchFriends(uid, getResponseMsgMap());
            }else{
                getResponseMsgMap().clear();
                getResponseMsgMap().put(Parm.CODE, Parm.CODE_INVALID);
                getResponseMsgMap().put(Parm.MEAN, "验证失败");
            }
        }else{
            getResponseMsgMap().clear();
            getResponseMsgMap().put(Parm.CODE, Parm.CODE_UNKNOWN);
            getResponseMsgMap().put(Parm.MEAN, "参数错误");
        }
        return SUCCESS;
    }

    /**
     * id
     * uid
     * verification
     * alias
     * @return
     */
    public String changFriendAlias(){
        if(!Tools.isEmpty(verification) && !Tools.isEmpty(getAlias())){
            UsersEntity user = getUserService().checkUser(getUid(), getVerification());
            if(user != null){
                getResponseMsgMap().clear();
                getFriendService().changFriendAlias(id, uid, getAlias(), getResponseMsgMap());
            }else{
                getResponseMsgMap().clear();
                getResponseMsgMap().put(Parm.CODE, Parm.CODE_INVALID);
                getResponseMsgMap().put(Parm.MEAN, "验证失败");
            }
        }else{
            getResponseMsgMap().clear();
            getResponseMsgMap().put(Parm.CODE, Parm.CODE_UNKNOWN);
            getResponseMsgMap().put(Parm.MEAN, "参数错误");
        }
        return SUCCESS;
    }

    /**
     * uid
     * verification
     * friendId
     * @return
     */
    public String relateUser(){
        if(!Tools.isEmpty(verification) ){
            UsersEntity user = getUserService().checkUser(getUid(), getVerification());
            if(user != null){
                getResponseMsgMap().clear();
                getFriendService().relateUser(uid, friendId, getResponseMsgMap());
            }else{
                getResponseMsgMap().clear();
                getResponseMsgMap().put(Parm.CODE, Parm.CODE_INVALID);
                getResponseMsgMap().put(Parm.MEAN, "验证失败");
            }
        }else{
            getResponseMsgMap().clear();
            getResponseMsgMap().put(Parm.CODE, Parm.CODE_UNKNOWN);
            getResponseMsgMap().put(Parm.MEAN, "参数错误");
        }
        return SUCCESS;
    }


    /**
     * id
     * uid
     * verification
     * friendId
     * operation
     * @return
     */
    public String operation(){
        if(!Tools.isEmpty(verification) && !Tools.isEmpty(operation)){
            UsersEntity user = getUserService().checkUser(getUid(), getVerification());
            if(user != null){
                getResponseMsgMap().clear();
                switch (operation){
                    case "add":
                        getFriendService().relateUser(uid, friendId, getResponseMsgMap());
                        break;
                    case "agree":
                        getFriendService().agreeFriend(getId(), getUid(), getFriendId(), getResponseMsgMap());
                        break;
                    case "reject":
                        getFriendService().rejectFriend(getId(), getUid(), getFriendId(), getResponseMsgMap());
                        break;
                    case "delete":
                        getFriendService().deleteFriend(getId(), getUid(), getFriendId(), getResponseMsgMap());
                        break;
                    case "defriend":
                        getFriendService().deFriend(getId(), getUid(), getFriendId(), getResponseMsgMap());
                        break;
                    case "cancel_defriend":
                        getFriendService().cancelDefriend(getId(), getUid(), getFriendId(), getResponseMsgMap());
                        break;
                }

            }else{
                getResponseMsgMap().clear();
                getResponseMsgMap().put(Parm.CODE, Parm.CODE_INVALID);
                getResponseMsgMap().put(Parm.MEAN, "验证失败");
            }
        }else{
            getResponseMsgMap().clear();
            getResponseMsgMap().put(Parm.CODE, Parm.CODE_UNKNOWN);
            getResponseMsgMap().put(Parm.MEAN, "参数错误");
        }
        return SUCCESS;
    }


    /*get and set*/

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getFriendId() {
        return friendId;
    }

    public void setFriendId(Integer friendId) {
        this.friendId = friendId;
    }

    public String getFriendAlias() {
        return friendAlias;
    }

    public void setFriendAlias(String friendAlias) {
        this.friendAlias = friendAlias;
    }

    public Long getRelateTime() {
        return relateTime;
    }

    public void setRelateTime(Long relateTime) {
        this.relateTime = relateTime;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getReadState() {
        return readState;
    }

    public void setReadState(Integer readState) {
        this.readState = readState;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public FriendsEntity getFriend() {
        return friend;
    }

    public void setFriend(FriendsEntity friend) {
        this.friend = friend;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getVerification() {
        return verification;
    }

    public void setVerification(String verification) {
        this.verification = verification;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }
}
