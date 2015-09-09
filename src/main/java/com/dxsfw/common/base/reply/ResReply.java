package com.dxsfw.common.base.reply;

import com.dxsfw.pub.model.Reply;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class ResReply extends Reply {
	@JsonInclude(Include.NON_NULL)
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
