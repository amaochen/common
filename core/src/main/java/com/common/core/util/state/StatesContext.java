package com.common.core.util.state;

import java.util.Objects;

public class StatesContext<T, K> {


    /**
     * 当前状态
     */
    private States states;
    /**
     * 数据
     */
    private T order;

    private K param;

    public StatesContext(States states, T order, K param) {
        Objects.requireNonNull(states);
        Objects.requireNonNull(order);
        this.states = states;
        this.order = order;
        this.param = param;
    }


    public States getStates() {
        return states;
    }

    public T getOrder() {
        return order;
    }

    public K getParam() {
        return param;
    }

    @Override
    public String toString() {
        return
                "orderId:" + order.toString() + ", status:{" + states.toString() + "}";
    }

}
