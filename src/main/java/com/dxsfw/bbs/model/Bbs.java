package com.dxsfw.bbs.model;

import java.util.Date;

import com.dxsfw.common.base.AbstractVo;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class Bbs extends AbstractVo {
    private Integer bbsid;

    private Integer userid;

    @JsonInclude(Include.NON_NULL)
    private Integer feeid;

    @JsonInclude(Include.NON_NULL)
    private Date createtime;

    @JsonInclude(Include.NON_NULL)
    private Date expiretime;

    @JsonInclude(Include.NON_NULL)
    private Date updatetime;

    @JsonInclude(Include.NON_NULL)
    private String type;

    @JsonInclude(Include.NON_NULL)
    private String title;

    @JsonInclude(Include.NON_NULL)
    private String address;

    @JsonInclude(Include.NON_NULL)
    private String bankuai;

    @JsonInclude(Include.NON_NULL)
    private String zhuanye;

    @JsonInclude(Include.NON_NULL)
    private String teachtype;

    @JsonInclude(Include.NON_NULL)
    private String content;

    @JsonInclude(Include.NON_NULL)
    private String teachtime;

    @JsonInclude(Include.NON_NULL)
    private Integer people;

    @JsonInclude(Include.NON_NULL)
    private Integer replynumber;

    @JsonInclude(Include.NON_NULL)
    private Integer clicknumber;

    @JsonInclude(Include.NON_NULL)
    private String tag;

    @JsonInclude(Include.NON_NULL)
    private String status;

    @JsonInclude(Include.NON_NULL)
    private String pictures;

    @JsonInclude(Include.NON_NULL)
    private String info;

    public Integer getBbsid() {
        return bbsid;
    }

    public void setBbsid(Integer bbsid) {
        this.bbsid = bbsid;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
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

    public Date getExpiretime() {
        return expiretime;
    }

    public void setExpiretime(Date expiretime) {
        this.expiretime = expiretime;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getBankuai() {
        return bankuai;
    }

    public void setBankuai(String bankuai) {
        this.bankuai = bankuai == null ? null : bankuai.trim();
    }

    public String getZhuanye() {
        return zhuanye;
    }

    public void setZhuanye(String zhuanye) {
        this.zhuanye = zhuanye == null ? null : zhuanye.trim();
    }

    public String getTeachtype() {
        return teachtype;
    }

    public void setTeachtype(String teachtype) {
        this.teachtype = teachtype == null ? null : teachtype.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getTeachtime() {
        return teachtime;
    }

    public void setTeachtime(String teachtime) {
        this.teachtime = teachtime == null ? null : teachtime.trim();
    }

    public Integer getPeople() {
        return people;
    }

    public void setPeople(Integer people) {
        this.people = people;
    }

    public Integer getReplynumber() {
        return replynumber;
    }

    public void setReplynumber(Integer replynumber) {
        this.replynumber = replynumber;
    }

    public Integer getClicknumber() {
        return clicknumber;
    }

    public void setClicknumber(Integer clicknumber) {
        this.clicknumber = clicknumber;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag == null ? null : tag.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getPictures() {
        return pictures;
    }

    public void setPictures(String pictures) {
        this.pictures = pictures == null ? null : pictures.trim();
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info == null ? null : info.trim();
    }
}