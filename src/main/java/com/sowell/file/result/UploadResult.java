package com.sowell.file.result;

import java.util.List;
import java.util.Map;

import com.sowell.file.model.FileDTO;

/**
 * 上传结果类
 * @author Xiaojie.Xu
 */
public class UploadResult extends Result {

    private List<FileDTO> files;

    private Map<String, Object> values;

    /**
     * @return 已上传的文件列表
     */
    public List<FileDTO> getFiles() {
        return files;
    }

    /**
     * 设置
     * @param files 文件列表
     */
    public void setFiles(List<FileDTO> files) {
        this.files = files;
    }

    /**
     * @return 上传请求中的其他参数集合
     */
    public Map<String, Object> getValues() {
        return values;
    }

    /**
     * 设置
     * @param values 参数集合
     */
    public void setValues(Map<String, Object> values) {
        this.values = values;
    }

}
