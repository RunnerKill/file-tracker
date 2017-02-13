package com.sowell.file.tracker;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.sowell.file.enums.FileType;
import com.sowell.file.model.ExcelDTO;
import com.sowell.file.model.FileDTO;
import com.sowell.file.model.ImageDTO;
import com.sowell.file.model.WordDTO;
import com.sowell.file.util.HttpUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 文件服务器交互工具类
 * @author Xiaojie.Xu
 */
public class FileTracker extends BaseTracker {

    /**
     * 构造方法
     * @param server 服务器地址
     * @param webdir 文件web目录
     * @param domain 文件服务项目名
     */
    public FileTracker(String server, String webdir, String domain) {
        super(server, webdir, domain);
    }

    /**
     * 同步服务器上的缓存(Cache)与数据库(DB)
     * <br>注：请在处理业务表单请求时调用。
     * @param request 当前request对象
     * @return 同步结果对象
     */
    public List<FileDTO> synchronize(HttpServletRequest request) {
        String key = request.getParameter("CACHE_KEY");
        return synchronize(key);
    }

    /**
     * 同步服务器上的缓存(Cache)与数据库(DB)
     * <br>注：请在处理业务表单请求时调用。
     * @param cacheId 服务器端缓存的id
     * @return 同步结果对象
     */
    public List<FileDTO> synchronize(String cacheId) {
        String url = httpUrl + "/cache/synchronize";
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("key", cacheId);
        String jsonStr = HttpUtil.getString(url, map);
        List<FileDTO> list = jsonToFiles(jsonStr);
        return list != null && list.size() > 0 ? list : null;
    }

    /**
     * 批量上传
     * <br>注：直接通过HttpUrlConnection创建post请求上传实体文件(java.io.File)对象。
     * @param files 文件列表
     * @param project 项目名
     * @param module 模块名
     * @return 文件对象列表
     */
    public List<FileDTO> upload(List<File> files, String project, String module) {
        String url = httpUrl + "/file/upload";
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("project", project);
        params.put("module", module);
        String jsonStr = HttpUtil.upload(url, params, files);
        List<FileDTO> list = jsonToFiles(jsonStr);
        return list != null && list.size() > 0 ? list : null;
    }

    /**
     * 单文件上传
     * <br>注：直接通过HttpUrlConnection创建post请求上传实体文件(java.io.File)对象。
     * @param file 文件
     * @param project 项目名
     * @param module 文件组
     * @return 文件对象
     */
    public FileDTO upload(File file, String project, String module) {
        List<File> files = new ArrayList<File>();
        files.add(file);
        List<FileDTO> list = upload(files, project, module);
        return list != null && list.size() > 0 ? list.get(0) : null;
    }

    /**
     * 根据id取单个文件
     * @param id 文件id
     * @return 文件对象
     */
    public FileDTO getById(String id) {
        String url = httpUrl + "/file/getById";
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("id", id);
        String jsonStr = HttpUtil.getString(url, params);
        return jsonToFile(jsonStr);
    }

    /**
     * 根据id列表取文件列表
     * @param idArray 文件id列表
     * @return 业务下所有文件对象
     */
    public List<FileDTO> getByIds(List<String> idArray) {
        String url = httpUrl + "/file/getByIds";
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("idArray", idArray);
        String jsonStr = HttpUtil.getString(url, params);
        return jsonToFiles(jsonStr);
    }

    /**
     * 根据id删除文件
     * @param id 文件id
     * @return 删除结果
     */
    public boolean deleteById(String id) {
        String url = httpUrl + "/file/deleteById";
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("id", id);
        String jsonStr = HttpUtil.getString(url, params);
        JSONObject jb = JSONObject.fromObject(jsonStr);
        if(!parseJson(jsonStr)) return false;
        return jb.getInt("data") > 0;
    }

    /**
     * 删除业务下所有文件
     * @param idArray 文件id列表
     * @return 删除结果
     */
    public boolean deleteByIds(List<String> idArray) {
        String url = httpUrl + "/file/deleteByIds";
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("idArray", idArray);
        String jsonStr = HttpUtil.getString(url, params);
        JSONObject jb = JSONObject.fromObject(jsonStr);
        if(!parseJson(jsonStr)) return false;
        return jb.getInt("data") > 0;
    }

    /**
     * 根据id下载文件
     * @param id 文件id
     * @param path 要保存的目录（绝对物理路径）
     *        <br>注：可用request.getSession().getServletContext().getRealPath("/")获取项目所在目录
     * @return 实体文件(java.io.File)对象
     */
    public File download(String id, String path) {
        String url = httpUrl + "/file/download";
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("id", id);
        HttpUtil.download(url, params, path);
        return new File(path);
    }

    private List<FileDTO> jsonToFiles(String jsonStr) {
        if(!parseJson(jsonStr)) return null;
        JSONObject jb = JSONObject.fromObject(jsonStr);
        return createFiles(jb.getJSONArray("data"));
    }

    private FileDTO jsonToFile(String jsonStr) {
        if(parseJson(jsonStr)) return null;
        JSONObject jb = JSONObject.fromObject(jsonStr);
        return createFile(jb.getJSONObject("data"));
    }
    
    private List<FileDTO> createFiles(JSONArray array) {
        List<FileDTO> files = new ArrayList<FileDTO>();
        for(int i = 0; i < array.size(); i++) {
            FileDTO file = createFile(array.getJSONObject(i));
            files.add(file);
        }
        return files.size() > 0 ? files : null;
    }

    private FileDTO createFile(JSONObject obj) {
        FileType type = FileType.getType(obj.getString("ftype"));
        if(type == FileType.IMAGE)
            return new ImageDTO(fileUrl, obj);
        if(type == FileType.WORD)
            return new WordDTO(fileUrl, obj);
        if(type == FileType.EXCEL)
            return new ExcelDTO(fileUrl, obj);
        return new FileDTO(fileUrl, obj);
    }
}
