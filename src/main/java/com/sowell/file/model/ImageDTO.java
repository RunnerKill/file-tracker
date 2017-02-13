package com.sowell.file.model;

import net.sf.json.JSONObject;

/**
 * 图片传输对象
 * @author Xiaojie.Xu
 */
public class ImageDTO extends FileDTO {

    private static final long serialVersionUID = ImageDTO.class.hashCode();

    private int width;

    private int height;

    private String orientation;

    /**
     * @return 原始宽度
     */
    public int getWidth() {
        return width;
    }

    /**
     * @return 原始高度
     */
    public int getHeight() {
        return height;
    }
    
    /**
     * @return 原始方向
     */
    public String getOrientation() {
        return orientation;
    }

    public ImageDTO(String webDir, JSONObject obj) {
        super(webDir, obj);
        if(obj.has("width")) width = obj.getInt("width");
        if(obj.has("height")) height = obj.getInt("height");
        if(obj.has("orientation")) orientation = obj.getString("orientation");
    }

}
