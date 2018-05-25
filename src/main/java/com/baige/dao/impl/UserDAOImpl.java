package com.baige.dao.impl;

import com.baige.dao.UserDAO;
import com.baige.exception.SqlException;
import com.baige.pojo.UsersEntity;
import com.baige.util.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl extends BaseDAOImpl<UsersEntity> implements UserDAO {

    @Override
    public UsersEntity getIdByName(String name) throws SqlException {
       UsersEntity user  = null;
        Session session = HibernateUtil.getSession(); // 生成session实例
        Transaction tx = session.beginTransaction(); // 创建transaction实例
        try {
            // 对应的“name” 是Bean中的字段名而不是数据库中表内的字段名
            Criteria criteria = session.createCriteria(UsersEntity.class).add(Restrictions.eq(UserDAO.NAME,name));
            user = (UsersEntity) criteria.uniqueResult();
            tx.commit(); // 提交事务;
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback(); // 回滚事务
            throw new SqlException("sql execute fail !");
        } finally {
           session.close();
        }
        return user;
    }

    @Override
    public List<UsersEntity> searchUserByName(String name) throws SqlException {
        List<UsersEntity> list = new ArrayList<UsersEntity>();
        Session session = HibernateUtil.getSession(); // 生成session实例
        Transaction tx = session.beginTransaction(); // 创建transaction实例
        try {
            // 对应的“name” 是Bean中的字段名而不是数据库中表内的字段名
            Criteria criteria =session.createCriteria(UsersEntity.class).add(Restrictions.eq(UserDAO.NAME,name));
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
    public UsersEntity searchUserByNameAndPassword(String name, String password) throws SqlException {
        UsersEntity user = null;
        Session session = HibernateUtil.getSession(); // 生成session实例
        Transaction tx = session.beginTransaction(); // 创建transaction实例
        try {
            // 对应的“name” 是Bean中的字段名而不是数据库中表内的字段名
            Criteria criteria =session.createCriteria(UsersEntity.class).add(
                    Restrictions.and(Restrictions.eq(UserDAO.NAME, name),
                            Restrictions.eq(UserDAO.PASSWORD, password)));
            user = (UsersEntity) criteria.uniqueResult();
            tx.commit(); // 提交事务;
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback(); // 回滚事务
            throw new SqlException("sql execute fail !");
        } finally {
            session.close();
        }
        return user;
    }

    @Override
    public UsersEntity updateAliasByIdAndVer(int id, String verification, String alias) throws SqlException {
        UsersEntity user = null;
        Session session = HibernateUtil.getSession(); // 生成session实例
        Transaction tx = session.beginTransaction(); // 创建transaction实例
        try {
            Query q = session.createQuery("update UsersEntity u set u.alias  =:alias  where u.id = :id and u.verification = :verification");
            q.setString("alias", alias);
            q.setInteger("id", id);
            q.setString("verification", verification);
            q.executeUpdate();
            Criteria criteria =session.createCriteria(UsersEntity.class).add(
                    Restrictions.and(Restrictions.eq(UserDAO.ID, id),
                            Restrictions.eq(UserDAO.VERIFICATION, verification)));
            user = (UsersEntity) criteria.uniqueResult();
            tx.commit(); // 提交事务;
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback(); // 回滚事务
            throw new SqlException("sql execute fail !");
        } finally {
            session.close();
        }
        return user;
    }

    @Override
    public UsersEntity searchUserByIdAndVerification(int id, String verification) throws SqlException {
        UsersEntity user = null;
        Session session = HibernateUtil.getSession(); // 生成session实例
        Transaction tx = session.beginTransaction(); // 创建transaction实例
        try {
            // 对应的“name” 是Bean中的字段名而不是数据库中表内的字段名
            Criteria criteria =session.createCriteria(UsersEntity.class).add(
                    Restrictions.and(Restrictions.eq(UserDAO.ID, id),
                            Restrictions.eq(UserDAO.VERIFICATION, verification)));
            user = (UsersEntity) criteria.uniqueResult();
            tx.commit(); // 提交事务;
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback(); // 回滚事务
            throw new SqlException("sql execute fail !");
        } finally {
            session.close();
        }
        return user;
    }

    @Override
    public boolean updateHeadImgById(int id, String headImgName) throws SqlException {
        Session session = HibernateUtil.getSession(); // 生成session实例
        Transaction tx = session.beginTransaction(); // 创建transaction实例
        int count = 0;
        try {
            Query q = session.createQuery("update UsersEntity u set u.imgName  =:img  where u.id = :id");
            q.setString("img", headImgName);
            q.setInteger("id", id);
            count = q.executeUpdate();
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
    public List<UsersEntity> searchUserBykeyword(String keyword) throws SqlException {
        List<UsersEntity> list = new ArrayList<UsersEntity>();
        Session session = HibernateUtil.getSession(); // 生成session实例
        Transaction tx = session.beginTransaction(); // 创建transaction实例
        try {
            Criteria criteria = session.createCriteria(UsersEntity.class).add(Restrictions.or(Restrictions.like(UserDAO.NAME, "%"+keyword+"%"), Restrictions.like(UserDAO.ALIAS, "%"+keyword+"%")));
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
}
