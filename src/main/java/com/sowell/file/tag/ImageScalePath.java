package com.sowell.file.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

public class ImageScalePath extends TagSupport {

    private static final long serialVersionUID = 1L;

    private String path;

    private int width;

    private int height;

    public void setPath(String path) {
        this.path = path;
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
        if(width == 0 && height == 0) {
            newPath = path;
        } else {
            int pos = path.lastIndexOf(".");
            newPath = path.substring(0, pos)
                    + "-scale(w" + width + "_h" + height + ")"
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
