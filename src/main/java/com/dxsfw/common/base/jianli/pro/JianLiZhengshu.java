package com.dxsfw.common.base.jianli.pro;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class JianLiZhengshu implements Serializable {
	@JsonInclude(Include.NON_NULL)
	private String time;
	@JsonInclude(Include.NON_NULL)
	private String name;
	@JsonInclude(Include.NON_NULL)
	private String dengji;
	
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDengji() {
		return dengji;
	}
	public void setDengji(String dengji) {
		this.dengji = dengji;
	}
}
