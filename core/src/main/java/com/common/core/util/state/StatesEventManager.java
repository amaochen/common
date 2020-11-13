package com.common.core.util.state;


import com.common.core.entity.vo.Result;

import java.util.HashMap;
import java.util.Map;

/**
 * 具体使用方式，参考demo
 * @author 阿毛
 */
public class StatesEventManager {

    Map<Integer, AbstractStatesHandler> statesProcessors = new HashMap<>();

    /**
     * 状态流转方法
     *
     * @param statesContext 上下文
     * @param statesEvent   操作
     * @return 扭转后的状态
     */
    public Result handle(final StatesContext statesContext, final StatesEvent statesEvent) {
        // 获取对应处理器,根据入参状态订单流转的结果状态
        AbstractStatesHandler abstractStatesHandler = getProcessor(statesContext, statesEvent);
        return abstractStatesHandler.handler(statesContext);
    }

    /**
     * 根据入参状态枚举实例获取对应的状态后处理器
     *
     * @param statesContext 上下文
     * @param statesEvent   操作事件
     * @return
     */
    private AbstractStatesHandler getProcessor(final StatesContext statesContext, final StatesEvent statesEvent) {
        AbstractStatesHandler processor = statesProcessors.get(statesEvent.getCode());
        if (null == processor) {
            throw new IllegalArgumentException(String.format("can't find states processor. :%s", logString(statesContext, statesEvent)));
        }
        return processor;
    }

    private String logString(final StatesContext statesContext, final StatesEvent statesEvent) {
        StringBuilder sb = new StringBuilder();
        if (null != statesContext) {
            sb.append(String.format("context [%s] ", statesContext.toString()));
        }
        if (null != statesEvent) {
            sb.append(String.format("event [%s] ", statesEvent.toString()));
        }
        return sb.toString();
    }

    public void addHandler(AbstractStatesHandler handler) {
        statesProcessors.put(handler.statesEvent().getCode(), handler);
    }

}