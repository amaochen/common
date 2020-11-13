package com.common.core.util.state;

import com.common.core.entity.vo.Result;

import java.util.Arrays;

/**
 * Function: 有限状态机处理器
 *
 * @author chenxiaoqi_wb
 * @date 2020/11/13
 * Copyright (c) 2020,chenxiaoqi_wb@g7.com.cn All Rights Reserved.
 */
abstract class AbstractStatesHandler<T, K> {

    /**
     * 操作类对应的状态操作事件
     *
     * @return
     */
    abstract StatesEvent statesEvent();

    /**
     * 状态机处理器支持的状态
     *
     * @return
     */
    private States[] supportStates() {
        return statesEvent().getSupportStates();
    }

    /**
     * 处理完成后的状态
     *
     * @return
     */
    States nextStates() {
        return statesEvent().getNextStates();
    }

    /**
     * 判断当前处理器是否支持当前状态
     *
     * @param statesContext
     * @return
     */
    boolean isSupport(final StatesContext<T, K> statesContext) {
        States[] statusEnums = supportStates();
        return Arrays.stream(statusEnums).anyMatch(s -> s.code() == statesContext.getStates().code());
    }

    /**
     * 实际操作逻辑
     * <p>
     * isSupport 判断当前状态
     * before 进行一些前置检查，返回true才进行下一步操作
     * process 执行操作，返回false表示执行失败，抛出异常
     * after 操作执行完成后，一般为发送状态变更通知消息等与主流程无关的业务
     * exception 执行异常
     *
     * @param statesContext
     * @return 操作执行后的状态
     */
    Result handler(final StatesContext<T, K> statesContext) {
        // 判断是否支持当前状态
        if (!isSupport(statesContext)) {
            return Result.fail("无效的状态");
        }
        // 操作 前置处理
        boolean beforeResult = before(statesContext);
        // 执行
        Result result = null;
        if (beforeResult) {
            result = process(statesContext);
        }
        // 操作 后置处理
        if (null != result && Result.SUCCESSFUL_CODE.equals(result.getCode())) {
            after(statesContext, nextStates());
        }
        return result;


    }

    boolean before(final StatesContext<T, K> statesContext) {
        return true;
    }

    abstract Result process(final StatesContext<T, K> statesContext);

    abstract void after(final StatesContext<T, K> statesContext, States nextStates);

    private String toString(States[] states) {
        StringBuilder sb = new StringBuilder();
        Arrays.stream(states).forEach(e -> sb.append(e.toString()).append(","));
        return sb.toString();
    }


}
