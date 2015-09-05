package com.dxsfw.pub.model;

import java.util.Date;

import com.dxsfw.common.base.AbstractVo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class JianLi extends AbstractVo {
	@JsonInclude(Include.NON_NULL)
    private Integer jianliid;

    @JsonInclude(Include.NON_NULL)
    private Integer userid;

    @JsonInclude(Include.NON_NULL)
    private String title;

    @JsonInclude(Include.NON_NULL)
    private String name;

    @JsonInclude(Include.NON_NULL)
    private String sex;

    @JsonInclude(Include.NON_NULL)
    private Date birthdate;

    @JsonInclude(Include.NON_NULL)
    private String mobile;

    @JsonInclude(Include.NON_NULL)
    private String email;

    @JsonInclude(Include.NON_NULL)
    private String card;

    @JsonInclude(Include.NON_NULL)
    private String address;
    @JsonInclude(Include.NON_NULL)
    private String height;

    @JsonIgnore
    private String education;

    @JsonIgnore
    private String train;

    @JsonIgnore
    private String language;

    @JsonIgnore
    private String zhengshu;

    @JsonIgnore
    private String experience;

    @JsonInclude(Include.NON_NULL)
    private String evaluation;

    @JsonInclude(Include.NON_NULL)
    private String picture;

    @JsonInclude(Include.NON_NULL)
    private String fujian;
    @JsonInclude(Include.NON_NULL)
    private Date createtime;
    @JsonInclude(Include.NON_NULL)
    private Date updatetime;
    
    @JsonInclude(Include.NON_NULL)
    private String status;

    public Integer getJianliid() {
        return jianliid;
    }

    public void setJianliid(Integer jianliid) {
        this.jianliid = jianliid;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card == null ? null : card.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height == null ? null : height.trim();
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education == null ? null : education.trim();
    }

    public String getTrain() {
        return train;
    }

    public void setTrain(String train) {
        this.train = train == null ? null : train.trim();
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language == null ? null : language.trim();
    }

    public String getZhengshu() {
        return zhengshu;
    }

    public void setZhengshu(String zhengshu) {
        this.zhengshu = zhengshu == null ? null : zhengshu.trim();
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience == null ? null : experience.trim();
    }

    public String getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(String evaluation) {
        this.evaluation = evaluation == null ? null : evaluation.trim();
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture == null ? null : picture.trim();
    }

    public String getFujian() {
        return fujian;
    }

    public void setFujian(String fujian) {
        this.fujian = fujian == null ? null : fujian.trim();
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }
}