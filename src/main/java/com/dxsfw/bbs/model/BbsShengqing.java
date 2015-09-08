package com.dxsfw.bbs.model;

import java.util.Date;

import com.dxsfw.common.base.AbstractVo;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class BbsShengqing extends AbstractVo {
    private Integer shenqingid;

    private Integer bbsid;

    @JsonInclude(Include.NON_NULL)
    private Integer publishuserid;

    @JsonInclude(Include.NON_NULL)
    private Integer shengqinguserid;

    @JsonInclude(Include.NON_NULL)
    private Integer payid;

    @JsonInclude(Include.NON_NULL)
    private Date time;

    @JsonInclude(Include.NON_NULL)
    private String message;

    @JsonInclude(Include.NON_NULL)
    private String status;

    public Integer getShenqingid() {
        return shenqingid;
    }

    public void setShenqingid(Integer shenqingid) {
        this.shenqingid = shenqingid;
    }

    public Integer getBbsid() {
        return bbsid;
    }

    public void setBbsid(Integer bbsid) {
        this.bbsid = bbsid;
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

    public Integer getPayid() {
        return payid;
    }

    public void setPayid(Integer payid) {
        this.payid = payid;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message == null ? null : message.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }
}