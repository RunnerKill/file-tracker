package com.sowell.file.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

public class ImageRotatePath extends TagSupport {

    private static final long serialVersionUID = 1L;

    private String path;

    private int degree;
    
    public void setPath(String path) {
        this.path = path;
    }
    
    public void setDegree(int degree) {
        this.degree = degree;
    }

    @Override
    public int doStartTag() throws JspException {
        String str = "";
        int pos = path.lastIndexOf(".");
        str = path.substring(0, pos)
                + "-rotated(" + degree + ")" 
                + path.substring(pos);
        JspWriter out = pageContext.getOut();
        try {
            out.print(str);
        } catch(IOException e) {
            throw new JspException(e.getMessage());
        }
        return super.doStartTag();
    }
}
