package com.baige.service.impl;

import com.baige.common.Parm;
import com.baige.common.State;
import com.baige.dao.impl.FriendDAOImpl;
import com.baige.exception.SqlException;
import com.baige.pojo.FriendView;
import com.baige.pojo.FriendsEntity;
import com.baige.service.IFriendService;

import java.util.List;
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
        try {
            List<FriendView> friendViews = getFriendDAO().searchFriend(uid);
            responseMsgMap.put(Parm.CODE, Parm.CODE_SUCCESS);
            responseMsgMap.put(Parm.MEAN, "查询好友成功");
            responseMsgMap.put(Parm.FRIENDS, friendViews);
        } catch (SqlException e) {
            e.printStackTrace();
            responseMsgMap.put(Parm.CODE, Parm.CODE_FAIL);
            responseMsgMap.put(Parm.MEAN, e.getMessage());
        }
    }

    @Override
    public void changFriendAlias(int id, int uid, String alias, Map<String, Object> responseMsgMap) {
        try {
            boolean update = getFriendDAO().changFriendAlias(id, uid, alias);
            if (update) {
                responseMsgMap.put(Parm.CODE, Parm.CODE_SUCCESS);
                responseMsgMap.put(Parm.MEAN, "更改好友备注成功");
            } else {
                responseMsgMap.put(Parm.CODE, Parm.CODE_FAIL);
                responseMsgMap.put(Parm.MEAN, "更改好友备注失败");
            }
        } catch (SqlException e) {
            e.printStackTrace();
            responseMsgMap.put(Parm.CODE, Parm.CODE_FAIL);
            responseMsgMap.put(Parm.MEAN, e.getMessage());
        }
    }

    @Override
    public void relateUser(int uid, int friendId, Map<String, Object> responseMsgMap) {
        try {
            FriendsEntity friend = getFriendDAO().relateUser(uid, friendId);
            if (friend != null && friend.getState() == State.RELATETION_WAITING) {
                responseMsgMap.put(Parm.CODE, Parm.CODE_SUCCESS);
                responseMsgMap.put(Parm.MEAN, "等待对方同意");
                responseMsgMap.put(Parm.DATA, friend);
            } else {
                responseMsgMap.put(Parm.CODE, Parm.CODE_FAIL);
                responseMsgMap.put(Parm.MEAN, "未知错误");
            }
        } catch (SqlException e) {
            e.printStackTrace();
            responseMsgMap.put(Parm.CODE, Parm.CODE_FAIL);
            responseMsgMap.put(Parm.MEAN, e.getMessage());
        }
    }

    @Override
    public void agreeFriend(int id, int uid, int friendId, Map<String, Object> responseMsgMap) {
        try {
            FriendsEntity friend = getFriendDAO().agreeFriend(id, uid, friendId);
            if (friend != null && friend.getState() == State.RELATETION_FRIEND) {
                responseMsgMap.put(Parm.CODE, Parm.CODE_SUCCESS);
                responseMsgMap.put(Parm.MEAN, "添加好友成功");
                responseMsgMap.put(Parm.DATA, friend);
            } else {
                responseMsgMap.put(Parm.CODE, Parm.CODE_FAIL);
                responseMsgMap.put(Parm.MEAN, "未知错误");
            }
        } catch (SqlException e) {
            e.printStackTrace();
            responseMsgMap.put(Parm.CODE, Parm.CODE_FAIL);
            responseMsgMap.put(Parm.MEAN, e.getMessage());
        }
    }

    @Override
    public void rejectFriend(int id, int uid, int friendId, Map<String, Object> responseMsgMap) {
        try {
            FriendsEntity friend = getFriendDAO().rejectFriend(id, uid, friendId);
            if (friend != null && friend.getState() == State.RELATETION_STRANGE) {
                responseMsgMap.put(Parm.CODE, Parm.CODE_SUCCESS);
                responseMsgMap.put(Parm.MEAN, "操作成功");
                responseMsgMap.put(Parm.DATA, friend);
            } else {
                responseMsgMap.put(Parm.CODE, Parm.CODE_FAIL);
                responseMsgMap.put(Parm.MEAN, "未知错误");
            }
        } catch (SqlException e) {
            e.printStackTrace();
            responseMsgMap.put(Parm.CODE, Parm.CODE_FAIL);
            responseMsgMap.put(Parm.MEAN, e.getMessage());
        }
    }

    @Override
    public void deleteFriend(int id, int uid, int friendId, Map<String, Object> responseMsgMap) {
        try {
            FriendsEntity friend = getFriendDAO().deleteFriend(id, uid, friendId);
            if (friend != null && friend.getState() == State.RELATETION_STRANGE) {
                responseMsgMap.put(Parm.CODE, Parm.CODE_SUCCESS);
                responseMsgMap.put(Parm.MEAN, "操作成功");
                responseMsgMap.put(Parm.DATA, friend);
            } else {
                responseMsgMap.put(Parm.CODE, Parm.CODE_FAIL);
                responseMsgMap.put(Parm.MEAN, "未知错误");
            }
        } catch (SqlException e) {
            e.printStackTrace();
            responseMsgMap.put(Parm.CODE, Parm.CODE_FAIL);
            responseMsgMap.put(Parm.MEAN, e.getMessage());
        }
    }

    @Override
    public void deFriend(int id, int uid, int friendId, Map<String, Object> responseMsgMap) {
        try {
            FriendsEntity friend = getFriendDAO().deFriend(id, uid, friendId);
            if (friend != null && friend.getState() == State.RELATETION_DEFRIEND) {
                responseMsgMap.put(Parm.CODE, Parm.CODE_SUCCESS);
                responseMsgMap.put(Parm.MEAN, "操作成功");
                responseMsgMap.put(Parm.DATA, friend);
            } else {
                responseMsgMap.put(Parm.CODE, Parm.CODE_FAIL);
                responseMsgMap.put(Parm.MEAN, "未知错误");
            }
        } catch (SqlException e) {
            e.printStackTrace();
            responseMsgMap.put(Parm.CODE, Parm.CODE_FAIL);
            responseMsgMap.put(Parm.MEAN, e.getMessage());
        }
    }

    @Override
    public void cancelDefriend(int id, int uid, int friendId, Map<String, Object> responseMsgMap) {
        try {
            FriendsEntity friend = getFriendDAO().cancelDefriend(id, uid, friendId);
            if (friend != null && friend.getState() == State.RELATETION_STRANGE) {
                responseMsgMap.put(Parm.CODE, Parm.CODE_SUCCESS);
                responseMsgMap.put(Parm.MEAN, "操作成功");
                responseMsgMap.put(Parm.DATA, friend);
            } else {
                responseMsgMap.put(Parm.CODE, Parm.CODE_FAIL);
                responseMsgMap.put(Parm.MEAN, "未知错误");
            }
        } catch (SqlException e) {
            e.printStackTrace();
            responseMsgMap.put(Parm.CODE, Parm.CODE_FAIL);
            responseMsgMap.put(Parm.MEAN, e.getMessage());
        }
    }
}
