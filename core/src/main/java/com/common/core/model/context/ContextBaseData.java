package com.common.core.model.context;

import javax.naming.event.EventContext;
import java.util.Date;

/**
 * 线程上下文数据基类
 */
public class ContextBaseData {

    /** 公共业务事件信息 */
    private EventContext eventContext;

    /** 当前时间 */
    private Date curDate;
}
