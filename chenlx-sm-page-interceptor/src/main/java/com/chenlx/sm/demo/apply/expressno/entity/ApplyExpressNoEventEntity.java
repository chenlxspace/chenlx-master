package com.chenlx.sm.demo.apply.expressno.entity;

import java.io.Serializable;
import java.util.Date;

import com.chenlx.sm.demo.apply.expressno.enums.ApplyExpressNoEventStatusEnum;

/**
 * 申请电子面单号事件表映射实体
 * 
 * @author Richard
 * @date 2017年3月12日
 * @time 上午11:33:27
 */
public class ApplyExpressNoEventEntity implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 7738889087900203038L;

    /**
     * 事件标识
     */
    private Long id;

    /**
     * 事件状态
     * 
     * @see com.chenlx.sm.demo.apply.expressno.enums.ApplyExpressNoEventStatusEnum
     */
    private Integer status;

    /**
     * 事件内容
     */
    private String content;

    /**
     * 事件异常原因
     */
    private String exceptionMsg;

    /**
     * 创建人标识
     */
    private Long createUserId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 最近修改人标识
     */
    private Long lastUpdateUserId;

    /**
     * 最近修改时间
     */
    private Date lastUpdateTime;

    /**
     * 删除标记
     */
    private Boolean deleteFlag;

    public void init() {
        this.status = ApplyExpressNoEventStatusEnum.NEW.getStatus();
        this.exceptionMsg = "";
        this.content = "";
        this.createTime = new Date();
        this.lastUpdateTime = new Date();
        this.deleteFlag = false;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getExceptionMsg() {
        return exceptionMsg;
    }

    public void setExceptionMsg(String exceptionMsg) {
        this.exceptionMsg = exceptionMsg;
    }

    public Long getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getLastUpdateUserId() {
        return lastUpdateUserId;
    }

    public void setLastUpdateUserId(Long lastUpdateUserId) {
        this.lastUpdateUserId = lastUpdateUserId;
    }

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public Boolean getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Boolean deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    @Override
    public String toString() {
        return "ApplyExpressNoEventEntity [id=" + id + ", status=" + status + ", content=" + content + ", exceptionMsg="
                + exceptionMsg + ", createUserId=" + createUserId + ", createTime=" + createTime + ", lastUpdateUserId="
                + lastUpdateUserId + ", lastUpdateTime=" + lastUpdateTime + ", deleteFlag=" + deleteFlag + "]";
    }
    
}
