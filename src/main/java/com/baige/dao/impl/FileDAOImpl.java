package com.baige.dao.impl;

import com.baige.dao.FileDAO;
import com.baige.exception.SqlException;
import com.baige.pojo.FilesEntity;
import com.baige.util.HibernateUtil;
import org.apache.commons.io.monitor.FileEntry;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class FileDAOImpl extends BaseDAOImpl<FilesEntity> implements FileDAO {

    @Override
    public void addDownloadCount(int fid) throws SqlException {

        Session session = HibernateUtil.getSession(); // 生成session实例
        Transaction tx = session.beginTransaction(); // 创建transaction实例
        try {
            // 对应的“name” 是Bean中的字段名而不是数据库中表内的字段名
            Query q = session.createQuery("update FilesEntity f set f.downloadCount  = f.downloadCount + 1  where f.id = :fid ");
            q.setInteger("fid", fid);
            q.executeUpdate();
            tx.commit(); // 提交事务;
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback(); // 回滚事务
            throw new SqlException("sql execute fail !");
        } finally {
            session.close();
        }
    }
}
