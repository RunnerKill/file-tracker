package com.sowell.file.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 回调代理请求响应类
 * @author Xiaojie.Xu
 */
public class FileServlet extends HttpServlet {

    private static final long serialVersionUID=1L;

    /**
     * 处理回调请求
     *  <br>act=init 初始化
     *  <br>act=upload 上传
     *  <br>act=preview 预览
     *  <br>act=delete 删除
     *  <br>act=download 下载
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String act = request.getParameter("act");
        String json = request.getParameter("json");
        String param = json == null ? "" : json;
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.print("<script type=\"text/javascript\">\nwindow.parent.swfile.callback('" + act + "', " + param + ", window);\n</script>");
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        this.doGet(request, response);
    }
}
