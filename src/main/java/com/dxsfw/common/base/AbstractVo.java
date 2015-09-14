package com.dxsfw.common.base;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.dxsfw.pub.model.User;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * VO 抽象基类
 * 
 * @Author leo.zhou
 * @CreateDate 2013-7-11
 * @Version 1.0
 */
public abstract class AbstractVo implements Serializable {
	
	private static final long serialVersionUID = 3504019333087374575L;

	@JsonInclude(Include.NON_NULL)
	private User user;
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}