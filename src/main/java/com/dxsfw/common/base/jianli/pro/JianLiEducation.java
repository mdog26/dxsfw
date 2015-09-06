package com.dxsfw.common.base.jianli.pro;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class JianLiEducation implements Serializable {
	@JsonInclude(Include.NON_NULL)
	private String time;
	@JsonInclude(Include.NON_NULL)
	private String school;
	@JsonInclude(Include.NON_NULL)
	private String zhuanye;
	@JsonInclude(Include.NON_NULL)
	private String xueli;
	@JsonInclude(Include.NON_NULL)
	private String miaoshu;
	
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getSchool() {
		return school;
	}
	public void setSchool(String school) {
		this.school = school;
	}
	public String getZhuanye() {
		return zhuanye;
	}
	public void setZhuanye(String zhuanye) {
		this.zhuanye = zhuanye;
	}
	public String getXueli() {
		return xueli;
	}
	public void setXueli(String xueli) {
		this.xueli = xueli;
	}
	public String getMiaoshu() {
		return miaoshu;
	}
	public void setMiaoshu(String miaoshu) {
		this.miaoshu = miaoshu;
	}
}
