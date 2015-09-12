package com.dxsfw.common.base;

import java.io.Serializable;
import java.util.List;

import com.dxsfw.bbs.model.Bbs;
import com.dxsfw.chuangye.model.ChuangYe;
import com.dxsfw.common.base.jianli.ResJianLi;
import com.dxsfw.common.constants.Constant;
import com.dxsfw.idea.model.Idea;
import com.dxsfw.idea.model.Zhengji;
import com.dxsfw.jianzhi.model.Jianzhi;
import com.dxsfw.party.model.Party;
import com.dxsfw.pub.model.Reply;
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
	private List<?> list;

	@JsonInclude(Include.NON_NULL)
	private User user;

	@JsonInclude(Include.NON_NULL)
	private ResJianLi jianli;
	
	@JsonInclude(Include.NON_NULL)
	private List<ResJianLi> jianliList;
	
	@JsonInclude(Include.NON_NULL)
	private Jianzhi jianzhi;
	
	@JsonInclude(Include.NON_NULL)
	private List<Jianzhi> jianzhiList;
	
	@JsonInclude(Include.NON_NULL)
	private Party party;
	
	@JsonInclude(Include.NON_NULL)
	private List<Party> partyList;
	
	@JsonInclude(Include.NON_NULL)
	private Bbs bbs;
	
	@JsonInclude(Include.NON_NULL)
	private List<Bbs> bbsList;
	
	@JsonInclude(Include.NON_NULL)
	private Reply reply;
	
	@JsonInclude(Include.NON_NULL)
	private List<Reply> replyList;
	
	@JsonInclude(Include.NON_NULL)
	private ChuangYe chuangye;
	
	@JsonInclude(Include.NON_NULL)
	private List<ChuangYe> chuangyeList;
	
	@JsonInclude(Include.NON_NULL)
	private Idea idea;
	
	@JsonInclude(Include.NON_NULL)
	private List<Idea> ideaList;
	
	@JsonInclude(Include.NON_NULL)
	private Zhengji zhengji;
	
	@JsonInclude(Include.NON_NULL)
	private List<Zhengji> zhengjiList;

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

	public ResJianLi getJianli() {
		return jianli;
	}

	public void setJianli(ResJianLi jianli) {
		this.jianli = jianli;
	}

	public List<ResJianLi> getJianliList() {
		return jianliList;
	}

	public void setJianliList(List<ResJianLi> jianliList) {
		this.jianliList = jianliList;
	}

	public Jianzhi getJianzhi() {
		return jianzhi;
	}

	public void setJianzhi(Jianzhi jianzhi) {
		this.jianzhi = jianzhi;
	}

	public List<Jianzhi> getJianzhiList() {
		return jianzhiList;
	}

	public void setJianzhiList(List<Jianzhi> jianzhiList) {
		this.jianzhiList = jianzhiList;
	}

	public List<?> getList() {
		return list;
	}

	public void setList(List<?> list) {
		this.list = list;
	}

	public Party getParty() {
		return party;
	}

	public void setParty(Party party) {
		this.party = party;
	}

	public List<Party> getPartyList() {
		return partyList;
	}

	public void setPartyList(List<Party> partyList) {
		this.partyList = partyList;
	}

	public Bbs getBbs() {
		return bbs;
	}

	public void setBbs(Bbs bbs) {
		this.bbs = bbs;
	}

	public List<Bbs> getBbsList() {
		return bbsList;
	}

	public void setBbsList(List<Bbs> bbsList) {
		this.bbsList = bbsList;
	}

	public Reply getReply() {
		return reply;
	}

	public void setReply(Reply reply) {
		this.reply = reply;
	}

	public List<Reply> getReplyList() {
		return replyList;
	}

	public void setReplyList(List<Reply> replyList) {
		this.replyList = replyList;
	}

	public ChuangYe getChuangye() {
		return chuangye;
	}

	public void setChuangye(ChuangYe chuangye) {
		this.chuangye = chuangye;
	}

	public List<ChuangYe> getChuangyeList() {
		return chuangyeList;
	}

	public void setChuangyeList(List<ChuangYe> chuangyeList) {
		this.chuangyeList = chuangyeList;
	}

	public Idea getIdea() {
		return idea;
	}

	public void setIdea(Idea idea) {
		this.idea = idea;
	}

	public List<Idea> getIdeaList() {
		return ideaList;
	}

	public void setIdeaList(List<Idea> ideaList) {
		this.ideaList = ideaList;
	}

	public Zhengji getZhengji() {
		return zhengji;
	}

	public void setZhengji(Zhengji zhengji) {
		this.zhengji = zhengji;
	}

	public List<Zhengji> getZhengjiList() {
		return zhengjiList;
	}

	public void setZhengjiList(List<Zhengji> zhengjiList) {
		this.zhengjiList = zhengjiList;
	}
}
