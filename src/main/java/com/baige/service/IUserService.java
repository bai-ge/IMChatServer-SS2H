package com.baige.service;

import com.baige.pojo.UsersEntity;

import java.util.Map;

public interface IUserService {

     void login(UsersEntity user, Map<String, Object> responseMsgMap);

    void register(UsersEntity user, Map<String, Object> responseMsgMap);

}
