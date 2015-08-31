package com.dxsfw.common.base;

import java.io.Serializable;
import java.util.List;

import com.dxsfw.common.constants.Constant;
import com.dxsfw.pub.model.JianLi;
import com.dxsfw.pub.model.User;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class Res implements Serializable {
	
	private int status = Constant.STATUS_OK_200;
	
	@JsonInclude(Include.NON_NULL)
	private String msg;
	
	@JsonInclude(Include.NON_NULL)
	private String token;

	@JsonInclude(Include.NON_NULL)
	private User user;

	@JsonInclude(Include.NON_NULL)
	private JianLi jianli;
	
	@JsonInclude(Include.NON_NULL)
	private List<JianLi> jianliList;

	public Res() {}
	
	public Res(int status, String msg) {
		this.status = status;
		this.msg = msg;
	}
	
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public JianLi getJianli() {
		return jianli;
	}

	public void setJianli(JianLi jianli) {
		this.jianli = jianli;
	}

	public List<JianLi> getJianliList() {
		return jianliList;
	}

	public void setJianliList(List<JianLi> jianliList) {
		this.jianliList = jianliList;
	}
}
