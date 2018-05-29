package com.baige.pojo;

public class FilesEntity {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FilesEntity that = (FilesEntity) o;

        if (id != that.id) return false;
        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;
        if (fileName != null ? !fileName.equals(that.fileName) : that.fileName != null) return false;
        if (filePath != null ? !filePath.equals(that.filePath) : that.filePath != null) return false;
        if (fileType != null ? !fileType.equals(that.fileType) : that.fileType != null) return false;
        if (fileSize != null ? !fileSize.equals(that.fileSize) : that.fileSize != null) return false;
        if (fileDescribe != null ? !fileDescribe.equals(that.fileDescribe) : that.fileDescribe != null) return false;
        if (uploadTime != null ? !uploadTime.equals(that.uploadTime) : that.uploadTime != null) return false;
        if (downloadCount != null ? !downloadCount.equals(that.downloadCount) : that.downloadCount != null)
            return false;
        if (remark != null ? !remark.equals(that.remark) : that.remark != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (fileName != null ? fileName.hashCode() : 0);
        result = 31 * result + (filePath != null ? filePath.hashCode() : 0);
        result = 31 * result + (fileType != null ? fileType.hashCode() : 0);
        result = 31 * result + (fileSize != null ? fileSize.hashCode() : 0);
        result = 31 * result + (fileDescribe != null ? fileDescribe.hashCode() : 0);
        result = 31 * result + (uploadTime != null ? uploadTime.hashCode() : 0);
        result = 31 * result + (downloadCount != null ? downloadCount.hashCode() : 0);
        result = 31 * result + (remark != null ? remark.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "FilesEntity{" +
                "id=" + id +
                ", userId=" + userId +
                ", fileName='" + fileName + '\'' +
                ", filePath='" + filePath + '\'' +
                ", fileType=" + fileType +
                ", fileSize=" + fileSize +
                ", fileDescribe='" + fileDescribe + '\'' +
                ", uploadTime=" + uploadTime +
                ", downloadCount=" + downloadCount +
                ", remark='" + remark + '\'' +
                '}';
    }
}
