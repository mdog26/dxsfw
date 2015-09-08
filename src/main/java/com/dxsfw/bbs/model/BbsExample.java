package com.dxsfw.bbs.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.dxsfw.common.base.BaseExample;

public class BbsExample extends BaseExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public BbsExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andBbsidIsNull() {
            addCriterion("bbsid is null");
            return (Criteria) this;
        }

        public Criteria andBbsidIsNotNull() {
            addCriterion("bbsid is not null");
            return (Criteria) this;
        }

        public Criteria andBbsidEqualTo(Integer value) {
            addCriterion("bbsid =", value, "bbsid");
            return (Criteria) this;
        }

        public Criteria andBbsidNotEqualTo(Integer value) {
            addCriterion("bbsid <>", value, "bbsid");
            return (Criteria) this;
        }

        public Criteria andBbsidGreaterThan(Integer value) {
            addCriterion("bbsid >", value, "bbsid");
            return (Criteria) this;
        }

        public Criteria andBbsidGreaterThanOrEqualTo(Integer value) {
            addCriterion("bbsid >=", value, "bbsid");
            return (Criteria) this;
        }

        public Criteria andBbsidLessThan(Integer value) {
            addCriterion("bbsid <", value, "bbsid");
            return (Criteria) this;
        }

        public Criteria andBbsidLessThanOrEqualTo(Integer value) {
            addCriterion("bbsid <=", value, "bbsid");
            return (Criteria) this;
        }

        public Criteria andBbsidIn(List<Integer> values) {
            addCriterion("bbsid in", values, "bbsid");
            return (Criteria) this;
        }

        public Criteria andBbsidNotIn(List<Integer> values) {
            addCriterion("bbsid not in", values, "bbsid");
            return (Criteria) this;
        }

        public Criteria andBbsidBetween(Integer value1, Integer value2) {
            addCriterion("bbsid between", value1, value2, "bbsid");
            return (Criteria) this;
        }

        public Criteria andBbsidNotBetween(Integer value1, Integer value2) {
            addCriterion("bbsid not between", value1, value2, "bbsid");
            return (Criteria) this;
        }

        public Criteria andUseridIsNull() {
            addCriterion("userid is null");
            return (Criteria) this;
        }

        public Criteria andUseridIsNotNull() {
            addCriterion("userid is not null");
            return (Criteria) this;
        }

        public Criteria andUseridEqualTo(Integer value) {
            addCriterion("userid =", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridNotEqualTo(Integer value) {
            addCriterion("userid <>", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridGreaterThan(Integer value) {
            addCriterion("userid >", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridGreaterThanOrEqualTo(Integer value) {
            addCriterion("userid >=", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridLessThan(Integer value) {
            addCriterion("userid <", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridLessThanOrEqualTo(Integer value) {
            addCriterion("userid <=", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridIn(List<Integer> values) {
            addCriterion("userid in", values, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridNotIn(List<Integer> values) {
            addCriterion("userid not in", values, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridBetween(Integer value1, Integer value2) {
            addCriterion("userid between", value1, value2, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridNotBetween(Integer value1, Integer value2) {
            addCriterion("userid not between", value1, value2, "userid");
            return (Criteria) this;
        }

        public Criteria andFeeidIsNull() {
            addCriterion("feeid is null");
            return (Criteria) this;
        }

        public Criteria andFeeidIsNotNull() {
            addCriterion("feeid is not null");
            return (Criteria) this;
        }

        public Criteria andFeeidEqualTo(Integer value) {
            addCriterion("feeid =", value, "feeid");
            return (Criteria) this;
        }

        public Criteria andFeeidNotEqualTo(Integer value) {
            addCriterion("feeid <>", value, "feeid");
            return (Criteria) this;
        }

        public Criteria andFeeidGreaterThan(Integer value) {
            addCriterion("feeid >", value, "feeid");
            return (Criteria) this;
        }

        public Criteria andFeeidGreaterThanOrEqualTo(Integer value) {
            addCriterion("feeid >=", value, "feeid");
            return (Criteria) this;
        }

        public Criteria andFeeidLessThan(Integer value) {
            addCriterion("feeid <", value, "feeid");
            return (Criteria) this;
        }

        public Criteria andFeeidLessThanOrEqualTo(Integer value) {
            addCriterion("feeid <=", value, "feeid");
            return (Criteria) this;
        }

        public Criteria andFeeidIn(List<Integer> values) {
            addCriterion("feeid in", values, "feeid");
            return (Criteria) this;
        }

        public Criteria andFeeidNotIn(List<Integer> values) {
            addCriterion("feeid not in", values, "feeid");
            return (Criteria) this;
        }

        public Criteria andFeeidBetween(Integer value1, Integer value2) {
            addCriterion("feeid between", value1, value2, "feeid");
            return (Criteria) this;
        }

        public Criteria andFeeidNotBetween(Integer value1, Integer value2) {
            addCriterion("feeid not between", value1, value2, "feeid");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIsNull() {
            addCriterion("createtime is null");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIsNotNull() {
            addCriterion("createtime is not null");
            return (Criteria) this;
        }

        public Criteria andCreatetimeEqualTo(Date value) {
            addCriterion("createtime =", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotEqualTo(Date value) {
            addCriterion("createtime <>", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeGreaterThan(Date value) {
            addCriterion("createtime >", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeGreaterThanOrEqualTo(Date value) {
            addCriterion("createtime >=", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeLessThan(Date value) {
            addCriterion("createtime <", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeLessThanOrEqualTo(Date value) {
            addCriterion("createtime <=", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIn(List<Date> values) {
            addCriterion("createtime in", values, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotIn(List<Date> values) {
            addCriterion("createtime not in", values, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeBetween(Date value1, Date value2) {
            addCriterion("createtime between", value1, value2, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotBetween(Date value1, Date value2) {
            addCriterion("createtime not between", value1, value2, "createtime");
            return (Criteria) this;
        }

        public Criteria andExpiretimeIsNull() {
            addCriterion("expiretime is null");
            return (Criteria) this;
        }

        public Criteria andExpiretimeIsNotNull() {
            addCriterion("expiretime is not null");
            return (Criteria) this;
        }

        public Criteria andExpiretimeEqualTo(Date value) {
            addCriterion("expiretime =", value, "expiretime");
            return (Criteria) this;
        }

        public Criteria andExpiretimeNotEqualTo(Date value) {
            addCriterion("expiretime <>", value, "expiretime");
            return (Criteria) this;
        }

        public Criteria andExpiretimeGreaterThan(Date value) {
            addCriterion("expiretime >", value, "expiretime");
            return (Criteria) this;
        }

        public Criteria andExpiretimeGreaterThanOrEqualTo(Date value) {
            addCriterion("expiretime >=", value, "expiretime");
            return (Criteria) this;
        }

        public Criteria andExpiretimeLessThan(Date value) {
            addCriterion("expiretime <", value, "expiretime");
            return (Criteria) this;
        }

        public Criteria andExpiretimeLessThanOrEqualTo(Date value) {
            addCriterion("expiretime <=", value, "expiretime");
            return (Criteria) this;
        }

        public Criteria andExpiretimeIn(List<Date> values) {
            addCriterion("expiretime in", values, "expiretime");
            return (Criteria) this;
        }

        public Criteria andExpiretimeNotIn(List<Date> values) {
            addCriterion("expiretime not in", values, "expiretime");
            return (Criteria) this;
        }

        public Criteria andExpiretimeBetween(Date value1, Date value2) {
            addCriterion("expiretime between", value1, value2, "expiretime");
            return (Criteria) this;
        }

        public Criteria andExpiretimeNotBetween(Date value1, Date value2) {
            addCriterion("expiretime not between", value1, value2, "expiretime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeIsNull() {
            addCriterion("updatetime is null");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeIsNotNull() {
            addCriterion("updatetime is not null");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeEqualTo(Date value) {
            addCriterion("updatetime =", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeNotEqualTo(Date value) {
            addCriterion("updatetime <>", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeGreaterThan(Date value) {
            addCriterion("updatetime >", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeGreaterThanOrEqualTo(Date value) {
            addCriterion("updatetime >=", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeLessThan(Date value) {
            addCriterion("updatetime <", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeLessThanOrEqualTo(Date value) {
            addCriterion("updatetime <=", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeIn(List<Date> values) {
            addCriterion("updatetime in", values, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeNotIn(List<Date> values) {
            addCriterion("updatetime not in", values, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeBetween(Date value1, Date value2) {
            addCriterion("updatetime between", value1, value2, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeNotBetween(Date value1, Date value2) {
            addCriterion("updatetime not between", value1, value2, "updatetime");
            return (Criteria) this;
        }

        public Criteria andTypeIsNull() {
            addCriterion("type is null");
            return (Criteria) this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("type is not null");
            return (Criteria) this;
        }

        public Criteria andTypeEqualTo(String value) {
            addCriterion("type =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(String value) {
            addCriterion("type <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(String value) {
            addCriterion("type >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(String value) {
            addCriterion("type >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(String value) {
            addCriterion("type <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(String value) {
            addCriterion("type <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLike(String value) {
            addCriterion("type like", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotLike(String value) {
            addCriterion("type not like", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<String> values) {
            addCriterion("type in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<String> values) {
            addCriterion("type not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(String value1, String value2) {
            addCriterion("type between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(String value1, String value2) {
            addCriterion("type not between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTitleIsNull() {
            addCriterion("title is null");
            return (Criteria) this;
        }

        public Criteria andTitleIsNotNull() {
            addCriterion("title is not null");
            return (Criteria) this;
        }

        public Criteria andTitleEqualTo(String value) {
            addCriterion("title =", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotEqualTo(String value) {
            addCriterion("title <>", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleGreaterThan(String value) {
            addCriterion("title >", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleGreaterThanOrEqualTo(String value) {
            addCriterion("title >=", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLessThan(String value) {
            addCriterion("title <", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLessThanOrEqualTo(String value) {
            addCriterion("title <=", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLike(String value) {
            addCriterion("title like", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotLike(String value) {
            addCriterion("title not like", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleIn(List<String> values) {
            addCriterion("title in", values, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotIn(List<String> values) {
            addCriterion("title not in", values, "title");
            return (Criteria) this;
        }

        public Criteria andTitleBetween(String value1, String value2) {
            addCriterion("title between", value1, value2, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotBetween(String value1, String value2) {
            addCriterion("title not between", value1, value2, "title");
            return (Criteria) this;
        }

        public Criteria andAddressIsNull() {
            addCriterion("address is null");
            return (Criteria) this;
        }

        public Criteria andAddressIsNotNull() {
            addCriterion("address is not null");
            return (Criteria) this;
        }

        public Criteria andAddressEqualTo(String value) {
            addCriterion("address =", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotEqualTo(String value) {
            addCriterion("address <>", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThan(String value) {
            addCriterion("address >", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThanOrEqualTo(String value) {
            addCriterion("address >=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThan(String value) {
            addCriterion("address <", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThanOrEqualTo(String value) {
            addCriterion("address <=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLike(String value) {
            addCriterion("address like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotLike(String value) {
            addCriterion("address not like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressIn(List<String> values) {
            addCriterion("address in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotIn(List<String> values) {
            addCriterion("address not in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressBetween(String value1, String value2) {
            addCriterion("address between", value1, value2, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotBetween(String value1, String value2) {
            addCriterion("address not between", value1, value2, "address");
            return (Criteria) this;
        }

        public Criteria andBankuaiIsNull() {
            addCriterion("bankuai is null");
            return (Criteria) this;
        }

        public Criteria andBankuaiIsNotNull() {
            addCriterion("bankuai is not null");
            return (Criteria) this;
        }

        public Criteria andBankuaiEqualTo(String value) {
            addCriterion("bankuai =", value, "bankuai");
            return (Criteria) this;
        }

        public Criteria andBankuaiNotEqualTo(String value) {
            addCriterion("bankuai <>", value, "bankuai");
            return (Criteria) this;
        }

        public Criteria andBankuaiGreaterThan(String value) {
            addCriterion("bankuai >", value, "bankuai");
            return (Criteria) this;
        }

        public Criteria andBankuaiGreaterThanOrEqualTo(String value) {
            addCriterion("bankuai >=", value, "bankuai");
            return (Criteria) this;
        }

        public Criteria andBankuaiLessThan(String value) {
            addCriterion("bankuai <", value, "bankuai");
            return (Criteria) this;
        }

        public Criteria andBankuaiLessThanOrEqualTo(String value) {
            addCriterion("bankuai <=", value, "bankuai");
            return (Criteria) this;
        }

        public Criteria andBankuaiLike(String value) {
            addCriterion("bankuai like", value, "bankuai");
            return (Criteria) this;
        }

        public Criteria andBankuaiNotLike(String value) {
            addCriterion("bankuai not like", value, "bankuai");
            return (Criteria) this;
        }

        public Criteria andBankuaiIn(List<String> values) {
            addCriterion("bankuai in", values, "bankuai");
            return (Criteria) this;
        }

        public Criteria andBankuaiNotIn(List<String> values) {
            addCriterion("bankuai not in", values, "bankuai");
            return (Criteria) this;
        }

        public Criteria andBankuaiBetween(String value1, String value2) {
            addCriterion("bankuai between", value1, value2, "bankuai");
            return (Criteria) this;
        }

        public Criteria andBankuaiNotBetween(String value1, String value2) {
            addCriterion("bankuai not between", value1, value2, "bankuai");
            return (Criteria) this;
        }

        public Criteria andZhuanyeIsNull() {
            addCriterion("zhuanye is null");
            return (Criteria) this;
        }

        public Criteria andZhuanyeIsNotNull() {
            addCriterion("zhuanye is not null");
            return (Criteria) this;
        }

        public Criteria andZhuanyeEqualTo(String value) {
            addCriterion("zhuanye =", value, "zhuanye");
            return (Criteria) this;
        }

        public Criteria andZhuanyeNotEqualTo(String value) {
            addCriterion("zhuanye <>", value, "zhuanye");
            return (Criteria) this;
        }

        public Criteria andZhuanyeGreaterThan(String value) {
            addCriterion("zhuanye >", value, "zhuanye");
            return (Criteria) this;
        }

        public Criteria andZhuanyeGreaterThanOrEqualTo(String value) {
            addCriterion("zhuanye >=", value, "zhuanye");
            return (Criteria) this;
        }

        public Criteria andZhuanyeLessThan(String value) {
            addCriterion("zhuanye <", value, "zhuanye");
            return (Criteria) this;
        }

        public Criteria andZhuanyeLessThanOrEqualTo(String value) {
            addCriterion("zhuanye <=", value, "zhuanye");
            return (Criteria) this;
        }

        public Criteria andZhuanyeLike(String value) {
            addCriterion("zhuanye like", value, "zhuanye");
            return (Criteria) this;
        }

        public Criteria andZhuanyeNotLike(String value) {
            addCriterion("zhuanye not like", value, "zhuanye");
            return (Criteria) this;
        }

        public Criteria andZhuanyeIn(List<String> values) {
            addCriterion("zhuanye in", values, "zhuanye");
            return (Criteria) this;
        }

        public Criteria andZhuanyeNotIn(List<String> values) {
            addCriterion("zhuanye not in", values, "zhuanye");
            return (Criteria) this;
        }

        public Criteria andZhuanyeBetween(String value1, String value2) {
            addCriterion("zhuanye between", value1, value2, "zhuanye");
            return (Criteria) this;
        }

        public Criteria andZhuanyeNotBetween(String value1, String value2) {
            addCriterion("zhuanye not between", value1, value2, "zhuanye");
            return (Criteria) this;
        }

        public Criteria andTeachtypeIsNull() {
            addCriterion("teachtype is null");
            return (Criteria) this;
        }

        public Criteria andTeachtypeIsNotNull() {
            addCriterion("teachtype is not null");
            return (Criteria) this;
        }

        public Criteria andTeachtypeEqualTo(String value) {
            addCriterion("teachtype =", value, "teachtype");
            return (Criteria) this;
        }

        public Criteria andTeachtypeNotEqualTo(String value) {
            addCriterion("teachtype <>", value, "teachtype");
            return (Criteria) this;
        }

        public Criteria andTeachtypeGreaterThan(String value) {
            addCriterion("teachtype >", value, "teachtype");
            return (Criteria) this;
        }

        public Criteria andTeachtypeGreaterThanOrEqualTo(String value) {
            addCriterion("teachtype >=", value, "teachtype");
            return (Criteria) this;
        }

        public Criteria andTeachtypeLessThan(String value) {
            addCriterion("teachtype <", value, "teachtype");
            return (Criteria) this;
        }

        public Criteria andTeachtypeLessThanOrEqualTo(String value) {
            addCriterion("teachtype <=", value, "teachtype");
            return (Criteria) this;
        }

        public Criteria andTeachtypeLike(String value) {
            addCriterion("teachtype like", value, "teachtype");
            return (Criteria) this;
        }

        public Criteria andTeachtypeNotLike(String value) {
            addCriterion("teachtype not like", value, "teachtype");
            return (Criteria) this;
        }

        public Criteria andTeachtypeIn(List<String> values) {
            addCriterion("teachtype in", values, "teachtype");
            return (Criteria) this;
        }

        public Criteria andTeachtypeNotIn(List<String> values) {
            addCriterion("teachtype not in", values, "teachtype");
            return (Criteria) this;
        }

        public Criteria andTeachtypeBetween(String value1, String value2) {
            addCriterion("teachtype between", value1, value2, "teachtype");
            return (Criteria) this;
        }

        public Criteria andTeachtypeNotBetween(String value1, String value2) {
            addCriterion("teachtype not between", value1, value2, "teachtype");
            return (Criteria) this;
        }

        public Criteria andContentIsNull() {
            addCriterion("content is null");
            return (Criteria) this;
        }

        public Criteria andContentIsNotNull() {
            addCriterion("content is not null");
            return (Criteria) this;
        }

        public Criteria andContentEqualTo(String value) {
            addCriterion("content =", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotEqualTo(String value) {
            addCriterion("content <>", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentGreaterThan(String value) {
            addCriterion("content >", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentGreaterThanOrEqualTo(String value) {
            addCriterion("content >=", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentLessThan(String value) {
            addCriterion("content <", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentLessThanOrEqualTo(String value) {
            addCriterion("content <=", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentLike(String value) {
            addCriterion("content like", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotLike(String value) {
            addCriterion("content not like", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentIn(List<String> values) {
            addCriterion("content in", values, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotIn(List<String> values) {
            addCriterion("content not in", values, "content");
            return (Criteria) this;
        }

        public Criteria andContentBetween(String value1, String value2) {
            addCriterion("content between", value1, value2, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotBetween(String value1, String value2) {
            addCriterion("content not between", value1, value2, "content");
            return (Criteria) this;
        }

        public Criteria andTeachtimeIsNull() {
            addCriterion("teachtime is null");
            return (Criteria) this;
        }

        public Criteria andTeachtimeIsNotNull() {
            addCriterion("teachtime is not null");
            return (Criteria) this;
        }

        public Criteria andTeachtimeEqualTo(String value) {
            addCriterion("teachtime =", value, "teachtime");
            return (Criteria) this;
        }

        public Criteria andTeachtimeNotEqualTo(String value) {
            addCriterion("teachtime <>", value, "teachtime");
            return (Criteria) this;
        }

        public Criteria andTeachtimeGreaterThan(String value) {
            addCriterion("teachtime >", value, "teachtime");
            return (Criteria) this;
        }

        public Criteria andTeachtimeGreaterThanOrEqualTo(String value) {
            addCriterion("teachtime >=", value, "teachtime");
            return (Criteria) this;
        }

        public Criteria andTeachtimeLessThan(String value) {
            addCriterion("teachtime <", value, "teachtime");
            return (Criteria) this;
        }

        public Criteria andTeachtimeLessThanOrEqualTo(String value) {
            addCriterion("teachtime <=", value, "teachtime");
            return (Criteria) this;
        }

        public Criteria andTeachtimeLike(String value) {
            addCriterion("teachtime like", value, "teachtime");
            return (Criteria) this;
        }

        public Criteria andTeachtimeNotLike(String value) {
            addCriterion("teachtime not like", value, "teachtime");
            return (Criteria) this;
        }

        public Criteria andTeachtimeIn(List<String> values) {
            addCriterion("teachtime in", values, "teachtime");
            return (Criteria) this;
        }

        public Criteria andTeachtimeNotIn(List<String> values) {
            addCriterion("teachtime not in", values, "teachtime");
            return (Criteria) this;
        }

        public Criteria andTeachtimeBetween(String value1, String value2) {
            addCriterion("teachtime between", value1, value2, "teachtime");
            return (Criteria) this;
        }

        public Criteria andTeachtimeNotBetween(String value1, String value2) {
            addCriterion("teachtime not between", value1, value2, "teachtime");
            return (Criteria) this;
        }

        public Criteria andPeopleIsNull() {
            addCriterion("people is null");
            return (Criteria) this;
        }

        public Criteria andPeopleIsNotNull() {
            addCriterion("people is not null");
            return (Criteria) this;
        }

        public Criteria andPeopleEqualTo(Integer value) {
            addCriterion("people =", value, "people");
            return (Criteria) this;
        }

        public Criteria andPeopleNotEqualTo(Integer value) {
            addCriterion("people <>", value, "people");
            return (Criteria) this;
        }

        public Criteria andPeopleGreaterThan(Integer value) {
            addCriterion("people >", value, "people");
            return (Criteria) this;
        }

        public Criteria andPeopleGreaterThanOrEqualTo(Integer value) {
            addCriterion("people >=", value, "people");
            return (Criteria) this;
        }

        public Criteria andPeopleLessThan(Integer value) {
            addCriterion("people <", value, "people");
            return (Criteria) this;
        }

        public Criteria andPeopleLessThanOrEqualTo(Integer value) {
            addCriterion("people <=", value, "people");
            return (Criteria) this;
        }

        public Criteria andPeopleIn(List<Integer> values) {
            addCriterion("people in", values, "people");
            return (Criteria) this;
        }

        public Criteria andPeopleNotIn(List<Integer> values) {
            addCriterion("people not in", values, "people");
            return (Criteria) this;
        }

        public Criteria andPeopleBetween(Integer value1, Integer value2) {
            addCriterion("people between", value1, value2, "people");
            return (Criteria) this;
        }

        public Criteria andPeopleNotBetween(Integer value1, Integer value2) {
            addCriterion("people not between", value1, value2, "people");
            return (Criteria) this;
        }

        public Criteria andReplynumberIsNull() {
            addCriterion("replynumber is null");
            return (Criteria) this;
        }

        public Criteria andReplynumberIsNotNull() {
            addCriterion("replynumber is not null");
            return (Criteria) this;
        }

        public Criteria andReplynumberEqualTo(Integer value) {
            addCriterion("replynumber =", value, "replynumber");
            return (Criteria) this;
        }

        public Criteria andReplynumberNotEqualTo(Integer value) {
            addCriterion("replynumber <>", value, "replynumber");
            return (Criteria) this;
        }

        public Criteria andReplynumberGreaterThan(Integer value) {
            addCriterion("replynumber >", value, "replynumber");
            return (Criteria) this;
        }

        public Criteria andReplynumberGreaterThanOrEqualTo(Integer value) {
            addCriterion("replynumber >=", value, "replynumber");
            return (Criteria) this;
        }

        public Criteria andReplynumberLessThan(Integer value) {
            addCriterion("replynumber <", value, "replynumber");
            return (Criteria) this;
        }

        public Criteria andReplynumberLessThanOrEqualTo(Integer value) {
            addCriterion("replynumber <=", value, "replynumber");
            return (Criteria) this;
        }

        public Criteria andReplynumberIn(List<Integer> values) {
            addCriterion("replynumber in", values, "replynumber");
            return (Criteria) this;
        }

        public Criteria andReplynumberNotIn(List<Integer> values) {
            addCriterion("replynumber not in", values, "replynumber");
            return (Criteria) this;
        }

        public Criteria andReplynumberBetween(Integer value1, Integer value2) {
            addCriterion("replynumber between", value1, value2, "replynumber");
            return (Criteria) this;
        }

        public Criteria andReplynumberNotBetween(Integer value1, Integer value2) {
            addCriterion("replynumber not between", value1, value2, "replynumber");
            return (Criteria) this;
        }

        public Criteria andClicknumberIsNull() {
            addCriterion("clicknumber is null");
            return (Criteria) this;
        }

        public Criteria andClicknumberIsNotNull() {
            addCriterion("clicknumber is not null");
            return (Criteria) this;
        }

        public Criteria andClicknumberEqualTo(Integer value) {
            addCriterion("clicknumber =", value, "clicknumber");
            return (Criteria) this;
        }

        public Criteria andClicknumberNotEqualTo(Integer value) {
            addCriterion("clicknumber <>", value, "clicknumber");
            return (Criteria) this;
        }

        public Criteria andClicknumberGreaterThan(Integer value) {
            addCriterion("clicknumber >", value, "clicknumber");
            return (Criteria) this;
        }

        public Criteria andClicknumberGreaterThanOrEqualTo(Integer value) {
            addCriterion("clicknumber >=", value, "clicknumber");
            return (Criteria) this;
        }

        public Criteria andClicknumberLessThan(Integer value) {
            addCriterion("clicknumber <", value, "clicknumber");
            return (Criteria) this;
        }

        public Criteria andClicknumberLessThanOrEqualTo(Integer value) {
            addCriterion("clicknumber <=", value, "clicknumber");
            return (Criteria) this;
        }

        public Criteria andClicknumberIn(List<Integer> values) {
            addCriterion("clicknumber in", values, "clicknumber");
            return (Criteria) this;
        }

        public Criteria andClicknumberNotIn(List<Integer> values) {
            addCriterion("clicknumber not in", values, "clicknumber");
            return (Criteria) this;
        }

        public Criteria andClicknumberBetween(Integer value1, Integer value2) {
            addCriterion("clicknumber between", value1, value2, "clicknumber");
            return (Criteria) this;
        }

        public Criteria andClicknumberNotBetween(Integer value1, Integer value2) {
            addCriterion("clicknumber not between", value1, value2, "clicknumber");
            return (Criteria) this;
        }

        public Criteria andTagIsNull() {
            addCriterion("tag is null");
            return (Criteria) this;
        }

        public Criteria andTagIsNotNull() {
            addCriterion("tag is not null");
            return (Criteria) this;
        }

        public Criteria andTagEqualTo(String value) {
            addCriterion("tag =", value, "tag");
            return (Criteria) this;
        }

        public Criteria andTagNotEqualTo(String value) {
            addCriterion("tag <>", value, "tag");
            return (Criteria) this;
        }

        public Criteria andTagGreaterThan(String value) {
            addCriterion("tag >", value, "tag");
            return (Criteria) this;
        }

        public Criteria andTagGreaterThanOrEqualTo(String value) {
            addCriterion("tag >=", value, "tag");
            return (Criteria) this;
        }

        public Criteria andTagLessThan(String value) {
            addCriterion("tag <", value, "tag");
            return (Criteria) this;
        }

        public Criteria andTagLessThanOrEqualTo(String value) {
            addCriterion("tag <=", value, "tag");
            return (Criteria) this;
        }

        public Criteria andTagLike(String value) {
            addCriterion("tag like", value, "tag");
            return (Criteria) this;
        }

        public Criteria andTagNotLike(String value) {
            addCriterion("tag not like", value, "tag");
            return (Criteria) this;
        }

        public Criteria andTagIn(List<String> values) {
            addCriterion("tag in", values, "tag");
            return (Criteria) this;
        }

        public Criteria andTagNotIn(List<String> values) {
            addCriterion("tag not in", values, "tag");
            return (Criteria) this;
        }

        public Criteria andTagBetween(String value1, String value2) {
            addCriterion("tag between", value1, value2, "tag");
            return (Criteria) this;
        }

        public Criteria andTagNotBetween(String value1, String value2) {
            addCriterion("tag not between", value1, value2, "tag");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(String value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(String value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(String value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(String value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(String value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(String value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLike(String value) {
            addCriterion("status like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotLike(String value) {
            addCriterion("status not like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<String> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<String> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(String value1, String value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(String value1, String value2) {
            addCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andPicturesIsNull() {
            addCriterion("pictures is null");
            return (Criteria) this;
        }

        public Criteria andPicturesIsNotNull() {
            addCriterion("pictures is not null");
            return (Criteria) this;
        }

        public Criteria andPicturesEqualTo(String value) {
            addCriterion("pictures =", value, "pictures");
            return (Criteria) this;
        }

        public Criteria andPicturesNotEqualTo(String value) {
            addCriterion("pictures <>", value, "pictures");
            return (Criteria) this;
        }

        public Criteria andPicturesGreaterThan(String value) {
            addCriterion("pictures >", value, "pictures");
            return (Criteria) this;
        }

        public Criteria andPicturesGreaterThanOrEqualTo(String value) {
            addCriterion("pictures >=", value, "pictures");
            return (Criteria) this;
        }

        public Criteria andPicturesLessThan(String value) {
            addCriterion("pictures <", value, "pictures");
            return (Criteria) this;
        }

        public Criteria andPicturesLessThanOrEqualTo(String value) {
            addCriterion("pictures <=", value, "pictures");
            return (Criteria) this;
        }

        public Criteria andPicturesLike(String value) {
            addCriterion("pictures like", value, "pictures");
            return (Criteria) this;
        }

        public Criteria andPicturesNotLike(String value) {
            addCriterion("pictures not like", value, "pictures");
            return (Criteria) this;
        }

        public Criteria andPicturesIn(List<String> values) {
            addCriterion("pictures in", values, "pictures");
            return (Criteria) this;
        }

        public Criteria andPicturesNotIn(List<String> values) {
            addCriterion("pictures not in", values, "pictures");
            return (Criteria) this;
        }

        public Criteria andPicturesBetween(String value1, String value2) {
            addCriterion("pictures between", value1, value2, "pictures");
            return (Criteria) this;
        }

        public Criteria andPicturesNotBetween(String value1, String value2) {
            addCriterion("pictures not between", value1, value2, "pictures");
            return (Criteria) this;
        }

        public Criteria andInfoIsNull() {
            addCriterion("info is null");
            return (Criteria) this;
        }

        public Criteria andInfoIsNotNull() {
            addCriterion("info is not null");
            return (Criteria) this;
        }

        public Criteria andInfoEqualTo(String value) {
            addCriterion("info =", value, "info");
            return (Criteria) this;
        }

        public Criteria andInfoNotEqualTo(String value) {
            addCriterion("info <>", value, "info");
            return (Criteria) this;
        }

        public Criteria andInfoGreaterThan(String value) {
            addCriterion("info >", value, "info");
            return (Criteria) this;
        }

        public Criteria andInfoGreaterThanOrEqualTo(String value) {
            addCriterion("info >=", value, "info");
            return (Criteria) this;
        }

        public Criteria andInfoLessThan(String value) {
            addCriterion("info <", value, "info");
            return (Criteria) this;
        }

        public Criteria andInfoLessThanOrEqualTo(String value) {
            addCriterion("info <=", value, "info");
            return (Criteria) this;
        }

        public Criteria andInfoLike(String value) {
            addCriterion("info like", value, "info");
            return (Criteria) this;
        }

        public Criteria andInfoNotLike(String value) {
            addCriterion("info not like", value, "info");
            return (Criteria) this;
        }

        public Criteria andInfoIn(List<String> values) {
            addCriterion("info in", values, "info");
            return (Criteria) this;
        }

        public Criteria andInfoNotIn(List<String> values) {
            addCriterion("info not in", values, "info");
            return (Criteria) this;
        }

        public Criteria andInfoBetween(String value1, String value2) {
            addCriterion("info between", value1, value2, "info");
            return (Criteria) this;
        }

        public Criteria andInfoNotBetween(String value1, String value2) {
            addCriterion("info not between", value1, value2, "info");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}