package com.baige.service;

import com.baige.pojo.FilesEntity;

import java.util.Map;

public interface IFileService {

    void saveFile(FilesEntity fileEntity, Map<String, Object> responseMsgMap);

    void searchAllFile(Map<String, Object> responseMsgMap);

    void addDownloadCount(int fid);

    void deleteFile(int fid, int uid, Map<String, Object> responseMsgMap);
}
