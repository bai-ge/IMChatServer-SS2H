package com.baige.util;


import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


/**
 *
 */
public class HibernateUtil {

    private static SessionFactory sessionFactory;

    // 使用静态代码块初始化 Hibernate
    static {
        try {

            Configuration configuration = new Configuration().configure(); // 读取配置文件 hibernate.cfg.xml
            sessionFactory = configuration.buildSessionFactory();    // 创建 SesstionFactory
        } catch (Throwable ex) {
            ex.printStackTrace();
            throw new ExceptionInInitializerError(ex);
        }
    }

    // 获得SesstionFactory实例
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    // 获得ThreadLocal对象管理的Session实例
    public static Session getSession() throws HibernateException {
        Session session = null;
        if (sessionFactory == null) {
            rebuildSesstionFactory();
        }
        // 通过SessionFactory对象创建session对象
        session = (sessionFactory != null) ? sessionFactory.openSession() : null;
        return session;
    }


    // 重建SessionFactory
    public static void rebuildSesstionFactory() {
        try {
            Configuration configuration = new Configuration().configure(); // 读取配置文件 hibernate.cfg.xml
            sessionFactory = configuration.buildSessionFactory();    // 创建 SesstionFactory
        } catch (Exception e) {
            System.err.println("Error Creating SessionFactory");
            e.printStackTrace();
        }
    }

    // 关闭缓存和连接池
    public static void shutdown() {
        getSessionFactory().close();
    }
}
