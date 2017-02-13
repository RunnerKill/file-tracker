package com.sowell.file.result;

import com.google.gson.Gson;

/**
 * 交互结果类
 * @author Xiaojie.Xu
 */
public abstract class Result {

    protected Gson gsonUtil = new Gson();

    public String toJson() {
        return gsonUtil.toJson(this);
    }

}
