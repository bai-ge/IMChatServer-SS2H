package com.baige.service;

import com.baige.pojo.UsersEntity;

import java.util.Map;

public interface IUserService {

    void login(UsersEntity user, Map<String, Object> responseMsgMap);

    void register(UsersEntity user, Map<String, Object> responseMsgMap);

    boolean checkNameClash(String name);

    UsersEntity checkUser(int id, String verification);

    void updateAlias(UsersEntity user, Map<String, Object> responseMsgMap);

    boolean updateHeadImgName(int id, String headImg);

    void searchUserBykeyword(String keyword, Map<String, Object> responseMsgMap);

}
