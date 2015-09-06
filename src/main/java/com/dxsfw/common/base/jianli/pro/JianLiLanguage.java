package com.dxsfw.common.base.jianli.pro;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class JianLiLanguage implements Serializable {
	@JsonInclude(Include.NON_NULL)
	private String zhonglei;
	@JsonInclude(Include.NON_NULL)
	private String dengji;
	@JsonInclude(Include.NON_NULL)
	private String chengdu;
	@JsonInclude(Include.NON_NULL)
	private String duxie;
	@JsonInclude(Include.NON_NULL)
	private String tingshuo;
	
	public String getZhonglei() {
		return zhonglei;
	}
	public void setZhonglei(String zhonglei) {
		this.zhonglei = zhonglei;
	}
	public String getDengji() {
		return dengji;
	}
	public void setDengji(String dengji) {
		this.dengji = dengji;
	}
	public String getChengdu() {
		return chengdu;
	}
	public void setChengdu(String chengdu) {
		this.chengdu = chengdu;
	}
	public String getDuxie() {
		return duxie;
	}
	public void setDuxie(String duxie) {
		this.duxie = duxie;
	}
	public String getTingshuo() {
		return tingshuo;
	}
	public void setTingshuo(String tingshuo) {
		this.tingshuo = tingshuo;
	}
}
