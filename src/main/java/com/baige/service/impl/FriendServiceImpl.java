package com.baige.service.impl;

import com.baige.dao.impl.FriendDAOImpl;
import com.baige.service.IFriendService;

import java.util.Map;

public class FriendServiceImpl implements IFriendService {

    private FriendDAOImpl friendDAO;

    public FriendDAOImpl getFriendDAO() {
        if (friendDAO == null){
            friendDAO = new FriendDAOImpl();
        }
        return friendDAO;
    }

    @Override
    public void searchFriends(int uid, Map<String, Object> responseMsgMap) {

    }

    @Override
    public void changFriendAlias(int id, int uid, String alias, Map<String, Object> responseMsgMap) {

    }

    @Override
    public void relateUser(int uid, int friendId, Map<String, Object> responseMsgMap) {

    }

    @Override
    public void agreeFriend(int id, int uid, int friendId, Map<String, Object> responseMsgMap) {

    }

    @Override
    public void rejectFriend(int id, int uid, int friendId, Map<String, Object> responseMsgMap) {

    }

    @Override
    public void deleteFriend(int id, int uid, int friendId, Map<String, Object> responseMsgMap) {

    }

    @Override
    public void deFriend(int id, int uid, int friendId, Map<String, Object> responseMsgMap) {

    }
}
