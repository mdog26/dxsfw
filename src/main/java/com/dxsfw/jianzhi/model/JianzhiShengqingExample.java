package com.dxsfw.jianzhi.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.dxsfw.common.base.BaseExample;

public class JianzhiShengqingExample extends BaseExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public JianzhiShengqingExample() {
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

        public Criteria andShenqingidIsNull() {
            addCriterion("shenqingid is null");
            return (Criteria) this;
        }

        public Criteria andShenqingidIsNotNull() {
            addCriterion("shenqingid is not null");
            return (Criteria) this;
        }

        public Criteria andShenqingidEqualTo(Integer value) {
            addCriterion("shenqingid =", value, "shenqingid");
            return (Criteria) this;
        }

        public Criteria andShenqingidNotEqualTo(Integer value) {
            addCriterion("shenqingid <>", value, "shenqingid");
            return (Criteria) this;
        }

        public Criteria andShenqingidGreaterThan(Integer value) {
            addCriterion("shenqingid >", value, "shenqingid");
            return (Criteria) this;
        }

        public Criteria andShenqingidGreaterThanOrEqualTo(Integer value) {
            addCriterion("shenqingid >=", value, "shenqingid");
            return (Criteria) this;
        }

        public Criteria andShenqingidLessThan(Integer value) {
            addCriterion("shenqingid <", value, "shenqingid");
            return (Criteria) this;
        }

        public Criteria andShenqingidLessThanOrEqualTo(Integer value) {
            addCriterion("shenqingid <=", value, "shenqingid");
            return (Criteria) this;
        }

        public Criteria andShenqingidIn(List<Integer> values) {
            addCriterion("shenqingid in", values, "shenqingid");
            return (Criteria) this;
        }

        public Criteria andShenqingidNotIn(List<Integer> values) {
            addCriterion("shenqingid not in", values, "shenqingid");
            return (Criteria) this;
        }

        public Criteria andShenqingidBetween(Integer value1, Integer value2) {
            addCriterion("shenqingid between", value1, value2, "shenqingid");
            return (Criteria) this;
        }

        public Criteria andShenqingidNotBetween(Integer value1, Integer value2) {
            addCriterion("shenqingid not between", value1, value2, "shenqingid");
            return (Criteria) this;
        }

        public Criteria andJianzhiidIsNull() {
            addCriterion("jianzhiid is null");
            return (Criteria) this;
        }

        public Criteria andJianzhiidIsNotNull() {
            addCriterion("jianzhiid is not null");
            return (Criteria) this;
        }

        public Criteria andJianzhiidEqualTo(Integer value) {
            addCriterion("jianzhiid =", value, "jianzhiid");
            return (Criteria) this;
        }

        public Criteria andJianzhiidNotEqualTo(Integer value) {
            addCriterion("jianzhiid <>", value, "jianzhiid");
            return (Criteria) this;
        }

        public Criteria andJianzhiidGreaterThan(Integer value) {
            addCriterion("jianzhiid >", value, "jianzhiid");
            return (Criteria) this;
        }

        public Criteria andJianzhiidGreaterThanOrEqualTo(Integer value) {
            addCriterion("jianzhiid >=", value, "jianzhiid");
            return (Criteria) this;
        }

        public Criteria andJianzhiidLessThan(Integer value) {
            addCriterion("jianzhiid <", value, "jianzhiid");
            return (Criteria) this;
        }

        public Criteria andJianzhiidLessThanOrEqualTo(Integer value) {
            addCriterion("jianzhiid <=", value, "jianzhiid");
            return (Criteria) this;
        }

        public Criteria andJianzhiidIn(List<Integer> values) {
            addCriterion("jianzhiid in", values, "jianzhiid");
            return (Criteria) this;
        }

        public Criteria andJianzhiidNotIn(List<Integer> values) {
            addCriterion("jianzhiid not in", values, "jianzhiid");
            return (Criteria) this;
        }

        public Criteria andJianzhiidBetween(Integer value1, Integer value2) {
            addCriterion("jianzhiid between", value1, value2, "jianzhiid");
            return (Criteria) this;
        }

        public Criteria andJianzhiidNotBetween(Integer value1, Integer value2) {
            addCriterion("jianzhiid not between", value1, value2, "jianzhiid");
            return (Criteria) this;
        }

        public Criteria andPublishuseridIsNull() {
            addCriterion("publishUserid is null");
            return (Criteria) this;
        }

        public Criteria andPublishuseridIsNotNull() {
            addCriterion("publishUserid is not null");
            return (Criteria) this;
        }

        public Criteria andPublishuseridEqualTo(Integer value) {
            addCriterion("publishUserid =", value, "publishuserid");
            return (Criteria) this;
        }

        public Criteria andPublishuseridNotEqualTo(Integer value) {
            addCriterion("publishUserid <>", value, "publishuserid");
            return (Criteria) this;
        }

        public Criteria andPublishuseridGreaterThan(Integer value) {
            addCriterion("publishUserid >", value, "publishuserid");
            return (Criteria) this;
        }

        public Criteria andPublishuseridGreaterThanOrEqualTo(Integer value) {
            addCriterion("publishUserid >=", value, "publishuserid");
            return (Criteria) this;
        }

        public Criteria andPublishuseridLessThan(Integer value) {
            addCriterion("publishUserid <", value, "publishuserid");
            return (Criteria) this;
        }

        public Criteria andPublishuseridLessThanOrEqualTo(Integer value) {
            addCriterion("publishUserid <=", value, "publishuserid");
            return (Criteria) this;
        }

        public Criteria andPublishuseridIn(List<Integer> values) {
            addCriterion("publishUserid in", values, "publishuserid");
            return (Criteria) this;
        }

        public Criteria andPublishuseridNotIn(List<Integer> values) {
            addCriterion("publishUserid not in", values, "publishuserid");
            return (Criteria) this;
        }

        public Criteria andPublishuseridBetween(Integer value1, Integer value2) {
            addCriterion("publishUserid between", value1, value2, "publishuserid");
            return (Criteria) this;
        }

        public Criteria andPublishuseridNotBetween(Integer value1, Integer value2) {
            addCriterion("publishUserid not between", value1, value2, "publishuserid");
            return (Criteria) this;
        }

        public Criteria andShengqinguseridIsNull() {
            addCriterion("shengqingUserid is null");
            return (Criteria) this;
        }

        public Criteria andShengqinguseridIsNotNull() {
            addCriterion("shengqingUserid is not null");
            return (Criteria) this;
        }

        public Criteria andShengqinguseridEqualTo(Integer value) {
            addCriterion("shengqingUserid =", value, "shengqinguserid");
            return (Criteria) this;
        }

        public Criteria andShengqinguseridNotEqualTo(Integer value) {
            addCriterion("shengqingUserid <>", value, "shengqinguserid");
            return (Criteria) this;
        }

        public Criteria andShengqinguseridGreaterThan(Integer value) {
            addCriterion("shengqingUserid >", value, "shengqinguserid");
            return (Criteria) this;
        }

        public Criteria andShengqinguseridGreaterThanOrEqualTo(Integer value) {
            addCriterion("shengqingUserid >=", value, "shengqinguserid");
            return (Criteria) this;
        }

        public Criteria andShengqinguseridLessThan(Integer value) {
            addCriterion("shengqingUserid <", value, "shengqinguserid");
            return (Criteria) this;
        }

        public Criteria andShengqinguseridLessThanOrEqualTo(Integer value) {
            addCriterion("shengqingUserid <=", value, "shengqinguserid");
            return (Criteria) this;
        }

        public Criteria andShengqinguseridIn(List<Integer> values) {
            addCriterion("shengqingUserid in", values, "shengqinguserid");
            return (Criteria) this;
        }

        public Criteria andShengqinguseridNotIn(List<Integer> values) {
            addCriterion("shengqingUserid not in", values, "shengqinguserid");
            return (Criteria) this;
        }

        public Criteria andShengqinguseridBetween(Integer value1, Integer value2) {
            addCriterion("shengqingUserid between", value1, value2, "shengqinguserid");
            return (Criteria) this;
        }

        public Criteria andShengqinguseridNotBetween(Integer value1, Integer value2) {
            addCriterion("shengqingUserid not between", value1, value2, "shengqinguserid");
            return (Criteria) this;
        }

        public Criteria andJianliidIsNull() {
            addCriterion("jianliid is null");
            return (Criteria) this;
        }

        public Criteria andJianliidIsNotNull() {
            addCriterion("jianliid is not null");
            return (Criteria) this;
        }

        public Criteria andJianliidEqualTo(Integer value) {
            addCriterion("jianliid =", value, "jianliid");
            return (Criteria) this;
        }

        public Criteria andJianliidNotEqualTo(Integer value) {
            addCriterion("jianliid <>", value, "jianliid");
            return (Criteria) this;
        }

        public Criteria andJianliidGreaterThan(Integer value) {
            addCriterion("jianliid >", value, "jianliid");
            return (Criteria) this;
        }

        public Criteria andJianliidGreaterThanOrEqualTo(Integer value) {
            addCriterion("jianliid >=", value, "jianliid");
            return (Criteria) this;
        }

        public Criteria andJianliidLessThan(Integer value) {
            addCriterion("jianliid <", value, "jianliid");
            return (Criteria) this;
        }

        public Criteria andJianliidLessThanOrEqualTo(Integer value) {
            addCriterion("jianliid <=", value, "jianliid");
            return (Criteria) this;
        }

        public Criteria andJianliidIn(List<Integer> values) {
            addCriterion("jianliid in", values, "jianliid");
            return (Criteria) this;
        }

        public Criteria andJianliidNotIn(List<Integer> values) {
            addCriterion("jianliid not in", values, "jianliid");
            return (Criteria) this;
        }

        public Criteria andJianliidBetween(Integer value1, Integer value2) {
            addCriterion("jianliid between", value1, value2, "jianliid");
            return (Criteria) this;
        }

        public Criteria andJianliidNotBetween(Integer value1, Integer value2) {
            addCriterion("jianliid not between", value1, value2, "jianliid");
            return (Criteria) this;
        }

        public Criteria andTimeIsNull() {
            addCriterion("time is null");
            return (Criteria) this;
        }

        public Criteria andTimeIsNotNull() {
            addCriterion("time is not null");
            return (Criteria) this;
        }

        public Criteria andTimeEqualTo(Date value) {
            addCriterion("time =", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeNotEqualTo(Date value) {
            addCriterion("time <>", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeGreaterThan(Date value) {
            addCriterion("time >", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("time >=", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeLessThan(Date value) {
            addCriterion("time <", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeLessThanOrEqualTo(Date value) {
            addCriterion("time <=", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeIn(List<Date> values) {
            addCriterion("time in", values, "time");
            return (Criteria) this;
        }

        public Criteria andTimeNotIn(List<Date> values) {
            addCriterion("time not in", values, "time");
            return (Criteria) this;
        }

        public Criteria andTimeBetween(Date value1, Date value2) {
            addCriterion("time between", value1, value2, "time");
            return (Criteria) this;
        }

        public Criteria andTimeNotBetween(Date value1, Date value2) {
            addCriterion("time not between", value1, value2, "time");
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