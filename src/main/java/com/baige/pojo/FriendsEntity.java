package com.baige.pojo;

import java.util.Objects;

public class FriendsEntity {
    private int id;
    private Integer userId;
    private Integer friendId;
    private String friendAlias;
    private Long relateTime;
    private Integer state;
    private Integer readState;
    private String remark;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FriendsEntity that = (FriendsEntity) o;
        return id == that.id &&
                Objects.equals(userId, that.userId) &&
                Objects.equals(friendId, that.friendId) &&
                Objects.equals(friendAlias, that.friendAlias) &&
                Objects.equals(relateTime, that.relateTime) &&
                Objects.equals(state, that.state) &&
                Objects.equals(readState, that.readState) &&
                Objects.equals(remark, that.remark);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, userId, friendId, friendAlias, relateTime, state, readState, remark);
    }
}
