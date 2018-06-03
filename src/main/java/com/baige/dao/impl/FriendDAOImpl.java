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

import java.util.List;

public class FriendDAOImpl extends BaseDAOImpl<FriendsEntity> implements FriendDAO {

    @Override
    public FriendsEntity searchFriend(int userId, int friendId) throws SqlException {
        FriendsEntity friend = null;
        Session session = HibernateUtil.getSession(); // 生成session实例
        Transaction tx = session.beginTransaction(); // 创建transaction实例
        try {
            // 对应的“name” 是Bean中的字段名而不是数据库中表内的字段名
            Criteria criteria = session.createCriteria(FriendsEntity.class).add(
                    Restrictions.and(Restrictions.eq(FriendDAO.USER_ID, userId),
                            Restrictions.eq(FriendDAO.FRIEND_ID, friendId)));
            friend = (FriendsEntity) criteria.uniqueResult();
            tx.commit(); // 提交事务;
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback(); // 回滚事务
            throw new SqlException("sql execute fail !");
        } finally {
            session.close();
        }
        return friend;
    }

    @Override
    public FriendView searchFriendView(int userId, int friendId) throws SqlException {
        FriendView friendView = null;
        Session session = HibernateUtil.getSession(); // 生成session实例
        Transaction tx = session.beginTransaction(); // 创建transaction实例
        try {
            Criteria criteria = session.createCriteria(FriendView.class).add(Restrictions.eq(FriendDAO.USER_ID, userId)).add(Restrictions.eq(FriendDAO.FRIEND_ID, friendId));
            friendView = (FriendView) criteria.uniqueResult();
            tx.commit(); // 提交事务;
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback(); // 回滚事务
            throw new SqlException("sql execute fail !");
        } finally {
            session.close();
        }
        return friendView;
    }

    @Override
    public FriendView searchFriendById(int id) throws SqlException {
        FriendView friendView = null;
        Session session = HibernateUtil.getSession(); // 生成session实例
        Transaction tx = session.beginTransaction(); // 创建transaction实例
        try {
            Criteria criteria = session.createCriteria(FriendView.class).add(Restrictions.eq(FriendDAO.ID, id));
            friendView = (FriendView) criteria.uniqueResult();
            tx.commit(); // 提交事务;
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback(); // 回滚事务
            throw new SqlException("sql execute fail !");
        } finally {
            session.close();
        }
        return friendView;
    }

    @Override
    public List<FriendView> searchFriend(int uid) throws SqlException {
        List<FriendView> list;
        Session session = HibernateUtil.getSession(); // 生成session实例
        Transaction tx = session.beginTransaction(); // 创建transaction实例
        try {
            Criteria criteria = session.createCriteria(FriendView.class).add(Restrictions.eq(FriendDAO.USER_ID, uid));
            list = criteria.list();
            tx.commit(); // 提交事务;
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback(); // 回滚事务
            throw new SqlException("sql execute fail !");
        } finally {
            session.close();
        }
        return list;
    }

    @Override
    public boolean changFriendAlias(int id, int uid, String alias) throws SqlException {
        int count = 0;
        Session session = HibernateUtil.getSession(); // 生成session实例
        Transaction tx = session.beginTransaction(); // 创建transaction实例
        try {
            Query ql = session.createQuery("update FriendsEntity f set f.friendAlias =:alias  where f.id = :id and f.userId = :uid");
            ql.setString("alias", alias);
            ql.setInteger("id", id);
            ql.setInteger("uid", uid);
            count = ql.executeUpdate();
            tx.commit(); // 提交事务;
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback(); // 回滚事务
            throw new SqlException("sql execute fail !");
        } finally {
            session.close();
        }
        return count == 1;
    }

    @Override
    public FriendsEntity relateUser(int userId, int friendId) throws SqlException {
        FriendsEntity ownFriendsEntity = null;
        FriendsEntity friendsEntity = null;
        Session session = HibernateUtil.getSession(); // 生成session实例
        Transaction tx = session.beginTransaction(); // 创建transaction实例
        try {
            ownFriendsEntity = searchFriend(userId, friendId);
            friendsEntity = searchFriend(friendId, userId);
            if (ownFriendsEntity == null || friendsEntity == null) {
                if (ownFriendsEntity == null) {
                    ownFriendsEntity = new FriendsEntity();
                    ownFriendsEntity.setUserId(userId);
                    ownFriendsEntity.setFriendId(friendId);
                    ownFriendsEntity.setRelateTime(System.currentTimeMillis());
                    ownFriendsEntity.setState(State.RELATETION_WAITING);
                    ownFriendsEntity.setReadState(State.READ_STATE);
                    session.save(ownFriendsEntity);
                }else{
                    ownFriendsEntity.setRelateTime(System.currentTimeMillis());
                    ownFriendsEntity.setState(State.RELATETION_WAITING);
                    ownFriendsEntity.setReadState(State.READ_STATE);
                    session.update(ownFriendsEntity);
                }
                if (friendsEntity == null) {
                    friendsEntity = new FriendsEntity();
                    friendsEntity.setUserId(friendId);
                    friendsEntity.setFriendId(userId);
                    friendsEntity.setRelateTime(System.currentTimeMillis());
                    friendsEntity.setState(State.RELATETION_BEADDED);
                    friendsEntity.setReadState(State.UNREAD_STATE);
                    session.save(friendsEntity);
                }else{
                    friendsEntity.setRelateTime(System.currentTimeMillis());
                    friendsEntity.setState(State.RELATETION_BEADDED);
                    friendsEntity.setReadState(State.UNREAD_STATE);
                    session.update(friendsEntity);
                }
            } else if (ownFriendsEntity.getState() == State.RELATETION_STRANGE
                    && friendsEntity.getState() == State.RELATETION_STRANGE) {
                ownFriendsEntity.setRelateTime(System.currentTimeMillis());
                ownFriendsEntity.setState(State.RELATETION_WAITING);
                ownFriendsEntity.setReadState(State.READ_STATE);
                session.update(ownFriendsEntity);

                friendsEntity.setRelateTime(System.currentTimeMillis());
                friendsEntity.setState(State.RELATETION_BEADDED);
                friendsEntity.setReadState(State.UNREAD_STATE);
                session.update(friendsEntity);
            }else{
                System.out.println("错误状态 relateUser() own friendState :"+ ownFriendsEntity.getState()+ ", friend State ="+friendsEntity.getState());
            }
            tx.commit(); // 提交事务;
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback(); // 回滚事务
            throw new SqlException("sql execute fail !");
        } finally {
            session.close();
        }
        return ownFriendsEntity;
    }

    @Override
    public FriendsEntity agreeFriend(int id, int userId, int friendId) throws SqlException {
        FriendsEntity ownFriendsEntity = null;
        FriendsEntity friendsEntity = null;
        Session session = HibernateUtil.getSession(); // 生成session实例
        Transaction tx = session.beginTransaction(); // 创建transaction实例
        //TODO 不需要id
        try {
            ownFriendsEntity = searchFriend(userId, friendId);
            friendsEntity = searchFriend(friendId, userId);
            if (ownFriendsEntity == null || friendsEntity == null) {
                    System.out.println("错误状态 agreeFriend() 不存在该条记录"+ownFriendsEntity + ", "+friendsEntity );
            } else if (ownFriendsEntity.getState() == State.RELATETION_BEADDED
                    && friendsEntity.getState() == State.RELATETION_WAITING) {
                ownFriendsEntity.setState(State.RELATETION_FRIEND);
                ownFriendsEntity.setReadState(State.READ_STATE);
                session.update(ownFriendsEntity);

                friendsEntity.setState(State.RELATETION_FRIEND);
                friendsEntity.setReadState(State.UNREAD_STATE);
                session.update(friendsEntity);
            }else{
                System.out.println("错误状态 agreeFriend() own friendState :"+ ownFriendsEntity.getState()+ ", friend State ="+friendsEntity.getState());
            }
            tx.commit(); // 提交事务;
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback(); // 回滚事务
            throw new SqlException("sql execute fail !");
        } finally {
            session.close();
        }
        return ownFriendsEntity;
    }

    @Override
    public FriendsEntity rejectFriend(int id, int userId, int friendId) throws SqlException {
        FriendsEntity ownFriendsEntity = null;
        FriendsEntity friendsEntity = null;
        Session session = HibernateUtil.getSession(); // 生成session实例
        Transaction tx = session.beginTransaction(); // 创建transaction实例
        //TODO 不需要id
        try {
            ownFriendsEntity = searchFriend(userId, friendId);
            friendsEntity = searchFriend(friendId, userId);
            if (ownFriendsEntity == null || friendsEntity == null) {
                System.out.println("错误状态 rejectFriend() 不存在该条记录"+ownFriendsEntity + ", "+friendsEntity );
            } else if (ownFriendsEntity.getState() == State.RELATETION_BEADDED
                    && friendsEntity.getState() == State.RELATETION_WAITING) {
                ownFriendsEntity.setState(State.RELATETION_STRANGE);
                ownFriendsEntity.setReadState(State.READ_STATE);
                session.update(ownFriendsEntity);

                friendsEntity.setState(State.RELATETION_STRANGE);
                friendsEntity.setReadState(State.UNREAD_STATE);
                session.update(friendsEntity);
            }else{
                System.out.println("错误状态 rejectFriend() own friendState :"+ ownFriendsEntity.getState()+ ", friend State ="+friendsEntity.getState());
            }
            tx.commit(); // 提交事务;
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback(); // 回滚事务
            throw new SqlException("sql execute fail !");
        } finally {
            session.close();
        }
        return ownFriendsEntity;
    }

    @Override
    public FriendsEntity deFriend(int id, int userId, int friendId) throws SqlException {
        FriendsEntity ownFriendsEntity = null;
        FriendsEntity friendsEntity = null;
        Session session = HibernateUtil.getSession(); // 生成session实例
        Transaction tx = session.beginTransaction(); // 创建transaction实例
        //TODO 不需要id
        try {
            ownFriendsEntity = searchFriend(userId, friendId);
            friendsEntity = searchFriend(friendId, userId);
            if (ownFriendsEntity == null || friendsEntity == null) {
                if (ownFriendsEntity == null) {
                    ownFriendsEntity = new FriendsEntity();
                    ownFriendsEntity.setUserId(userId);
                    ownFriendsEntity.setFriendId(friendId);
                    ownFriendsEntity.setRelateTime(System.currentTimeMillis());
                    ownFriendsEntity.setState(State.RELATETION_DEFRIEND);
                    ownFriendsEntity.setReadState(State.READ_STATE);
                    session.save(ownFriendsEntity);
                }else{
                    ownFriendsEntity.setRelateTime(System.currentTimeMillis());
                    ownFriendsEntity.setState(State.RELATETION_DEFRIEND);
                    ownFriendsEntity.setReadState(State.READ_STATE);
                    session.update(ownFriendsEntity);
                }
                if (friendsEntity == null) {
                    friendsEntity = new FriendsEntity();
                    friendsEntity.setUserId(friendId);
                    friendsEntity.setFriendId(userId);
                    friendsEntity.setRelateTime(System.currentTimeMillis());
                    friendsEntity.setState(State.RELATETION_BEDEFRIEND);
                    friendsEntity.setReadState(State.READ_STATE);
                    session.save(friendsEntity);
                }else{
                    friendsEntity.setRelateTime(System.currentTimeMillis());
                    friendsEntity.setState(State.RELATETION_BEDEFRIEND);
                    friendsEntity.setReadState(State.READ_STATE);
                    session.update(friendsEntity);
                }
            } else if (ownFriendsEntity.getState() == State.RELATETION_STRANGE
                    && friendsEntity.getState() == State.RELATETION_STRANGE) {
                ownFriendsEntity.setState(State.RELATETION_DEFRIEND);
                ownFriendsEntity.setReadState(State.READ_STATE);
                session.update(ownFriendsEntity);

                friendsEntity.setState(State.RELATETION_BEDEFRIEND);
                friendsEntity.setReadState(State.READ_STATE);
                session.update(friendsEntity);
            }else{
                System.out.println("错误状态 deFriend() own friendState :"+ ownFriendsEntity.getState()+ ", friend State ="+friendsEntity.getState());
            }
            tx.commit(); // 提交事务;
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback(); // 回滚事务
            throw new SqlException("sql execute fail !");
        } finally {
            session.close();
        }
        return ownFriendsEntity;
    }

    @Override
    public FriendsEntity deleteFriend(int id, int userId, int friendId) throws SqlException {
        FriendsEntity ownFriendsEntity = null;
        FriendsEntity friendsEntity = null;
        Session session = HibernateUtil.getSession(); // 生成session实例
        Transaction tx = session.beginTransaction(); // 创建transaction实例
        //TODO 不需要id
        try {
            ownFriendsEntity = searchFriend(userId, friendId);
            friendsEntity = searchFriend(friendId, userId);
            if (ownFriendsEntity == null || friendsEntity == null) {
                System.out.println("错误状态 deleteFriend() 不存在该条记录"+ownFriendsEntity + ", "+friendsEntity );
            } else if (ownFriendsEntity.getState() == State.RELATETION_FRIEND
                    && friendsEntity.getState() == State.RELATETION_FRIEND) {
                ownFriendsEntity.setState(State.RELATETION_STRANGE);
                ownFriendsEntity.setReadState(State.READ_STATE);
                session.update(ownFriendsEntity);

                friendsEntity.setState(State.RELATETION_STRANGE);
                friendsEntity.setReadState(State.UNREAD_STATE);
                session.update(friendsEntity);
            }else{
                System.out.println("错误状态 deleteFriend() own friendState :"+ ownFriendsEntity.getState()+ ", friend State ="+friendsEntity.getState());
            }
            tx.commit(); // 提交事务;
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback(); // 回滚事务
            throw new SqlException("sql execute fail !");
        } finally {
            session.close();
        }
        return ownFriendsEntity;
    }

    @Override
    public FriendsEntity cancelDefriend(int id, int userId, int friendId) throws SqlException {
        FriendsEntity ownFriendsEntity = null;
        FriendsEntity friendsEntity = null;
        Session session = HibernateUtil.getSession(); // 生成session实例
        Transaction tx = session.beginTransaction(); // 创建transaction实例
        //TODO 不需要id
        try {
            ownFriendsEntity = searchFriend(userId, friendId);
            friendsEntity = searchFriend(friendId, userId);
            if (ownFriendsEntity == null || friendsEntity == null) {
                System.out.println("错误状态 cancelDefriend() 不存在该条记录"+ownFriendsEntity + ", "+friendsEntity );
            } else if (ownFriendsEntity.getState() == State.RELATETION_DEFRIEND
                    && friendsEntity.getState() == State.RELATETION_BEDEFRIEND) {
                ownFriendsEntity.setState(State.RELATETION_STRANGE);
                ownFriendsEntity.setReadState(State.READ_STATE);
                session.update(ownFriendsEntity);

                friendsEntity.setState(State.RELATETION_STRANGE);
                friendsEntity.setReadState(State.UNREAD_STATE);
                session.update(friendsEntity);
            }else{
                System.out.println("错误状态 deleteFriend() own friendState :"+ ownFriendsEntity.getState()+ ", friend State ="+friendsEntity.getState());
            }
            tx.commit(); // 提交事务;
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback(); // 回滚事务
            throw new SqlException("sql execute fail !");
        } finally {
            session.close();
        }
        return ownFriendsEntity;
    }

    @Override
    public List<FriendsEntity> getUnRead(int userId) throws SqlException {
        return null;
    }
}
