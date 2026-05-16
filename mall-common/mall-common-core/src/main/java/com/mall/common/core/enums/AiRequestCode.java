package com.mall.common.core.enums;

public enum AiRequestCode {
    OPEN_PAGE(1001, "打开菜单");

    AiRequestCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private final int code;
    private final String msg;

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

}
