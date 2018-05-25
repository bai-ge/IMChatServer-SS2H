package com.baige.service;

import java.util.Map;

public interface IFriendService {

    void searchFriends(int uid, Map<String, Object> responseMsgMap);

    void changFriendAlias(int id, int uid, String alias, Map<String, Object> responseMsgMap);

    void relateUser(int uid, int friendId, Map<String, Object> responseMsgMap);

    void agreeFriend(int id, int uid, int friendId, Map<String, Object> responseMsgMap);

    void rejectFriend(int id, int uid, int friendId, Map<String, Object> responseMsgMap);

    void deleteFriend(int id, int uid, int friendId, Map<String, Object> responseMsgMap);

    void deFriend(int id, int uid, int friendId, Map<String, Object> responseMsgMap);
}
