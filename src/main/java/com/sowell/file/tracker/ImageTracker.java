package com.sowell.file.tracker;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sowell.file.util.HttpUtil;

/**
 * 图片交互工具类
 * @author Xiaojie.Xu
 */
public class ImageTracker extends BaseTracker {

    /**
     * 构造方法
     * @param server 服务器地址
     * @param webdir 文件web目录
     * @param domain 附件服务项目名
     */
    public ImageTracker(String server, String webdir, String domain) {
        super(server, webdir, domain);
    }

    /**
     * 缩放
     * @param id 图片id
     * @param width 目标宽度
     * @param height 目标高度
     * @return 缩放结果
     */
    public boolean scale(String id, int width, int height) {
        String url = httpUrl + "/image/scale";
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("id", id);
        params.put("width", width);
        params.put("height", height);
        String jsonStr = HttpUtil.getString(url, params);
        return parseJson(jsonStr);
    }
    
    /**
     * 批量缩放（批量规格）
     * @param ids 图片id列表
     * @param widths 目标宽度列表
     * @param heights 目标高度列表
     * @return 缩放结果
     */
    public boolean scaleMultiply(List<String> ids, List<Integer> widths, List<Integer> heights) {
        String url = httpUrl + "/image/scaleMultiply";
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("ids", ids);
        params.put("widths", widths);
        params.put("heights", heights);
        String jsonStr = HttpUtil.getString(url, params);
        return parseJson(jsonStr);
    }

    /**
     * 裁剪
     * @param id 图片id
     * @param x 左上角横坐标
     * @param y 左上角纵坐标
     * @param width 区域宽度
     * @param height 区域高度
     * @return 裁剪结果
     */
    public boolean cut(String id, int x, int y, int width, int height) {
        String url = httpUrl + "/image/cut";
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("id", id);
        params.put("x", x);
        params.put("y", y);
        params.put("width", width);
        params.put("height", height);
        String jsonStr = HttpUtil.getString(url, params);
        return parseJson(jsonStr);
    }

    /**
     * 旋转至正方向（针对移动设备拍摄的照片）
     * @param id 图片id
     * @return 旋转结果
     */
    public boolean rotate(String id) {
        String url = httpUrl + "/image/rotate";
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("id", id);
        String jsonStr = HttpUtil.getString(url, params);
        return parseJson(jsonStr);
    }

}
