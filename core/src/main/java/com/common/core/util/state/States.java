package com.common.core.util.state;

public class States {

    /**
     * 状态编码
     */
    protected int code;
    /**
     * 状态描述
     */
    protected String desc;

    public States(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int code() {
        return this.code;
    }

    public String desc() {
        return this.desc;
    }

    @Override
    public String toString() {
        return "code=" + code + ", desc=" + desc;
    }
}