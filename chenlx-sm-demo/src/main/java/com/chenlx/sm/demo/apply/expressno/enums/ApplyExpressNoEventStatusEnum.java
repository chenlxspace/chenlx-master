package com.chenlx.sm.demo.apply.expressno.enums;

/**
 * 申请电子面单号事件状态枚举
 * 
 * @author Richard
 * @date 2017年3月12日
 * @time 下午1:13:22
 */
public enum ApplyExpressNoEventStatusEnum {
    NEW(1, "事件新建"), //
    PROCESSED(2, "事件处理完毕"), //
    EXCEPTION(3, "事件处理发生异常"), //
    CANCEL(4, "事件取消");

    private int status;
    private String description;

    private ApplyExpressNoEventStatusEnum(int status, String description) {
        this.status = status;
        this.description = description;
    }

    public int getStatus() {
        return status;
    }

    public String getDescription() {
        return description;
    }

}
