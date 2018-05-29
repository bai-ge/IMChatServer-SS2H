package com.baige.service.impl;

import com.baige.common.Parm;
import com.baige.dao.impl.FileDAOImpl;
import com.baige.exception.SqlException;
import com.baige.pojo.FilesEntity;
import com.baige.service.IFileService;

import java.util.List;
import java.util.Map;

public class FileServiceImpl implements IFileService {

    FileDAOImpl fileDAO;

    public FileDAOImpl getFileDAO() {
        if(fileDAO == null){
            fileDAO = new FileDAOImpl();
        }
        return fileDAO;
    }

    @Override
    public void saveFile(FilesEntity fileEntity, Map<String, Object> responseMsgMap) {
        try{
            getFileDAO().doSave(fileEntity);
            responseMsgMap.put(Parm.CODE, Parm.CODE_SUCCESS);
            responseMsgMap.put(Parm.MEAN, "文件分享成功");
        }catch (SqlException e){
            e.printStackTrace();
            responseMsgMap.put(Parm.CODE, Parm.CODE_FAIL);
            responseMsgMap.put(Parm.MEAN, e.getMessage());
        }
    }

    @Override
    public void searchAllFile(Map<String, Object> responseMsgMap) {
        try{
            List<FilesEntity> fileEntities = getFileDAO().doFindAll();
            if(fileEntities != null && fileEntities.size() > 0){
                responseMsgMap.put(Parm.CODE, Parm.CODE_SUCCESS);
                responseMsgMap.put(Parm.MEAN, "查询成功");
                responseMsgMap.put(Parm.FILES, fileEntities);
            }else{
                responseMsgMap.put(Parm.CODE, Parm.CODE_NOTFIND);
                responseMsgMap.put(Parm.MEAN, "未找到");
            }
        }catch (SqlException e){
            e.printStackTrace();
            responseMsgMap.put(Parm.CODE, Parm.CODE_FAIL);
            responseMsgMap.put(Parm.MEAN, e.getMessage());
        }
    }
}
