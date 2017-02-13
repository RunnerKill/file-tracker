package com.sowell.file.model;

import net.sf.json.JSONObject;

public class ExcelDTO extends FileDTO {

    private static final long serialVersionUID = ExcelDTO.class.hashCode();

    private String previewPath;

    private String version;

    /**
     * @return 预览路径
     */
    public String getPreviewPath() {
        return previewPath;
    }

    /**
     * @return 版本
     */
    public String getVersion() {
        return version;
    }

    public ExcelDTO(String webDir, JSONObject obj) {
        super(webDir, obj);
        if(obj.has("previewPath")) previewPath = obj.getString("previewPath");
        if(obj.has("version")) version = obj.getString("version");
    }

}
