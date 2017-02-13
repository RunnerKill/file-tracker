package com.sowell.file.model;

import java.io.Serializable;

import com.google.gson.Gson;
import com.sowell.file.Constants;
import com.sowell.file.enums.FileType;

import net.sf.json.JSONObject;

/**
 * 附件传输对象
 * @author Xiaojie.Xu
 */
public class FileDTO implements Serializable, Constants {
    
    private static final long serialVersionUID = FileDTO.class.hashCode();

    private String id;

    private String name;
    
    private String encodeName;

    private String ext;
    
    private FileType type;

    private String remarks;

    private String size;

    private String path;

    private String relativePath;
    
    private Long saveTime;

    private Long uploadTime;

    private String project;

    private String module;

    /**
     * @return 数据库主键
     */
    public String getId() {
        return id;
    }
    
    /**
     * @return 原文件名
     */
    public String getName() {
        return name;
    }
    
    /**
     * @return UTF-8编码文件名（防中文乱码）
     */
    public String getEncodeName() {
        return encodeName;
    }

    /**
     * @return 后缀名
     */
    public String getExt() {
        return ext;
    }

    /**
     * @return 备注
     */
    public String getRemarks() {
        return remarks;
    }

    /**
     * @return 文件大小
     */
    public String getSize() {
        return size;
    }

    /**
     * @return 文件的Web绝对路径，如：http://ip:port/dir.../file.ext
     */
    public String getPath() {
        return path;
    }

    /**
     * @return 文件在服务器上的相对路径，如：dir.../file.ext
     */
    public String getRelativePath() {
        return relativePath;
    }

    /**
     * @return 插入数据库的时间
     */
    public Long getSaveTime() {
        return saveTime;
    }

    /**
     * @return 上传至服务器的时间
     */
    public Long getUploadTime() {
        return uploadTime;
    }

    /**
     * @return 项目名
     */
    public String getProject() {
        return project;
    }

    /**
     * @return 模块名
     */
    public String getModule() {
        return module;
    }
    
    /**
     * @return 类型
     */
    public FileType getType() {
        return type;
    }

    /**
     * 返回Json格式字符串
     * @return string as json
     */
    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
    
    /**
     * 用Json数据构造对象
     * @param webDir - file's web dir path
     * @param obj - string as json
     */
    public FileDTO(String webDir, JSONObject obj) {
        super();
        if(obj.has("id")) id = obj.getString("id");
        if(obj.has("name")) name = obj.getString("name");
        if(obj.has("encodeName")) encodeName = obj.getString("encodeName");
        if(obj.has("ext")) ext = obj.getString("ext");
        if(obj.has("remarks")) remarks = obj.getString("remarks");
        if(obj.has("ftype")) type = FileType.getType(obj.getString("ftype"));
        if(obj.has("size")) size = obj.getString("size");
        if(obj.has("path")) {
            relativePath = obj.getString("path");
            path = webDir + "/" + relativePath;
        }
        if(obj.has("project")) project = obj.getString("project");
        if(obj.has("module")) module = obj.getString("module");
        if(obj.has("uploadTime")) uploadTime = obj.getLong("uploadTime");
    }
    
}
