package com.baige.service.impl;

import com.baige.common.Parm;
import com.baige.dao.impl.UserDAOImpl;
import com.baige.exception.SqlException;
import com.baige.pojo.UsersEntity;
import com.baige.service.IUserService;
import com.baige.util.Tools;

import java.util.List;
import java.util.Map;

public class UserServiceImpl implements IUserService {

    private UserDAOImpl userDAO;

    public UserDAOImpl getUserDAO() {
        if (userDAO == null){
            userDAO = new UserDAOImpl();
        }
        return userDAO;
    }

    /**
     * @param name
     * @return isClash
     */
    public  boolean checkNameClash(String name) {
        UsersEntity user;
        try {
            user = getUserDAO().getIdByName(name);
            if (user != null) {
                return true;
            }
        } catch (SqlException e) {
            e.printStackTrace();
        }
        return false;
    }


    @Override
    public void login(UsersEntity user, Map<String, Object> responseMsgMap) {
        try {
            UsersEntity updateUser = getUserDAO().searchUserByNameAndPassword(user.getName(), user.getPassword());
            if (updateUser != null) {
                //通知被迫下线的设备
                if (!Tools.isEquals(updateUser.getDeviceId(), user.getDeviceId()) &&
                        !Tools.isEmpty(updateUser.getVerification())) {
                    //被迫下线
                    denyOfService(updateUser.getDeviceId(), updateUser.getVerification());
                }
                updateUser.setLoginTime(user.getLoginTime());
                updateUser.setDeviceId(user.getDeviceId());
                updateUser.setVerification(Tools.randomVerification());
                updateUser.setLoginIp(user.getLoginIp());
                getUserDAO().doUpdate(updateUser);
                responseMsgMap.put(Parm.CODE, Parm.CODE_SUCCESS);
                responseMsgMap.put(Parm.MEAN, "登录成功");
                responseMsgMap.put(Parm.USER, updateUser);
            } else {
                responseMsgMap.put(Parm.CODE, Parm.CODE_FAIL);
                responseMsgMap.put(Parm.MEAN, "用户不存在或密码错误");
            }
        } catch (SqlException e) {
            e.printStackTrace();
            responseMsgMap.put(Parm.CODE, Parm.CODE_FAIL);
            responseMsgMap.put(Parm.MEAN, e.getMessage());
        }
    }

    @Override
    public void register(UsersEntity user, Map<String, Object> responseMsgMap) {
        if (!checkNameClash(user.getName())) {
            // 插入数据
            try {
                getUserDAO().doSave(user);
                responseMsgMap.put(Parm.CODE, Parm.CODE_SUCCESS);
                responseMsgMap.put(Parm.MEAN, "注册成功");
                responseMsgMap.put(Parm.USER, user);
            } catch (SqlException e) {
                e.printStackTrace();
                responseMsgMap.put(Parm.CODE, Parm.CODE_FAIL);
                responseMsgMap.put(Parm.MEAN, e.getMessage());
            }
        } else {
            responseMsgMap.put(Parm.CODE, Parm.CODE_FAIL);
            responseMsgMap.put(Parm.MEAN, "用户已经存在");
        }
    }

    @Override
    public UsersEntity checkUser(int id, String verification) {
        try {
            UsersEntity user = getUserDAO().searchUserByIdAndVerification(id, verification);
            return user;
        } catch (SqlException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void updateAlias(UsersEntity user, Map<String, Object> responseMsgMap) {
        try {
            user = getUserDAO().updateAliasByIdAndVer(user.getId(), user.getVerification(), user.getAlias());
            if (user != null) {
                responseMsgMap.put(Parm.CODE, Parm.CODE_SUCCESS);
                responseMsgMap.put(Parm.MEAN, "更新成功");
            } else {
                responseMsgMap.put(Parm.CODE, Parm.CODE_FAIL);
                responseMsgMap.put(Parm.MEAN, "更新失败");
            }
        } catch (SqlException e) {
            e.printStackTrace();
            responseMsgMap.put(Parm.CODE, Parm.CODE_FAIL);
            responseMsgMap.put(Parm.MEAN, "更新失败");
        }
    }

    @Override
    public boolean updateHeadImgName(int id, String headImg) {
        boolean res = false;
        try {
            res = getUserDAO().updateHeadImgById(id, headImg);
        } catch (SqlException e) {
            e.printStackTrace();
        }
        return res;
    }

    @Override
    public void searchUserBykeyword(String keyword, Map<String, Object> responseMsgMap) {
        try {
            List<UsersEntity> list = getUserDAO().searchUserBykeyword(keyword);
            if (list != null && !list.isEmpty()) {
                responseMsgMap.put(Parm.CODE, Parm.CODE_SUCCESS);
                responseMsgMap.put(Parm.USERS, list);
            } else {
                responseMsgMap.put(Parm.CODE, Parm.CODE_NOTFIND);
                responseMsgMap.put(Parm.MEAN, "未找到");
            }
        } catch (SqlException e) {
            e.printStackTrace();
            responseMsgMap.put(Parm.CODE, Parm.CODE_FAIL);
            responseMsgMap.put(Parm.MEAN, e.getMessage());
        }
    }

    /**
     * 取消设备deviceId 的验证码 verification, 即被迫下线
     *
     * @param deviceId
     * @param verification
     */
    public  void denyOfService(String deviceId, String verification) {
        //TODO
    }
}
