package com.dxsfw.jianzhi.model;

import java.util.Date;

import com.dxsfw.common.base.AbstractVo;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class JianzhiShengqing extends AbstractVo {
    private Integer shenqingid;

    private Integer jianzhiid;

    private Integer publishuserid;

    private Integer shengqinguserid;

    private Integer jianliid;

    @JsonInclude(Include.NON_NULL)
    private Date time;

    @JsonInclude(Include.NON_NULL)
    private String status;

    public Integer getShenqingid() {
        return shenqingid;
    }

    public void setShenqingid(Integer shenqingid) {
        this.shenqingid = shenqingid;
    }

    public Integer getJianzhiid() {
        return jianzhiid;
    }

    public void setJianzhiid(Integer jianzhiid) {
        this.jianzhiid = jianzhiid;
    }

    public Integer getPublishuserid() {
        return publishuserid;
    }

    public void setPublishuserid(Integer publishuserid) {
        this.publishuserid = publishuserid;
    }

    public Integer getShengqinguserid() {
        return shengqinguserid;
    }

    public void setShengqinguserid(Integer shengqinguserid) {
        this.shengqinguserid = shengqinguserid;
    }

    public Integer getJianliid() {
        return jianliid;
    }

    public void setJianliid(Integer jianliid) {
        this.jianliid = jianliid;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }
}