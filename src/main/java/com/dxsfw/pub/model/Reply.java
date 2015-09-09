package com.dxsfw.pub.model;

import java.util.Date;

import com.dxsfw.common.base.AbstractVo;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class Reply extends AbstractVo {
    private Integer replyid;

    private String tablename;

    private Integer pk;

    private Integer publishuserid;

    private Integer replyuserid;

    @JsonInclude(Include.NON_NULL)
    private String message;

    @JsonInclude(Include.NON_NULL)
    private Date time;

    @JsonInclude(Include.NON_NULL)
    private String picture;

    @JsonInclude(Include.NON_NULL)
    private String status;

    public Integer getReplyid() {
        return replyid;
    }

    public void setReplyid(Integer replyid) {
        this.replyid = replyid;
    }

    public String getTablename() {
        return tablename;
    }

    public void setTablename(String tablename) {
        this.tablename = tablename == null ? null : tablename.trim();
    }

    public Integer getPk() {
        return pk;
    }

    public void setPk(Integer pk) {
        this.pk = pk;
    }

    public Integer getPublishuserid() {
        return publishuserid;
    }

    public void setPublishuserid(Integer publishuserid) {
        this.publishuserid = publishuserid;
    }

    public Integer getReplyuserid() {
        return replyuserid;
    }

    public void setReplyuserid(Integer replyuserid) {
        this.replyuserid = replyuserid;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message == null ? null : message.trim();
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture == null ? null : picture.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }
}