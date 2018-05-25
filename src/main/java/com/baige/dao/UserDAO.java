package com.baige.dao;


import com.baige.exception.SqlException;
import com.baige.pojo.UsersEntity;

import java.util.List;

public interface UserDAO {

    String TAB_NAME = "users";
    String ID = "id";
    String NAME = "name";
    String PASSWORD = "password";
    String ALIAS = "alias";
    String DEVICE_ID = "deviceId";
    String LOGIN_TIME = "loginTime";
    String LOGIN_IP = "loginIp";
    String REGISTER_TIME = "registerTime";
    String VERIFICATION = "verification";
    String IMG_NAME = "imgName";
    String REMARK = "remark";

    UsersEntity getIdByName(String name) throws SqlException;
    List<UsersEntity> searchUserByName(String name) throws SqlException;
    UsersEntity updateAliasByIdAndVer(int id, String verification, String alias) throws SqlException;
    UsersEntity searchUserByNameAndPassword(String name, String password) throws SqlException;
    UsersEntity searchUserByIdAndVerification(int id, String verification) throws SqlException;
    boolean updateHeadImgById(int id, String headImgName) throws SqlException;
    List<UsersEntity> searchUserBykeyword(String keyword) throws SqlException;
}
