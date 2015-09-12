package com.dxsfw.idea.model;

import java.util.Date;

import com.dxsfw.common.base.AbstractVo;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class Idea extends AbstractVo {
    private Integer ideaid;

	private Integer userid;

	@JsonInclude(Include.NON_NULL)
	private Integer payuserid;

	@JsonInclude(Include.NON_NULL)
	private Integer feeid;

	@JsonInclude(Include.NON_NULL)
	private Integer zhengjiid;

	@JsonInclude(Include.NON_NULL)
	private Date createtime;

	@JsonInclude(Include.NON_NULL)
	private Date expiretime;

	@JsonInclude(Include.NON_NULL)
	private Date updatetime;

	@JsonInclude(Include.NON_NULL)
	private String type;

	@JsonInclude(Include.NON_NULL)
	private String level;

	@JsonInclude(Include.NON_NULL)
	private String title;

	@JsonInclude(Include.NON_NULL)
	private String jieshao;

	@JsonInclude(Include.NON_NULL)
	private String content;

	@JsonInclude(Include.NON_NULL)
	private String jiami;

	@JsonInclude(Include.NON_NULL)
	private Double price;

	@JsonInclude(Include.NON_NULL)
	private String sellstatus;

	@JsonInclude(Include.NON_NULL)
	private String pingjia;

	@JsonInclude(Include.NON_NULL)
	private Integer pingjiafenshu;

	@JsonInclude(Include.NON_NULL)
	private String tag;

	@JsonInclude(Include.NON_NULL)
	private String status;

	@JsonInclude(Include.NON_NULL)
	private String pictures;

    public Integer getIdeaid() {
        return ideaid;
    }

    public void setIdeaid(Integer ideaid) {
        this.ideaid = ideaid;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getPayuserid() {
        return payuserid;
    }

    public void setPayuserid(Integer payuserid) {
        this.payuserid = payuserid;
    }

    public Integer getFeeid() {
        return feeid;
    }

    public void setFeeid(Integer feeid) {
        this.feeid = feeid;
    }

    public Integer getZhengjiid() {
        return zhengjiid;
    }

    public void setZhengjiid(Integer zhengjiid) {
        this.zhengjiid = zhengjiid;
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

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level == null ? null : level.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getJieshao() {
        return jieshao;
    }

    public void setJieshao(String jieshao) {
        this.jieshao = jieshao == null ? null : jieshao.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getJiami() {
        return jiami;
    }

    public void setJiami(String jiami) {
        this.jiami = jiami == null ? null : jiami.trim();
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getSellstatus() {
        return sellstatus;
    }

    public void setSellstatus(String sellstatus) {
        this.sellstatus = sellstatus == null ? null : sellstatus.trim();
    }

    public String getPingjia() {
        return pingjia;
    }

    public void setPingjia(String pingjia) {
        this.pingjia = pingjia == null ? null : pingjia.trim();
    }

    public Integer getPingjiafenshu() {
        return pingjiafenshu;
    }

    public void setPingjiafenshu(Integer pingjiafenshu) {
        this.pingjiafenshu = pingjiafenshu;
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
}