package com.baige.dao.impl;

import com.baige.common.State;
import com.baige.dao.ChatMessageDAO;
import com.baige.exception.SqlException;
import com.baige.pojo.ChatMessageEntity;
import com.baige.util.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class ChatMessageDAOImpl extends BaseDAOImpl<ChatMessageEntity> implements ChatMessageDAO {


    @Override
    public List<ChatMessageEntity> findMsgRelate(int uid, int friendId) throws SqlException {
        List<ChatMessageEntity> list = null;
        Session session = HibernateUtil.getSession(); // 生成session实例
        Transaction tx = session.beginTransaction(); // 创建transaction实例
        try {
            // 对应的“name” 是Bean中的字段名而不是数据库中表内的字段名
            Criteria criteria = session.createCriteria(ChatMessageEntity.class);
            criteria.add(Restrictions.or(
                    Restrictions.and(Restrictions.eq(ChatMessageDAO.SENDER_ID, uid), Restrictions.eq(ChatMessageDAO.RECEIVE_ID, friendId)),
                    Restrictions.and(Restrictions.eq(ChatMessageDAO.SENDER_ID, friendId), Restrictions.eq(ChatMessageDAO.RECEIVE_ID, uid))));
            criteria.addOrder(Order.desc(ChatMessageDAO.SEND_TIME)).setMaxResults(100);
            list = criteria.list();
            tx.commit(); // 提交事务;
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback(); // 回滚事务
            throw new SqlException(e.getMessage());
        } finally {
            session.close();
        }
        return list;
    }

    @Override
    public List<ChatMessageEntity> findMsgRelateAfterTime(int uid, int friendId, long time) throws SqlException {
        List<ChatMessageEntity> list = null;
        Session session = HibernateUtil.getSession(); // 生成session实例
        Transaction tx = session.beginTransaction(); // 创建transaction实例
        try {
            // 对应的“name” 是Bean中的字段名而不是数据库中表内的字段名
            Criteria criteria = session.createCriteria(ChatMessageEntity.class);
            criteria.add(Restrictions.or(
                    Restrictions.and(Restrictions.eq(ChatMessageDAO.SENDER_ID, uid), Restrictions.eq(ChatMessageDAO.RECEIVE_ID, friendId)),
                    Restrictions.and(Restrictions.eq(ChatMessageDAO.SENDER_ID, friendId), Restrictions.eq(ChatMessageDAO.RECEIVE_ID, uid))));
            criteria.add(Restrictions.gt(ChatMessageDAO.SEND_TIME, time));
            criteria.addOrder(Order.desc(ChatMessageDAO.SEND_TIME)).setMaxResults(100);
            list = criteria.list();
            tx.commit(); // 提交事务;
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback(); // 回滚事务
            throw new SqlException(e.getMessage());
        } finally {
            session.close();
        }
        return list;
    }

    @Override
    public List<ChatMessageEntity> findMsgRelateBeforeTime(int uid, int friendId, long time) throws SqlException {
        List<ChatMessageEntity> list = null;
        Session session = HibernateUtil.getSession(); // 生成session实例
        Transaction tx = session.beginTransaction(); // 创建transaction实例
        try {
            // 对应的“name” 是Bean中的字段名而不是数据库中表内的字段名
            Criteria criteria = session.createCriteria(ChatMessageEntity.class);
            criteria.add(Restrictions.or(
                    Restrictions.and(Restrictions.eq(ChatMessageDAO.SENDER_ID, uid), Restrictions.eq(ChatMessageDAO.RECEIVE_ID, friendId)),
                    Restrictions.and(Restrictions.eq(ChatMessageDAO.SENDER_ID, friendId), Restrictions.eq(ChatMessageDAO.RECEIVE_ID, uid))));
            criteria.add(Restrictions.lt(ChatMessageDAO.SEND_TIME, time));
            criteria.addOrder(Order.desc(ChatMessageDAO.SEND_TIME)).setMaxResults(100);
            list = criteria.list();
            tx.commit(); // 提交事务;
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback(); // 回滚事务
            throw new SqlException(e.getMessage());
        } finally {
            session.close();
        }
        return list;
    }

    @Override
    public List<ChatMessageEntity> findMsg(int uid) throws SqlException {
        List<ChatMessageEntity> list = null;
        Session session = HibernateUtil.getSession(); // 生成session实例
        Transaction tx = session.beginTransaction(); // 创建transaction实例
        try {
            // 对应的“name” 是Bean中的字段名而不是数据库中表内的字段名
            Criteria criteria = session.createCriteria(ChatMessageEntity.class);
            criteria.add(Restrictions.or(
                    Restrictions.eq(ChatMessageDAO.SENDER_ID, uid),
                    Restrictions.eq(ChatMessageDAO.RECEIVE_ID, uid)) );
            criteria.addOrder(Order.desc(ChatMessageDAO.SEND_TIME)).setMaxResults(100);
            list = criteria.list();
            tx.commit(); // 提交事务;
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback(); // 回滚事务
            throw new SqlException(e.getMessage());
        } finally {
            session.close();
        }
        return list;
    }

    @Override
    public List<ChatMessageEntity> findMsgAfterTime(int uid, long time) throws SqlException {
        List<ChatMessageEntity> list = null;
        Session session = HibernateUtil.getSession(); // 生成session实例
        Transaction tx = session.beginTransaction(); // 创建transaction实例
        try {
            // 对应的“name” 是Bean中的字段名而不是数据库中表内的字段名
            Criteria criteria = session.createCriteria(ChatMessageEntity.class);
            criteria.add(Restrictions.or(
                    Restrictions.eq(ChatMessageDAO.SENDER_ID, uid),
                    Restrictions.eq(ChatMessageDAO.RECEIVE_ID, uid)) );
            criteria.add(Restrictions.gt(ChatMessageDAO.SEND_TIME, time));
            criteria.addOrder(Order.desc(ChatMessageDAO.SEND_TIME)).setMaxResults(100);
            list = criteria.list();
            tx.commit(); // 提交事务;
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback(); // 回滚事务
            throw new SqlException(e.getMessage());
        } finally {
            session.close();
        }
        return list;
    }

    @Override
    public List<ChatMessageEntity> findMsgBeforeTime(int uid, long time) throws SqlException {
        List<ChatMessageEntity> list = null;
        Session session = HibernateUtil.getSession(); // 生成session实例
        Transaction tx = session.beginTransaction(); // 创建transaction实例
        try {
            // 对应的“name” 是Bean中的字段名而不是数据库中表内的字段名
            Criteria criteria = session.createCriteria(ChatMessageEntity.class);
            criteria.add(Restrictions.or(
                    Restrictions.eq(ChatMessageDAO.SENDER_ID, uid),
                    Restrictions.eq(ChatMessageDAO.RECEIVE_ID, uid)) );
            criteria.add(Restrictions.lt(ChatMessageDAO.SEND_TIME, time));
            criteria.addOrder(Order.desc(ChatMessageDAO.SEND_TIME)).setMaxResults(100);
            list = criteria.list();
            tx.commit(); // 提交事务;
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback(); // 回滚事务
            throw new SqlException(e.getMessage());
        } finally {
            session.close();
        }
        return list;
    }

    @Override
    public boolean readBeforeTime(int uid, long time) throws SqlException {
        int count = 0;
        Session session = HibernateUtil.getSession(); // 生成session实例
        Transaction tx = session.beginTransaction(); // 创建transaction实例
        try {
            // 对应的“name” 是Bean中的字段名而不是数据库中表内的字段名
            Query q = session.createQuery("update ChatMessageEntity c set c.contextState  =:state  where (c.senderId = :uid or c.receiveId = :uid) and c.sendTime <= :time");
            q.setInteger("state", State.READ_STATE);
            q.setInteger("uid", uid);
            q.setLong("time", time);
            count = q.executeUpdate();
            tx.commit(); // 提交事务;
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback(); // 回滚事务
            throw new SqlException(e.getMessage());
        } finally {
            session.close();
        }
        return count > 0;
    }

    @Override
    public boolean readBeforeTime(int uid, int friendId, long time) throws SqlException {
        int count = 0;
        Session session = HibernateUtil.getSession(); // 生成session实例
        Transaction tx = session.beginTransaction(); // 创建transaction实例
        try {
            // 对应的“name” 是Bean中的字段名而不是数据库中表内的字段名
            Query q = session.createQuery("update ChatMessageEntity c set c.contextState  =:state  where ((c.senderId = :uid and c.receiveId = :friendId) or (c.senderId = :friendId and c.receiveId = :uid)) and c.sendTime <= :time");
            q.setInteger("state", State.READ_STATE);
            q.setInteger("uid", uid);
            q.setInteger("friendId", friendId);
            q.setLong("time", time);
            count = q.executeUpdate();
            tx.commit(); // 提交事务;
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback(); // 回滚事务
            throw new SqlException(e.getMessage());
        } finally {
            session.close();
        }
        return count > 0;
    }
}
