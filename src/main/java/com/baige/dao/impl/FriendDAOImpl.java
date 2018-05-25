package com.baige.dao.impl;


import com.baige.common.State;
import com.baige.dao.FriendDAO;
import com.baige.exception.SqlException;
import com.baige.pojo.FriendView;
import com.baige.pojo.FriendsEntity;
import com.baige.util.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import java.util.ArrayList;
import java.util.List;

public class FriendDAOImpl extends BaseDAOImpl<FriendsEntity> implements FriendDAO {

    @Override
    public FriendsEntity searchFriend(int userId, int friendId) throws SqlException {
        return null;
    }

    @Override
    public List<FriendView> searchFriend(int uid) throws SqlException {
        return null;
    }

    @Override
    public boolean changFriendAlias(int id, int uid, String alias) throws SqlException {
        return false;
    }

    @Override
    public FriendsEntity relateUser(int userId, int friendId) throws SqlException {
        return null;
    }

    @Override
    public FriendsEntity agreeFriend(int id, int userId, int friendId) throws SqlException {
        return null;
    }

    @Override
    public FriendsEntity rejectFriend(int id, int userId, int friendId) throws SqlException {
        return null;
    }

    @Override
    public FriendsEntity deFriend(int id, int userId, int friendId) throws SqlException {
        return null;
    }

    @Override
    public FriendsEntity deleteFriend(int id, int userId, int friendId) throws SqlException {
        return null;
    }

    @Override
    public List<FriendsEntity> getUnRead(int userId) throws SqlException {
        return null;
    }
}
