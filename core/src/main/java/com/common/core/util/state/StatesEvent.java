package com.common.core.util.state;

public class StatesEvent {


    /**
     * 事件编码
     */
    private int code;
    /**
     * 事件描述
     */
    private String desc;
    /**
     * 该事件发生时的状态
     */
    private States[] supportStates;
    /**
     * 该事件发生后的下一个状态
     */
    private States nextStates;

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    public States[] getSupportStates() {
        return supportStates;
    }

    public States getNextStates() {
        return nextStates;
    }

    public StatesEvent(int code, String desc, States[] supportStates, States resultStates) {
        this.code = code;
        this.desc = desc;
        this.supportStates = supportStates;
        this.nextStates = resultStates;
    }

    public StatesEvent(int code, String desc, States supportStates, States nextStates) {
        this.code = code;
        this.desc = desc;
        this.supportStates = new States[]{supportStates};
        this.nextStates = nextStates;
    }

    @Override
    public String toString() {
        return "code:" + code + ", desc:" + desc;
    }
}
