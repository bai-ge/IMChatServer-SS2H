package com.baige.pojo;

public class FriendsEntity {
    private int id;
    private Integer userId;
    private Integer friendId;
    private String userAlias;
    private String friendAlias;
    private Long relateTime;
    private Integer state;
    private Integer userReadState;
    private Integer friendReadState;
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

    public String getUserAlias() {
        return userAlias;
    }

    public void setUserAlias(String userAlias) {
        this.userAlias = userAlias;
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

    public Integer getUserReadState() {
        return userReadState;
    }

    public void setUserReadState(Integer userReadState) {
        this.userReadState = userReadState;
    }

    public Integer getFriendReadState() {
        return friendReadState;
    }

    public void setFriendReadState(Integer friendReadState) {
        this.friendReadState = friendReadState;
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

        if (id != that.id) return false;
        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;
        if (friendId != null ? !friendId.equals(that.friendId) : that.friendId != null) return false;
        if (userAlias != null ? !userAlias.equals(that.userAlias) : that.userAlias != null) return false;
        if (friendAlias != null ? !friendAlias.equals(that.friendAlias) : that.friendAlias != null) return false;
        if (relateTime != null ? !relateTime.equals(that.relateTime) : that.relateTime != null) return false;
        if (state != null ? !state.equals(that.state) : that.state != null) return false;
        if (userReadState != null ? !userReadState.equals(that.userReadState) : that.userReadState != null)
            return false;
        if (friendReadState != null ? !friendReadState.equals(that.friendReadState) : that.friendReadState != null)
            return false;
        if (remark != null ? !remark.equals(that.remark) : that.remark != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (friendId != null ? friendId.hashCode() : 0);
        result = 31 * result + (userAlias != null ? userAlias.hashCode() : 0);
        result = 31 * result + (friendAlias != null ? friendAlias.hashCode() : 0);
        result = 31 * result + (relateTime != null ? relateTime.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        result = 31 * result + (userReadState != null ? userReadState.hashCode() : 0);
        result = 31 * result + (friendReadState != null ? friendReadState.hashCode() : 0);
        result = 31 * result + (remark != null ? remark.hashCode() : 0);
        return result;
    }
}
