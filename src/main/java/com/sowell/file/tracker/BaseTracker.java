package com.sowell.file.tracker;

import net.sf.json.JSONObject;

import com.sowell.file.Constants;
import com.sowell.file.util.HttpUtil;

/**
 * 基础Tracker类
 * @author Xiaojie.Xu
 */
public abstract class BaseTracker implements Constants {

    // 请求前缀，如：http://ip:port/file
    protected String httpUrl;

    // 文件前缀，如：http://ip:port/fileDir
    protected String fileUrl;

    /**
     * 构造方法
     * @param server 服务器地址
     * @param webdir 文件web目录
     * @param domain 项目名
     */
    protected BaseTracker(String server, String webdir, String domain) {
        super();
        this.httpUrl = server + "/" + domain;
        String url = this.httpUrl + "/file/getFileDir";
        String jsonStr = HttpUtil.getString(url, null);
        String dir = null;
        if(parseJson(jsonStr)) {
            JSONObject jb = JSONObject.fromObject(jsonStr);
            dir = jb.getString("data");
        }
        this.fileUrl = webdir + "/" + dir + "/";
    }

    /**
     * 解析服务器端返回的json串
     * @param jsonStr 服务器端返回结果
     * @return http请求是否成功
     */
    protected boolean parseJson(String jsonStr) {
        JSONObject jb = JSONObject.fromObject(jsonStr);
        if(jb.getInt("status") == STATUS_SUC) {
            return true;
        }
        if(jb.getInt("status") == STATUS_FAD) {
            System.out.println("request failed: " + jb.getString("msg"));
            return false;
        }
        if(jb.getInt("status") == STATUS_ERR) {
            System.err.println("server error: " + jb.getString("msg"));
            return false;
        }
        return false;
    }
}
