package com.baige.action;


import com.baige.common.Parm;
import com.baige.common.State;
import com.baige.exception.SqlException;
import com.baige.pojo.FilesEntity;
import com.baige.pojo.UsersEntity;
import com.baige.service.impl.FileServiceImpl;
import com.baige.service.impl.UserServiceImpl;
import com.baige.util.Tools;
import org.apache.struts2.ServletActionContext;

import java.io.*;


public class FileAction extends BaseAction {


    private int id;
    private Integer userId;
    private String fileName;
    private String filePath;
    private Integer fileType;
    private Long fileSize;
    private String fileDescribe;
    private Long uploadTime;
    private Integer downloadCount;
    private Integer fileLocation;
    private String remark;


    //封装上传文件域的成员变量
    private File upload;

    //封装上传文件类型的成员变量
    private String uploadContentType;

    //封装上传文件名的属性
    private String uploadFileName;

    private String savePath;

    private int uid;

    private String verification;

    private UserServiceImpl userService;

    private FileServiceImpl fileService;

    //文件下载
    private Integer fn;
    private FilesEntity downloadFile;
    private String downloadFileName;

    public UserServiceImpl getUserService() {
        if (userService == null) {
            userService = new UserServiceImpl();
        }
        return userService;
    }

    public FileServiceImpl getFileService() {
        if (fileService == null) {
            fileService = new FileServiceImpl();
        }
        return fileService;
    }

    private FilesEntity init() {
        FilesEntity fileEntity = new FilesEntity();
        fileEntity.setUserId(getUid());
        fileEntity.setFileName(fileName);
        fileEntity.setFilePath(filePath);
        fileEntity.setFileType(fileType);
        fileEntity.setFileSize(fileSize);
        fileEntity.setFileDescribe(fileDescribe);
        fileEntity.setUploadTime(System.currentTimeMillis());
        fileEntity.setDownloadCount(downloadCount);
        fileEntity.setFileLocation(fileLocation);
        fileEntity.setRemark(remark);
        return fileEntity;
    }

    /**
     * 上传文件到云端
     * @return
     * @throws Exception
     */
    public String uploadFile() throws Exception {
        System.out.println("保存路径：" + getSavePath());
        System.out.println("原始路径：" + getUpload());
        if (getUpload() != null) {
            System.out.println(getUpload().getAbsolutePath());
        }


        File f = new File(getSavePath());
        if (!f.exists()) {
            f.mkdirs();//若文件不存在，则创建
        }


        File saveFile = new File(f, getUpload().getName());
        FileOutputStream fos = new FileOutputStream(saveFile);
        FileInputStream fis = new FileInputStream(getUpload());
        byte[] buffer = new byte[1024];
        int len = 0;
        while ((len = fis.read(buffer)) > 0) {
            fos.write(buffer, 0, len);
        }

        fos.close();
        fis.close();

        //保存进数据库
        FilesEntity entity = init();
        entity.setFileLocation(State.REMOTE);
        entity.setUserId(getUid());
        entity.setFileName(getUploadFileName());
        //TODO 需要改为相对路径，方便数据迁移
        entity.setFilePath(saveFile.getAbsolutePath());
        entity.setDownloadCount(0);
        System.out.println(entity.toString());
        getResponseMsgMap().clear();
        getFileService().saveFile(entity, getResponseMsgMap());
        return SUCCESS;
    }


    /**
     * fn=id;
     * @return
     */
    public String download(){
        if(fn == null){
            return ERROR;
        }
        try {
            System.out.println("下载文件:"+getFn());
            downloadFile = getFileService().getFileDAO().doGetById(getFn());
            downloadFileName = downloadFile.getFileName();
            File file = new File(downloadFile.getFilePath());
            if(!file.exists()){
                downloadFile = null;
                System.out.println("文件不存在");
                return ERROR;
            }
//            getFileService().addDownloadCount(getFn());
        } catch (SqlException e) {
            e.printStackTrace();
        }
        return SUCCESS;
    }

    /**
     * uid
     * verification
     * fn
     * @return
     */
    public String deleteFile(){
        if(!Tools.isEmpty(verification)){
            UsersEntity user = getUserService().checkUser(getUid(), getVerification());
            if(user != null){
                getResponseMsgMap().clear();
                getFileService().deleteFile(getFn(), getUid(), getResponseMsgMap());
            }else{
                getResponseMsgMap().clear();
                getResponseMsgMap().put(Parm.CODE, Parm.CODE_INVALID);
                getResponseMsgMap().put(Parm.MEAN, "验证失败");
            }
        }else{
            getResponseMsgMap().clear();
            getResponseMsgMap().put(Parm.CODE, Parm.CODE_UNKNOWN);
            getResponseMsgMap().put(Parm.MEAN, "参数错误");
        }
        return SUCCESS;
    }

    /**
     * fn=id;
     * @return
     */
    public String updateDownloadCount(){
        getFileService().addDownloadCount(getFn());
        try {
            FilesEntity filesEntity = getFileService().getFileDAO().doGetById(getFn());
            if(filesEntity != null){
                getResponseMsgMap().put(Parm.CODE, Parm.CODE_SUCCESS);
                getResponseMsgMap().put(Parm.FILE, filesEntity);
                getResponseMsgMap().put(Parm.MEAN, "更新成功");
            }else{
                getResponseMsgMap().put(Parm.CODE, Parm.CODE_FAIL);
                getResponseMsgMap().put(Parm.MEAN, "更新失败");
            }
        } catch (SqlException e) {
            e.printStackTrace();
        }
        return SUCCESS;
    }



    public InputStream getDownloadInputStream() throws Exception{
        //获得路径和文件名
        if(downloadFile == null){
            return null;
        }
        System.out.println("downloadFile ="+ downloadFile.getFilePath());
//        return ServletActionContext.getServletContext().getResourceAsStream(file);
        InputStream is = new FileInputStream(downloadFile.getFilePath());
        return is;
    }

    /**
     * @return 查找所有文件
     * @throws Exception
     */
    public String search() throws Exception {
        getResponseMsgMap().clear();
        getFileService().searchAllFile(getResponseMsgMap());
        return SUCCESS;
    }

    /**
     * uid
     * verification
     * @return
     */
    public String share(){
        if(!Tools.isEmpty(verification) ){
            UsersEntity user = getUserService().checkUser(getUid(), getVerification());
            if(user != null){
                getResponseMsgMap().clear();
                FilesEntity filesEntity = init();
                filesEntity.setUserId(getUid());
                filesEntity.setFileLocation(State.LOCAL);
                filesEntity.setUploadTime(System.currentTimeMillis());
                filesEntity.setDownloadCount(0);

                getFileService().saveFile(filesEntity, getResponseMsgMap());
            }else{
                getResponseMsgMap().clear();
                getResponseMsgMap().put(Parm.CODE, Parm.CODE_INVALID);
                getResponseMsgMap().put(Parm.MEAN, "验证失败");
            }
        }else{
            getResponseMsgMap().clear();
            getResponseMsgMap().put(Parm.CODE, Parm.CODE_UNKNOWN);
            getResponseMsgMap().put(Parm.MEAN, "参数错误");
        }
        return SUCCESS;
    }


    public void setSavePath(String value) {
        this.savePath = value;
    }

    public String getSavePath() {
        return ServletActionContext.getServletContext()
                .getRealPath(savePath);
    }

    public void setUpload(File upload) {
        this.upload = upload;
    }

    public File getUpload() {
        return this.upload;
    }

    public void setUploadContentType(String uploadContentType) {
        this.uploadContentType = uploadContentType;
    }

    public String getUploadContentType() {
        return this.uploadContentType;
    }

    public void setUploadFileName(String uploadFileName) {
        this.uploadFileName = uploadFileName;
    }

    public String getUploadFileName() {
        return this.uploadFileName;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public Integer getFileType() {
        return fileType;
    }

    public void setFileType(Integer fileType) {
        this.fileType = fileType;
    }

    public Long getFileSize() {
        return fileSize;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    public String getFileDescribe() {
        return fileDescribe;
    }

    public void setFileDescribe(String fileDescribe) {
        this.fileDescribe = fileDescribe;
    }

    public Long getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Long uploadTime) {
        this.uploadTime = uploadTime;
    }

    public Integer getDownloadCount() {
        return downloadCount;
    }

    public void setDownloadCount(Integer downloadCount) {
        this.downloadCount = downloadCount;
    }

    public Integer getFileLocation() {
        return fileLocation;
    }

    public void setFileLocation(Integer fileLocation) {
        this.fileLocation = fileLocation;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getVerification() {
        return verification;
    }

    public void setVerification(String verification) {
        this.verification = verification;
    }

    public Integer getFn() {
        return fn;
    }

    public void setFn(Integer fn) {
        this.fn = fn;
    }

    public String getDownloadFileName() {
        return downloadFileName;
    }
}
