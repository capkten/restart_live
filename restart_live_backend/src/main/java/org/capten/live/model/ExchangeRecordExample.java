package org.capten.live.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ExchangeRecordExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table public.exchange_record
     *
     * @mbg.generated Wed Jan 22 22:27:35 CST 2025
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table public.exchange_record
     *
     * @mbg.generated Wed Jan 22 22:27:35 CST 2025
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table public.exchange_record
     *
     * @mbg.generated Wed Jan 22 22:27:35 CST 2025
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.exchange_record
     *
     * @mbg.generated Wed Jan 22 22:27:35 CST 2025
     */
    public ExchangeRecordExample() {
        oredCriteria = new ArrayList<>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.exchange_record
     *
     * @mbg.generated Wed Jan 22 22:27:35 CST 2025
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.exchange_record
     *
     * @mbg.generated Wed Jan 22 22:27:35 CST 2025
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.exchange_record
     *
     * @mbg.generated Wed Jan 22 22:27:35 CST 2025
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.exchange_record
     *
     * @mbg.generated Wed Jan 22 22:27:35 CST 2025
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.exchange_record
     *
     * @mbg.generated Wed Jan 22 22:27:35 CST 2025
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.exchange_record
     *
     * @mbg.generated Wed Jan 22 22:27:35 CST 2025
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.exchange_record
     *
     * @mbg.generated Wed Jan 22 22:27:35 CST 2025
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.exchange_record
     *
     * @mbg.generated Wed Jan 22 22:27:35 CST 2025
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.exchange_record
     *
     * @mbg.generated Wed Jan 22 22:27:35 CST 2025
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.exchange_record
     *
     * @mbg.generated Wed Jan 22 22:27:35 CST 2025
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table public.exchange_record
     *
     * @mbg.generated Wed Jan 22 22:27:35 CST 2025
     */
    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<>();
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

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Object value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Object value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Object value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Object value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Object value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Object value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Object> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Object> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Object value1, Object value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Object value1, Object value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andAsIdIsNull() {
            addCriterion("as_id is null");
            return (Criteria) this;
        }

        public Criteria andAsIdIsNotNull() {
            addCriterion("as_id is not null");
            return (Criteria) this;
        }

        public Criteria andAsIdEqualTo(Object value) {
            addCriterion("as_id =", value, "asId");
            return (Criteria) this;
        }

        public Criteria andAsIdNotEqualTo(Object value) {
            addCriterion("as_id <>", value, "asId");
            return (Criteria) this;
        }

        public Criteria andAsIdGreaterThan(Object value) {
            addCriterion("as_id >", value, "asId");
            return (Criteria) this;
        }

        public Criteria andAsIdGreaterThanOrEqualTo(Object value) {
            addCriterion("as_id >=", value, "asId");
            return (Criteria) this;
        }

        public Criteria andAsIdLessThan(Object value) {
            addCriterion("as_id <", value, "asId");
            return (Criteria) this;
        }

        public Criteria andAsIdLessThanOrEqualTo(Object value) {
            addCriterion("as_id <=", value, "asId");
            return (Criteria) this;
        }

        public Criteria andAsIdIn(List<Object> values) {
            addCriterion("as_id in", values, "asId");
            return (Criteria) this;
        }

        public Criteria andAsIdNotIn(List<Object> values) {
            addCriterion("as_id not in", values, "asId");
            return (Criteria) this;
        }

        public Criteria andAsIdBetween(Object value1, Object value2) {
            addCriterion("as_id between", value1, value2, "asId");
            return (Criteria) this;
        }

        public Criteria andAsIdNotBetween(Object value1, Object value2) {
            addCriterion("as_id not between", value1, value2, "asId");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNull() {
            addCriterion("user_id is null");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("user_id is not null");
            return (Criteria) this;
        }

        public Criteria andUserIdEqualTo(Object value) {
            addCriterion("user_id =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(Object value) {
            addCriterion("user_id <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(Object value) {
            addCriterion("user_id >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(Object value) {
            addCriterion("user_id >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(Object value) {
            addCriterion("user_id <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(Object value) {
            addCriterion("user_id <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<Object> values) {
            addCriterion("user_id in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<Object> values) {
            addCriterion("user_id not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(Object value1, Object value2) {
            addCriterion("user_id between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(Object value1, Object value2) {
            addCriterion("user_id not between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("update_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("update_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("update_time =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("update_time <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("update_time >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("update_time >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("update_time <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("update_time <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("update_time in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("update_time not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("update_time between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("update_time not between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andIsDeleteIsNull() {
            addCriterion("is_delete is null");
            return (Criteria) this;
        }

        public Criteria andIsDeleteIsNotNull() {
            addCriterion("is_delete is not null");
            return (Criteria) this;
        }

        public Criteria andIsDeleteEqualTo(Boolean value) {
            addCriterion("is_delete =", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteNotEqualTo(Boolean value) {
            addCriterion("is_delete <>", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteGreaterThan(Boolean value) {
            addCriterion("is_delete >", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteGreaterThanOrEqualTo(Boolean value) {
            addCriterion("is_delete >=", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteLessThan(Boolean value) {
            addCriterion("is_delete <", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteLessThanOrEqualTo(Boolean value) {
            addCriterion("is_delete <=", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteIn(List<Boolean> values) {
            addCriterion("is_delete in", values, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteNotIn(List<Boolean> values) {
            addCriterion("is_delete not in", values, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteBetween(Boolean value1, Boolean value2) {
            addCriterion("is_delete between", value1, value2, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteNotBetween(Boolean value1, Boolean value2) {
            addCriterion("is_delete not between", value1, value2, "isDelete");
            return (Criteria) this;
        }

        public Criteria andStarsIsNull() {
            addCriterion("stars is null");
            return (Criteria) this;
        }

        public Criteria andStarsIsNotNull() {
            addCriterion("stars is not null");
            return (Criteria) this;
        }

        public Criteria andStarsEqualTo(Integer value) {
            addCriterion("stars =", value, "stars");
            return (Criteria) this;
        }

        public Criteria andStarsNotEqualTo(Integer value) {
            addCriterion("stars <>", value, "stars");
            return (Criteria) this;
        }

        public Criteria andStarsGreaterThan(Integer value) {
            addCriterion("stars >", value, "stars");
            return (Criteria) this;
        }

        public Criteria andStarsGreaterThanOrEqualTo(Integer value) {
            addCriterion("stars >=", value, "stars");
            return (Criteria) this;
        }

        public Criteria andStarsLessThan(Integer value) {
            addCriterion("stars <", value, "stars");
            return (Criteria) this;
        }

        public Criteria andStarsLessThanOrEqualTo(Integer value) {
            addCriterion("stars <=", value, "stars");
            return (Criteria) this;
        }

        public Criteria andStarsIn(List<Integer> values) {
            addCriterion("stars in", values, "stars");
            return (Criteria) this;
        }

        public Criteria andStarsNotIn(List<Integer> values) {
            addCriterion("stars not in", values, "stars");
            return (Criteria) this;
        }

        public Criteria andStarsBetween(Integer value1, Integer value2) {
            addCriterion("stars between", value1, value2, "stars");
            return (Criteria) this;
        }

        public Criteria andStarsNotBetween(Integer value1, Integer value2) {
            addCriterion("stars not between", value1, value2, "stars");
            return (Criteria) this;
        }

        public Criteria andAsNameIsNull() {
            addCriterion("as_name is null");
            return (Criteria) this;
        }

        public Criteria andAsNameIsNotNull() {
            addCriterion("as_name is not null");
            return (Criteria) this;
        }

        public Criteria andAsNameEqualTo(String value) {
            addCriterion("as_name =", value, "asName");
            return (Criteria) this;
        }

        public Criteria andAsNameNotEqualTo(String value) {
            addCriterion("as_name <>", value, "asName");
            return (Criteria) this;
        }

        public Criteria andAsNameGreaterThan(String value) {
            addCriterion("as_name >", value, "asName");
            return (Criteria) this;
        }

        public Criteria andAsNameGreaterThanOrEqualTo(String value) {
            addCriterion("as_name >=", value, "asName");
            return (Criteria) this;
        }

        public Criteria andAsNameLessThan(String value) {
            addCriterion("as_name <", value, "asName");
            return (Criteria) this;
        }

        public Criteria andAsNameLessThanOrEqualTo(String value) {
            addCriterion("as_name <=", value, "asName");
            return (Criteria) this;
        }

        public Criteria andAsNameLike(String value) {
            addCriterion("as_name like", value, "asName");
            return (Criteria) this;
        }

        public Criteria andAsNameNotLike(String value) {
            addCriterion("as_name not like", value, "asName");
            return (Criteria) this;
        }

        public Criteria andAsNameIn(List<String> values) {
            addCriterion("as_name in", values, "asName");
            return (Criteria) this;
        }

        public Criteria andAsNameNotIn(List<String> values) {
            addCriterion("as_name not in", values, "asName");
            return (Criteria) this;
        }

        public Criteria andAsNameBetween(String value1, String value2) {
            addCriterion("as_name between", value1, value2, "asName");
            return (Criteria) this;
        }

        public Criteria andAsNameNotBetween(String value1, String value2) {
            addCriterion("as_name not between", value1, value2, "asName");
            return (Criteria) this;
        }

        public Criteria andAsCoverUrlIsNull() {
            addCriterion("as_cover_url is null");
            return (Criteria) this;
        }

        public Criteria andAsCoverUrlIsNotNull() {
            addCriterion("as_cover_url is not null");
            return (Criteria) this;
        }

        public Criteria andAsCoverUrlEqualTo(String value) {
            addCriterion("as_cover_url =", value, "asCoverUrl");
            return (Criteria) this;
        }

        public Criteria andAsCoverUrlNotEqualTo(String value) {
            addCriterion("as_cover_url <>", value, "asCoverUrl");
            return (Criteria) this;
        }

        public Criteria andAsCoverUrlGreaterThan(String value) {
            addCriterion("as_cover_url >", value, "asCoverUrl");
            return (Criteria) this;
        }

        public Criteria andAsCoverUrlGreaterThanOrEqualTo(String value) {
            addCriterion("as_cover_url >=", value, "asCoverUrl");
            return (Criteria) this;
        }

        public Criteria andAsCoverUrlLessThan(String value) {
            addCriterion("as_cover_url <", value, "asCoverUrl");
            return (Criteria) this;
        }

        public Criteria andAsCoverUrlLessThanOrEqualTo(String value) {
            addCriterion("as_cover_url <=", value, "asCoverUrl");
            return (Criteria) this;
        }

        public Criteria andAsCoverUrlLike(String value) {
            addCriterion("as_cover_url like", value, "asCoverUrl");
            return (Criteria) this;
        }

        public Criteria andAsCoverUrlNotLike(String value) {
            addCriterion("as_cover_url not like", value, "asCoverUrl");
            return (Criteria) this;
        }

        public Criteria andAsCoverUrlIn(List<String> values) {
            addCriterion("as_cover_url in", values, "asCoverUrl");
            return (Criteria) this;
        }

        public Criteria andAsCoverUrlNotIn(List<String> values) {
            addCriterion("as_cover_url not in", values, "asCoverUrl");
            return (Criteria) this;
        }

        public Criteria andAsCoverUrlBetween(String value1, String value2) {
            addCriterion("as_cover_url between", value1, value2, "asCoverUrl");
            return (Criteria) this;
        }

        public Criteria andAsCoverUrlNotBetween(String value1, String value2) {
            addCriterion("as_cover_url not between", value1, value2, "asCoverUrl");
            return (Criteria) this;
        }

        public Criteria andAsIntroduceIsNull() {
            addCriterion("as_introduce is null");
            return (Criteria) this;
        }

        public Criteria andAsIntroduceIsNotNull() {
            addCriterion("as_introduce is not null");
            return (Criteria) this;
        }

        public Criteria andAsIntroduceEqualTo(String value) {
            addCriterion("as_introduce =", value, "asIntroduce");
            return (Criteria) this;
        }

        public Criteria andAsIntroduceNotEqualTo(String value) {
            addCriterion("as_introduce <>", value, "asIntroduce");
            return (Criteria) this;
        }

        public Criteria andAsIntroduceGreaterThan(String value) {
            addCriterion("as_introduce >", value, "asIntroduce");
            return (Criteria) this;
        }

        public Criteria andAsIntroduceGreaterThanOrEqualTo(String value) {
            addCriterion("as_introduce >=", value, "asIntroduce");
            return (Criteria) this;
        }

        public Criteria andAsIntroduceLessThan(String value) {
            addCriterion("as_introduce <", value, "asIntroduce");
            return (Criteria) this;
        }

        public Criteria andAsIntroduceLessThanOrEqualTo(String value) {
            addCriterion("as_introduce <=", value, "asIntroduce");
            return (Criteria) this;
        }

        public Criteria andAsIntroduceLike(String value) {
            addCriterion("as_introduce like", value, "asIntroduce");
            return (Criteria) this;
        }

        public Criteria andAsIntroduceNotLike(String value) {
            addCriterion("as_introduce not like", value, "asIntroduce");
            return (Criteria) this;
        }

        public Criteria andAsIntroduceIn(List<String> values) {
            addCriterion("as_introduce in", values, "asIntroduce");
            return (Criteria) this;
        }

        public Criteria andAsIntroduceNotIn(List<String> values) {
            addCriterion("as_introduce not in", values, "asIntroduce");
            return (Criteria) this;
        }

        public Criteria andAsIntroduceBetween(String value1, String value2) {
            addCriterion("as_introduce between", value1, value2, "asIntroduce");
            return (Criteria) this;
        }

        public Criteria andAsIntroduceNotBetween(String value1, String value2) {
            addCriterion("as_introduce not between", value1, value2, "asIntroduce");
            return (Criteria) this;
        }

        public Criteria andVersionIsNull() {
            addCriterion("version is null");
            return (Criteria) this;
        }

        public Criteria andVersionIsNotNull() {
            addCriterion("version is not null");
            return (Criteria) this;
        }

        public Criteria andVersionEqualTo(Integer value) {
            addCriterion("version =", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionNotEqualTo(Integer value) {
            addCriterion("version <>", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionGreaterThan(Integer value) {
            addCriterion("version >", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionGreaterThanOrEqualTo(Integer value) {
            addCriterion("version >=", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionLessThan(Integer value) {
            addCriterion("version <", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionLessThanOrEqualTo(Integer value) {
            addCriterion("version <=", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionIn(List<Integer> values) {
            addCriterion("version in", values, "version");
            return (Criteria) this;
        }

        public Criteria andVersionNotIn(List<Integer> values) {
            addCriterion("version not in", values, "version");
            return (Criteria) this;
        }

        public Criteria andVersionBetween(Integer value1, Integer value2) {
            addCriterion("version between", value1, value2, "version");
            return (Criteria) this;
        }

        public Criteria andVersionNotBetween(Integer value1, Integer value2) {
            addCriterion("version not between", value1, value2, "version");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table public.exchange_record
     *
     * @mbg.generated do_not_delete_during_merge Wed Jan 22 22:27:35 CST 2025
     */
    public static class Criteria extends GeneratedCriteria {
        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table public.exchange_record
     *
     * @mbg.generated Wed Jan 22 22:27:35 CST 2025
     */
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