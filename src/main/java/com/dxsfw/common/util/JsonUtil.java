package com.dxsfw.common.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.dxsfw.common.base.jianli.ResJianLi;
import com.dxsfw.common.base.jianli.pro.JianLiEducation;
import com.dxsfw.common.base.jianli.pro.JianLiExperience;
import com.dxsfw.common.base.jianli.pro.JianLiLanguage;
import com.dxsfw.common.base.jianli.pro.JianLiTrain;
import com.dxsfw.common.base.jianli.pro.JianLiZhengshu;
import com.dxsfw.pub.model.JianLi;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {
	private static ObjectMapper mapper = new ObjectMapper();
	
	/**
	 * 数据库JianLi类转接口Jianli类
	 * @param jl
	 * @return
	 * @throws Exception
	 */
	public static ResJianLi jianli2Res(JianLi jl) throws Exception {
		if(jl == null) return null;
		String jianliStr = mapper.writeValueAsString(jl);
		ResJianLi res = mapper.readValue(jianliStr, ResJianLi.class);
		if (!StringUtils.isEmpty(jl.getLanguage())) {
			List<JianLiLanguage> _language =  mapper.readValue(jl.getLanguage(), new TypeReference<List<JianLiLanguage>>() {});
			res.set_language(_language);
		}
		if (!StringUtils.isEmpty(jl.getEducation())) {
			List<JianLiEducation> _education =  mapper.readValue(jl.getEducation(), new TypeReference<List<JianLiEducation>>() {});
			res.set_education(_education);
		}
		if (!StringUtils.isEmpty(jl.getExperience())) {
			List<JianLiExperience> _experience =  mapper.readValue(jl.getExperience(), new TypeReference<List<JianLiExperience>>() {});
			res.set_experience(_experience);
		}
		if (!StringUtils.isEmpty(jl.getTrain())) {
			List<JianLiTrain> _train =  mapper.readValue(jl.getTrain(), new TypeReference<List<JianLiTrain>>() {});
			res.set_train(_train);
		}
		if (!StringUtils.isEmpty(jl.getZhengshu())) {
			List<JianLiZhengshu> _zhengshu =  mapper.readValue(jl.getZhengshu(), new TypeReference<List<JianLiZhengshu>>() {});
			res.set_zhengshu(_zhengshu);
		}
		return res;
	}
	
	/**
	 *  数据库JianLi集合类转接口Jianli集合类
	 * @param list
	 * @return
	 * @throws Exception
	 */
	public static List<ResJianLi> jianli2Res(List<JianLi> list) throws Exception {
		if(list == null) return null;
		List<ResJianLi> resList = new ArrayList<ResJianLi>();
		for (JianLi jianLi : list) {
			resList.add(jianli2Res(jianLi));
		}
		return resList;
	}
	
	/**
	 * 接口Jianli集合类转数据库JianLi集合类
	 * @param res
	 * @return
	 * @throws Exception
	 */
	public static List<JianLi> res2Jianli(List<ResJianLi> res) throws Exception {
		if(res == null) return null;
		List<JianLi> list = new ArrayList<JianLi>();
		for (ResJianLi jianLi : res) {
			list.add(res2Jianli(jianLi));
		}
		return list;
	}
	/**
	 * 接口Jianli类转数据库JianLi类
	 * @param res
	 * @return
	 * @throws Exception
	 */
	public static JianLi res2Jianli(ResJianLi res) throws Exception {
		if(res == null) return null;
		if (res.get_language() != null && res.get_language().size() > 0) {
			String language = mapper.writeValueAsString(res.get_language());
			res.setLanguage(language);
		}
		if (res.get_education() != null && res.get_education().size() > 0) {
			String education = mapper.writeValueAsString(res.get_education());
			res.setEducation(education);
		}
		if (res.get_experience() != null && res.get_experience().size() > 0) {
			String experience = mapper.writeValueAsString(res.get_experience());
			res.setExperience(experience);
		}
		if (res.get_train() != null && res.get_train().size() > 0) {
			String train = mapper.writeValueAsString(res.get_train());
			res.setTrain(train);
		}
		if (res.get_zhengshu() != null && res.get_zhengshu().size() > 0) {
			String zhengshu = mapper.writeValueAsString(res.get_zhengshu());
			res.setZhengshu(zhengshu);
		}
		return (JianLi)res;
	}
	
	public static void main(String[] args) throws Exception {
		String s = "{\"jianlidi\":5,\"userid\":4,\"title\":\"1我的简历标题2\",\"name\":\"姓名\",\"sex\":\"女\",\"birthdate\":1441036800000,\"mobile\":\"13607447461\",\"email\":\"sfa@163.com\",\"card\":\"420202199012120000\",\"address\":\"长沙市望城坡1-1301\",\"height\":\"身高\",\"evaluation\":\"自我评价啊,随便填\",\"picture\":\"5/1.ico\",\"fujian\":\"2/2.xls\",\"createtime\":1441448327000,\"updatetime\":1441448595000,\"status\":\"N\",\"_education\":[{\"time\":\"2003/9-2006/6\",\"school\":\"湖南大学\",\"zhuanye\":\"会计学\",\"xueli\":\"本科\",\"miaoshu\":\"预留字段\"},{\"time\":\"2007/9-2010/6\",\"school\":\"北京大学\",\"zhuanye\":\"会计学\",\"xueli\":\"研究生\"},{\"time\":\"2010/9-2013/6\",\"school\":\"哈弗大学\",\"zhuanye\":\"财经管理\",\"xueli\":\"博士生\"}],\"_train\":[{\"time\":\"2013/9-2013/12\",\"company\":\"新东方厨师学院\",\"kecheng\":\"厨师高级班\",\"address\":\"长沙\",\"zhengshu\":\"国家级厨师专业三级\",\"miaoshu\":\"预留字段\"},{\"time\":\"2013/9-2013/12\",\"company\":\"某某飞行学校\",\"kecheng\":\"飞行驾驶员课程\",\"address\":\"上海\",\"zhengshu\":\"飞行员资格证书\"}],\"_language\":[{\"zhonglei\":\"英语\",\"dengji\":\"专业八级\",\"chengdu\":\"精通\",\"duxie\":\"一般\",\"tingshuo\":\"良好\"},{\"zhonglei\":\"日语\",\"dengji\":\"国家一级\",\"chengdu\":\"熟练\",\"duxie\":\"一般\",\"tingshuo\":\"良好\"},{\"zhonglei\":\"法语\",\"dengji\":\"一级\",\"chengdu\":\"熟练\",\"duxie\":\"一般\",\"tingshuo\":\"良好\"}],\"_zhengshu\":[{\"time\":\"2013/9\",\"name\":\"校级辩论赛一等奖\",\"dengji\":\"高级0\"},{\"time\":\"2003/9\",\"name\":\"1一等奖\",\"dengji\":\"高级1\"},{\"time\":\"2004/9\",\"name\":\"2一等奖\",\"dengji\":\"高级2\"},{\"time\":\"2014/9\",\"name\":\"3一等奖\",\"dengji\":\"高级3\"},{\"time\":\"2015/9\",\"name\":\"4一等奖\",\"dengji\":\"高级4\"},{\"time\":\"2013/10\",\"name\":\"5一等奖\",\"dengji\":\"高级5\"}],\"_experience\":[{\"time\":\"2013/9-2015/12\",\"company\":\"新东方英语学校\",\"zhiwei\":\"英语口语高级讲师\",\"address\":\"北京\",\"zhengshu\":\"预留字段\",\"miaoshu\":\"预留字段1\"}]}";
		
		mapper.readValue(s, ResJianLi.class);
		JianLi jianli = new JianLi();
		jianli.setLanguage("[{\"id\":1,\"zhonglei\":\"英语\",\"dengji\":\"专业八级\",\"chengdu\":\"精通\",\"duxie\":\"一般\",\"tingshuo\":\"良好\"},{\"id\":1,\"zhonglei\":\"日语\",\"dengji\":\"国家一级\",\"chengdu\":\"熟练\",\"duxie\":\"一般\",\"tingshuo\":\"良好\"}]");
		
		ResJianLi res = jianli2Res(jianli);
		System.out.println(res.get_language().size());
		
		jianli = res2Jianli(res);
		System.out.println(jianli);
	}
}
