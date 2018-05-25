package com.baige.dao;

import com.baige.exception.SqlException;
import com.baige.pojo.FriendView;
import com.baige.pojo.FriendsEntity;

import java.util.List;

public interface FriendDAO {

    String ID = "id";
    String USER_ID = "userId";
    String FRIEND_ID = "friendId";
    String FRIEND_ALIAS = "friendAlias";
    String RELATE_TIME = "relateTime";
    String STATE = "state";
    String READ_STATE = "readState";
    String REMARK = "remark";

    FriendsEntity searchFriend(int userId, int friendId) throws SqlException; // 这俩id位置无关

    List<FriendView> searchFriend(int uid) throws SqlException;

    boolean changFriendAlias(int id, int uid, String alias) throws SqlException;

    FriendsEntity relateUser(int userId, int friendId) throws SqlException;

    FriendsEntity agreeFriend(int id, int userId, int friendId) throws SqlException;

    FriendsEntity rejectFriend(int id, int userId, int friendId) throws SqlException;

    FriendsEntity deFriend(int id, int userId, int friendId)throws SqlException;

    FriendsEntity deleteFriend(int id, int userId, int friendId)throws SqlException;

    List<FriendsEntity> getUnRead(int userId)throws SqlException;
}
