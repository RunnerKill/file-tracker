package com.sowell.file.result;

import java.util.List;

import com.sowell.file.model.FileDTO;

/**
 * 同步结果类
 * @author Xiaojie.Xu
 */
public class SyncResult extends Result {

    private List<FileDTO> insertFiles;

    private List<FileDTO> deleteFiles;

    /**
     * @return 同步过程中数据库插入的文件列表
     */
    public List<FileDTO> getInsertFiles() {
        return insertFiles;
    }

    /**
     * 设置
     * @param insertFiles 文件列表
     */
    public void setInsertFiles(List<FileDTO> insertFiles) {
        this.insertFiles = insertFiles;
    }

    /**
     * @return 同步过程中数据库删除的文件列表
     */
    public List<FileDTO> getDeleteFiles() {
        return deleteFiles;
    }

    /**
     * 设置
     * @param deleteFiles 文件列表
     */
    public void setDeleteFiles(List<FileDTO> deleteFiles) {
        this.deleteFiles = deleteFiles;
    }
}
