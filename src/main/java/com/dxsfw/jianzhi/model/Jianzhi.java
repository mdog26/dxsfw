package com.dxsfw.jianzhi.model;

import java.util.Date;

import com.dxsfw.common.base.AbstractVo;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class Jianzhi extends AbstractVo {
    private Integer jianzhiid;

    private Integer userid;

    @JsonInclude(Include.NON_NULL)
    private Date createtime;

    @JsonInclude(Include.NON_NULL)
    private Date expiretime;

	@JsonInclude(Include.NON_NULL)
	private Date updatetime;
	
	@JsonInclude(Include.NON_NULL)
	private String expire;
	
	@JsonInclude(Include.NON_NULL)
	private String title;
	
	@JsonInclude(Include.NON_NULL)
	private String tag;
	
	@JsonInclude(Include.NON_NULL)
	private String company;
	
	@JsonInclude(Include.NON_NULL)
	private String companyintroduction;
	
	@JsonInclude(Include.NON_NULL)
	private String industry;
	
	@JsonInclude(Include.NON_NULL)
	private String workplace;
	
	@JsonInclude(Include.NON_NULL)
	private String pay;
	
	@JsonInclude(Include.NON_NULL)
	private String worktime;
	
	@JsonInclude(Include.NON_NULL)
	private String people;
	
	@JsonInclude(Include.NON_NULL)
	private String description;
	
	@JsonInclude(Include.NON_NULL)
	private String status;

    public Integer getJianzhiid() {
        return jianzhiid;
    }

    public void setJianzhiid(Integer jianzhiid) {
        this.jianzhiid = jianzhiid;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
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

    public String getExpire() {
        return expire;
    }

    public void setExpire(String expire) {
        this.expire = expire == null ? null : expire.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag == null ? null : tag.trim();
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company == null ? null : company.trim();
    }

    public String getCompanyintroduction() {
        return companyintroduction;
    }

    public void setCompanyintroduction(String companyintroduction) {
        this.companyintroduction = companyintroduction == null ? null : companyintroduction.trim();
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry == null ? null : industry.trim();
    }

    public String getWorkplace() {
        return workplace;
    }

    public void setWorkplace(String workplace) {
        this.workplace = workplace == null ? null : workplace.trim();
    }

    public String getPay() {
        return pay;
    }

    public void setPay(String pay) {
        this.pay = pay == null ? null : pay.trim();
    }

    public String getWorktime() {
        return worktime;
    }

    public void setWorktime(String worktime) {
        this.worktime = worktime == null ? null : worktime.trim();
    }

    public String getPeople() {
        return people;
    }

    public void setPeople(String people) {
        this.people = people == null ? null : people.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }
}