package com.sowell.file.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

public class ImageCutPath extends TagSupport {

    private static final long serialVersionUID = 1L;

    private String path;

    private int x;

    private int y;

    private int width;

    private int height;

    public void setPath(String path) {
        this.path = path;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public int doStartTag() throws JspException {
        String newPath = null;
        if(x == 0 && y == 0 && width == 0 && height == 0) {
            newPath = path;
        } else {
            int pos = path.lastIndexOf(".");
            newPath = path.substring(0, pos)
                    + "-cut(x" + x + "_y" + y + "_w" + width + "_h" + height + ")"
                    + path.substring(pos);
        }
        JspWriter out = pageContext.getOut();
        try {
            out.print(newPath);
        } catch(IOException e) {
            throw new JspException(e.getMessage());
        }
        return super.doStartTag();
    }
}
