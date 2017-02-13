package com.sowell.file.util;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;

/**
 * http请求工具类
 * @author xiaojie.Xu
 */
public class HttpUtil {
    
    private static final String BOUNDARY = "------FileTrackerToolKit" + HttpUtil.class.hashCode();

    /**
     * 通过http请求post方式获取返回结果
     * @param urlStr 请求字符串
     * @param params 参数
     * @return 结果字符串
     */
    public static String getString(String urlStr, Map<String, Object> params) {
        URL url = null;
        HttpURLConnection connection = null;
        try {
            url = new URL(urlStr);
            // 初始化连接条件
            connection = (HttpURLConnection)url.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestMethod("POST");
            connection.setUseCaches(false);
//            connection.setRequestProperty("cookie", "JSESSIONID=" + sid);
            connection.connect();
            // 传参数
            writeData(connection.getOutputStream(), params);
            // 获取结果
            return getResult(connection.getInputStream());
        } catch(IOException e) {
            e.printStackTrace();
        } finally {
            if(connection != null) {
                connection.disconnect();
            }
        }
        return null;
    }

    /**
     * 创建http文件表单（可带参数）
     * @param urlStr 请求字符串
     * @param params 参数
     * @param files 要上传的文件
     * @return 结果字符串
     */
    public static String upload(String urlStr, Map<String, Object> params, List<File> files) {
        URL url = null;
        HttpURLConnection connection = null;
        try {
            url = new URL(urlStr);
            // 初始化连接条件
            connection = (HttpURLConnection)url.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setUseCaches(false);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("Charsert", "UTF-8");
            connection.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + BOUNDARY);
            connection.setChunkedStreamingMode(10240); // 指定流的大小，当内容达到这个值的时候就把流输出
            connection.connect();
            // 传文件
            writeData(connection.getOutputStream(), params, files);
            // 获取结果
            return getResult(connection.getInputStream());
        } catch(IOException e) {
            e.printStackTrace();
        } finally {
            if(connection != null) {
                connection.disconnect();
            }
        }
        return null;
    }

    /**
     * 创建http请求进行文件下载
     * @param urlStr 请求字符串
     * @param params 参数
     * @param path 要保存的本地目录（绝对路径）
     * @return 下载结果
     */
    public static boolean download(String urlStr, Map<String, Object> params, String path) {
        URL url = null;
        HttpURLConnection connection = null;
        try {
            url = new URL(urlStr);
            // 初始化连接条件
            connection = (HttpURLConnection)url.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestMethod("POST");
            connection.setUseCaches(false);
            connection.connect();
            // 传参数
            writeData(connection.getOutputStream(), params);
            // 创建文件
            File file = new File(path);
            if(!file.exists()) {
                if(!file.getParentFile().exists()) {
                    file.getParentFile().mkdirs();
                }
                file.createNewFile();
            }
            // 写文件
            FileOutputStream fos = new FileOutputStream(file);
            BufferedInputStream bis = new BufferedInputStream(connection.getInputStream());
            int bytes = 0;
            byte[] buffer = new byte[1024];
            while((bytes = bis.read(buffer)) != -1) {
                fos.write(buffer, 0, bytes);
            }
            fos.close();
            bis.close();
        } catch(IOException e) {
            e.printStackTrace();
        } finally {
            if(connection != null) {
                connection.disconnect();
            }
        }
        return true;
    }

    @SuppressWarnings("rawtypes")
    private static void writeData(OutputStream outputStream, Map<String, Object> params) throws IOException {
        DataOutputStream dos = new DataOutputStream(outputStream);
        if(params != null) {
            String str = "";
            for(String key : params.keySet()) {
                Object obj = params.get(key);
                if(obj == null) {
                    str += "";
                } else if(obj instanceof List) {
                    for(Object o : (List)obj) {
                        str += "&" + key + "=" + o;
                    }
                } else {
                    str += "&" + key + "=" + obj;
                }
            }
            str = str.length() > 1 ? str.substring(1) : str;
            dos.write(str.getBytes());
        }
        dos.flush();
        dos.close();
    }

    private static void writeData(OutputStream outputStream, Map<String, Object> params, List<File> files) throws IOException {
        DataOutputStream dos = new DataOutputStream(outputStream);
        if(params != null) {
            String str = "";
            for(String key : params.keySet()) {
                str += "--" + BOUNDARY + "\r\n"
                    + "Content-Disposition: form-data; name=\"" + key + "\"" + "\r\n"
                    + "\r\n" + params.get(key) + "\r\n";
            }
            dos.write(str.getBytes());
        }
        if(files != null) {
            for(int i = 0; i < files.size(); i++) {
                File f = files.get(i);
                String str = "--" + BOUNDARY + "\r\n"
                    + "Content-Disposition: form-data;name=\"file\";filename=\"" + f.getName() + "\"\r\n"
                    + "Content-Type:application/octet-stream\r\n\r\n";
                dos.write(str.getBytes());
                
                FileInputStream in = new FileInputStream(f);
                byte[] buffer = new byte[in.available()];
                in.read(buffer);
                dos.write(buffer);
//                DataInputStream in = new DataInputStream(new FileInputStream(f));
//                int bytes = 0;
//                byte[] buffer = new byte[1024];
//                while((bytes = in.read(buffer)) != -1) {
//                    dos.write(buffer, 0, bytes);
//                }
                dos.write("\r\n".getBytes()); // 多个文件时，二个文件之间加入这个
                in.close();
            }
        }
        dos.write(("\r\n--" + BOUNDARY + "--\r\n").getBytes());
        dos.flush();
        dos.close();
    }

    private static String getResult(InputStream inputStream) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "utf-8"));
        StringBuffer buffer = new StringBuffer();
        String line = "";
        while((line = reader.readLine()) != null) {
            buffer.append(line);
        }
        reader.close();
        return buffer.toString().trim();
    }
    
}
