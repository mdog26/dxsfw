package com.dxsfw.idea.model;

import java.util.Date;

import com.dxsfw.common.base.AbstractVo;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class Zhengji extends AbstractVo {
    private Integer zhengjiid;

    private Integer userid;

    @JsonInclude(Include.NON_NULL)
    private Integer selluserid;

    @JsonInclude(Include.NON_NULL)
    private Integer feeid;

    @JsonInclude(Include.NON_NULL)
    private Date createtime;

    @JsonInclude(Include.NON_NULL)
    private Date updatetime;

    @JsonInclude(Include.NON_NULL)
    private String title;

    @JsonInclude(Include.NON_NULL)
    private String content;

    @JsonInclude(Include.NON_NULL)
    private String sellstatus;

    @JsonInclude(Include.NON_NULL)
    private String tag;

    @JsonInclude(Include.NON_NULL)
    private String pictures;

    @JsonInclude(Include.NON_NULL)
    private String status;

    public Integer getZhengjiid() {
        return zhengjiid;
    }

    public void setZhengjiid(Integer zhengjiid) {
        this.zhengjiid = zhengjiid;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getSelluserid() {
        return selluserid;
    }

    public void setSelluserid(Integer selluserid) {
        this.selluserid = selluserid;
    }

    public Integer getFeeid() {
        return feeid;
    }

    public void setFeeid(Integer feeid) {
        this.feeid = feeid;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getSellstatus() {
        return sellstatus;
    }

    public void setSellstatus(String sellstatus) {
        this.sellstatus = sellstatus == null ? null : sellstatus.trim();
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag == null ? null : tag.trim();
    }

    public String getPictures() {
        return pictures;
    }

    public void setPictures(String pictures) {
        this.pictures = pictures == null ? null : pictures.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }
}