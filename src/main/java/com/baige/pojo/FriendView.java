package com.baige.pojo;

import java.util.Objects;

public class FriendView {
    private int id;
    private Integer userId;
    private Integer friendId;
    private String name;
    private String alias;
    private String friendAlias;
    private Long relateTime;
    private Integer state;
    private Integer readState;
    private String remark;
    private String imgName;
    private String deviceId;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
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

    public String getImgName() {
        return imgName;
    }

    public void setImgName(String imgName) {
        this.imgName = imgName;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FriendView that = (FriendView) o;
        return id == that.id &&
                Objects.equals(userId, that.userId) &&
                Objects.equals(friendId, that.friendId) &&
                Objects.equals(name, that.name) &&
                Objects.equals(alias, that.alias) &&
                Objects.equals(friendAlias, that.friendAlias) &&
                Objects.equals(relateTime, that.relateTime) &&
                Objects.equals(state, that.state) &&
                Objects.equals(readState, that.readState) &&
                Objects.equals(remark, that.remark) &&
                Objects.equals(imgName, that.imgName) &&
                Objects.equals(deviceId, that.deviceId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, userId, friendId, name, alias, friendAlias, relateTime, state, readState, remark, imgName, deviceId);
    }
}
