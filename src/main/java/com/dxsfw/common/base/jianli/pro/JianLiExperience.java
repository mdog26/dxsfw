package com.dxsfw.common.base.jianli.pro;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class JianLiExperience implements Serializable {
	@JsonInclude(Include.NON_NULL)
	private String time;
	@JsonInclude(Include.NON_NULL)
	private String company;
	@JsonInclude(Include.NON_NULL)
	private String zhiwei;
	@JsonInclude(Include.NON_NULL)
	private String address;
	@JsonInclude(Include.NON_NULL)
	private String zhengshu;
	@JsonInclude(Include.NON_NULL)
	private String miaoshu;
	
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getZhiwei() {
		return zhiwei;
	}
	public void setZhiwei(String zhiwei) {
		this.zhiwei = zhiwei;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getZhengshu() {
		return zhengshu;
	}
	public void setZhengshu(String zhengshu) {
		this.zhengshu = zhengshu;
	}
	public String getMiaoshu() {
		return miaoshu;
	}
	public void setMiaoshu(String miaoshu) {
		this.miaoshu = miaoshu;
	}
}
