package com.wucong.client.dto.model;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ChatInfoExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table w_chat_info
     *
     * @mbg.generated
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table w_chat_info
     *
     * @mbg.generated
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table w_chat_info
     *
     * @mbg.generated
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table w_chat_info
     *
     * @mbg.generated
     */
    public ChatInfoExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table w_chat_info
     *
     * @mbg.generated
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table w_chat_info
     *
     * @mbg.generated
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table w_chat_info
     *
     * @mbg.generated
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table w_chat_info
     *
     * @mbg.generated
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table w_chat_info
     *
     * @mbg.generated
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table w_chat_info
     *
     * @mbg.generated
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table w_chat_info
     *
     * @mbg.generated
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table w_chat_info
     *
     * @mbg.generated
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
     * This method corresponds to the database table w_chat_info
     *
     * @mbg.generated
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table w_chat_info
     *
     * @mbg.generated
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table w_chat_info
     *
     * @mbg.generated
     */
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

        public Criteria andChatIdIsNull() {
            addCriterion("wchat_id is null");
            return (Criteria) this;
        }

        public Criteria andChatIdIsNotNull() {
            addCriterion("wchat_id is not null");
            return (Criteria) this;
        }

        public Criteria andChatIdEqualTo(String value) {
            addCriterion("wchat_id =", value, "chatId");
            return (Criteria) this;
        }

        public Criteria andChatIdNotEqualTo(String value) {
            addCriterion("wchat_id <>", value, "chatId");
            return (Criteria) this;
        }

        public Criteria andChatIdGreaterThan(String value) {
            addCriterion("wchat_id >", value, "chatId");
            return (Criteria) this;
        }

        public Criteria andChatIdGreaterThanOrEqualTo(String value) {
            addCriterion("wchat_id >=", value, "chatId");
            return (Criteria) this;
        }

        public Criteria andChatIdLessThan(String value) {
            addCriterion("wchat_id <", value, "chatId");
            return (Criteria) this;
        }

        public Criteria andChatIdLessThanOrEqualTo(String value) {
            addCriterion("wchat_id <=", value, "chatId");
            return (Criteria) this;
        }

        public Criteria andChatIdLike(String value) {
            addCriterion("wchat_id like", value, "chatId");
            return (Criteria) this;
        }

        public Criteria andChatIdNotLike(String value) {
            addCriterion("wchat_id not like", value, "chatId");
            return (Criteria) this;
        }

        public Criteria andChatIdIn(List<String> values) {
            addCriterion("wchat_id in", values, "chatId");
            return (Criteria) this;
        }

        public Criteria andChatIdNotIn(List<String> values) {
            addCriterion("wchat_id not in", values, "chatId");
            return (Criteria) this;
        }

        public Criteria andChatIdBetween(String value1, String value2) {
            addCriterion("wchat_id between", value1, value2, "chatId");
            return (Criteria) this;
        }

        public Criteria andChatIdNotBetween(String value1, String value2) {
            addCriterion("wchat_id not between", value1, value2, "chatId");
            return (Criteria) this;
        }

        public Criteria andChatSenderIdIsNull() {
            addCriterion("wchat_sender_id is null");
            return (Criteria) this;
        }

        public Criteria andChatSenderIdIsNotNull() {
            addCriterion("wchat_sender_id is not null");
            return (Criteria) this;
        }

        public Criteria andChatSenderIdEqualTo(String value) {
            addCriterion("wchat_sender_id =", value, "chatSenderId");
            return (Criteria) this;
        }

        public Criteria andChatSenderIdNotEqualTo(String value) {
            addCriterion("wchat_sender_id <>", value, "chatSenderId");
            return (Criteria) this;
        }

        public Criteria andChatSenderIdGreaterThan(String value) {
            addCriterion("wchat_sender_id >", value, "chatSenderId");
            return (Criteria) this;
        }

        public Criteria andChatSenderIdGreaterThanOrEqualTo(String value) {
            addCriterion("wchat_sender_id >=", value, "chatSenderId");
            return (Criteria) this;
        }

        public Criteria andChatSenderIdLessThan(String value) {
            addCriterion("wchat_sender_id <", value, "chatSenderId");
            return (Criteria) this;
        }

        public Criteria andChatSenderIdLessThanOrEqualTo(String value) {
            addCriterion("wchat_sender_id <=", value, "chatSenderId");
            return (Criteria) this;
        }

        public Criteria andChatSenderIdLike(String value) {
            addCriterion("wchat_sender_id like", value, "chatSenderId");
            return (Criteria) this;
        }

        public Criteria andChatSenderIdNotLike(String value) {
            addCriterion("wchat_sender_id not like", value, "chatSenderId");
            return (Criteria) this;
        }

        public Criteria andChatSenderIdIn(List<String> values) {
            addCriterion("wchat_sender_id in", values, "chatSenderId");
            return (Criteria) this;
        }

        public Criteria andChatSenderIdNotIn(List<String> values) {
            addCriterion("wchat_sender_id not in", values, "chatSenderId");
            return (Criteria) this;
        }

        public Criteria andChatSenderIdBetween(String value1, String value2) {
            addCriterion("wchat_sender_id between", value1, value2, "chatSenderId");
            return (Criteria) this;
        }

        public Criteria andChatSenderIdNotBetween(String value1, String value2) {
            addCriterion("wchat_sender_id not between", value1, value2, "chatSenderId");
            return (Criteria) this;
        }

        public Criteria andChatSenderNameIsNull() {
            addCriterion("wchat_sender_name is null");
            return (Criteria) this;
        }

        public Criteria andChatSenderNameIsNotNull() {
            addCriterion("wchat_sender_name is not null");
            return (Criteria) this;
        }

        public Criteria andChatSenderNameEqualTo(String value) {
            addCriterion("wchat_sender_name =", value, "chatSenderName");
            return (Criteria) this;
        }

        public Criteria andChatSenderNameNotEqualTo(String value) {
            addCriterion("wchat_sender_name <>", value, "chatSenderName");
            return (Criteria) this;
        }

        public Criteria andChatSenderNameGreaterThan(String value) {
            addCriterion("wchat_sender_name >", value, "chatSenderName");
            return (Criteria) this;
        }

        public Criteria andChatSenderNameGreaterThanOrEqualTo(String value) {
            addCriterion("wchat_sender_name >=", value, "chatSenderName");
            return (Criteria) this;
        }

        public Criteria andChatSenderNameLessThan(String value) {
            addCriterion("wchat_sender_name <", value, "chatSenderName");
            return (Criteria) this;
        }

        public Criteria andChatSenderNameLessThanOrEqualTo(String value) {
            addCriterion("wchat_sender_name <=", value, "chatSenderName");
            return (Criteria) this;
        }

        public Criteria andChatSenderNameLike(String value) {
            addCriterion("wchat_sender_name like", value, "chatSenderName");
            return (Criteria) this;
        }

        public Criteria andChatSenderNameNotLike(String value) {
            addCriterion("wchat_sender_name not like", value, "chatSenderName");
            return (Criteria) this;
        }

        public Criteria andChatSenderNameIn(List<String> values) {
            addCriterion("wchat_sender_name in", values, "chatSenderName");
            return (Criteria) this;
        }

        public Criteria andChatSenderNameNotIn(List<String> values) {
            addCriterion("wchat_sender_name not in", values, "chatSenderName");
            return (Criteria) this;
        }

        public Criteria andChatSenderNameBetween(String value1, String value2) {
            addCriterion("wchat_sender_name between", value1, value2, "chatSenderName");
            return (Criteria) this;
        }

        public Criteria andChatSenderNameNotBetween(String value1, String value2) {
            addCriterion("wchat_sender_name not between", value1, value2, "chatSenderName");
            return (Criteria) this;
        }

        public Criteria andChatReceiverIdIsNull() {
            addCriterion("wchat_receiver_id is null");
            return (Criteria) this;
        }

        public Criteria andChatReceiverIdIsNotNull() {
            addCriterion("wchat_receiver_id is not null");
            return (Criteria) this;
        }

        public Criteria andChatReceiverIdEqualTo(String value) {
            addCriterion("wchat_receiver_id =", value, "chatReceiverId");
            return (Criteria) this;
        }

        public Criteria andChatReceiverIdNotEqualTo(String value) {
            addCriterion("wchat_receiver_id <>", value, "chatReceiverId");
            return (Criteria) this;
        }

        public Criteria andChatReceiverIdGreaterThan(String value) {
            addCriterion("wchat_receiver_id >", value, "chatReceiverId");
            return (Criteria) this;
        }

        public Criteria andChatReceiverIdGreaterThanOrEqualTo(String value) {
            addCriterion("wchat_receiver_id >=", value, "chatReceiverId");
            return (Criteria) this;
        }

        public Criteria andChatReceiverIdLessThan(String value) {
            addCriterion("wchat_receiver_id <", value, "chatReceiverId");
            return (Criteria) this;
        }

        public Criteria andChatReceiverIdLessThanOrEqualTo(String value) {
            addCriterion("wchat_receiver_id <=", value, "chatReceiverId");
            return (Criteria) this;
        }

        public Criteria andChatReceiverIdLike(String value) {
            addCriterion("wchat_receiver_id like", value, "chatReceiverId");
            return (Criteria) this;
        }

        public Criteria andChatReceiverIdNotLike(String value) {
            addCriterion("wchat_receiver_id not like", value, "chatReceiverId");
            return (Criteria) this;
        }

        public Criteria andChatReceiverIdIn(List<String> values) {
            addCriterion("wchat_receiver_id in", values, "chatReceiverId");
            return (Criteria) this;
        }

        public Criteria andChatReceiverIdNotIn(List<String> values) {
            addCriterion("wchat_receiver_id not in", values, "chatReceiverId");
            return (Criteria) this;
        }

        public Criteria andChatReceiverIdBetween(String value1, String value2) {
            addCriterion("wchat_receiver_id between", value1, value2, "chatReceiverId");
            return (Criteria) this;
        }

        public Criteria andChatReceiverIdNotBetween(String value1, String value2) {
            addCriterion("wchat_receiver_id not between", value1, value2, "chatReceiverId");
            return (Criteria) this;
        }

        public Criteria andChatReceiverNameIsNull() {
            addCriterion("wchat_receiver_name is null");
            return (Criteria) this;
        }

        public Criteria andChatReceiverNameIsNotNull() {
            addCriterion("wchat_receiver_name is not null");
            return (Criteria) this;
        }

        public Criteria andChatReceiverNameEqualTo(String value) {
            addCriterion("wchat_receiver_name =", value, "chatReceiverName");
            return (Criteria) this;
        }

        public Criteria andChatReceiverNameNotEqualTo(String value) {
            addCriterion("wchat_receiver_name <>", value, "chatReceiverName");
            return (Criteria) this;
        }

        public Criteria andChatReceiverNameGreaterThan(String value) {
            addCriterion("wchat_receiver_name >", value, "chatReceiverName");
            return (Criteria) this;
        }

        public Criteria andChatReceiverNameGreaterThanOrEqualTo(String value) {
            addCriterion("wchat_receiver_name >=", value, "chatReceiverName");
            return (Criteria) this;
        }

        public Criteria andChatReceiverNameLessThan(String value) {
            addCriterion("wchat_receiver_name <", value, "chatReceiverName");
            return (Criteria) this;
        }

        public Criteria andChatReceiverNameLessThanOrEqualTo(String value) {
            addCriterion("wchat_receiver_name <=", value, "chatReceiverName");
            return (Criteria) this;
        }

        public Criteria andChatReceiverNameLike(String value) {
            addCriterion("wchat_receiver_name like", value, "chatReceiverName");
            return (Criteria) this;
        }

        public Criteria andChatReceiverNameNotLike(String value) {
            addCriterion("wchat_receiver_name not like", value, "chatReceiverName");
            return (Criteria) this;
        }

        public Criteria andChatReceiverNameIn(List<String> values) {
            addCriterion("wchat_receiver_name in", values, "chatReceiverName");
            return (Criteria) this;
        }

        public Criteria andChatReceiverNameNotIn(List<String> values) {
            addCriterion("wchat_receiver_name not in", values, "chatReceiverName");
            return (Criteria) this;
        }

        public Criteria andChatReceiverNameBetween(String value1, String value2) {
            addCriterion("wchat_receiver_name between", value1, value2, "chatReceiverName");
            return (Criteria) this;
        }

        public Criteria andChatReceiverNameNotBetween(String value1, String value2) {
            addCriterion("wchat_receiver_name not between", value1, value2, "chatReceiverName");
            return (Criteria) this;
        }

        public Criteria andChatMessageIsNull() {
            addCriterion("wchat_message is null");
            return (Criteria) this;
        }

        public Criteria andChatMessageIsNotNull() {
            addCriterion("wchat_message is not null");
            return (Criteria) this;
        }

        public Criteria andChatMessageEqualTo(String value) {
            addCriterion("wchat_message =", value, "chatMessage");
            return (Criteria) this;
        }

        public Criteria andChatMessageNotEqualTo(String value) {
            addCriterion("wchat_message <>", value, "chatMessage");
            return (Criteria) this;
        }

        public Criteria andChatMessageGreaterThan(String value) {
            addCriterion("wchat_message >", value, "chatMessage");
            return (Criteria) this;
        }

        public Criteria andChatMessageGreaterThanOrEqualTo(String value) {
            addCriterion("wchat_message >=", value, "chatMessage");
            return (Criteria) this;
        }

        public Criteria andChatMessageLessThan(String value) {
            addCriterion("wchat_message <", value, "chatMessage");
            return (Criteria) this;
        }

        public Criteria andChatMessageLessThanOrEqualTo(String value) {
            addCriterion("wchat_message <=", value, "chatMessage");
            return (Criteria) this;
        }

        public Criteria andChatMessageLike(String value) {
            addCriterion("wchat_message like", value, "chatMessage");
            return (Criteria) this;
        }

        public Criteria andChatMessageNotLike(String value) {
            addCriterion("wchat_message not like", value, "chatMessage");
            return (Criteria) this;
        }

        public Criteria andChatMessageIn(List<String> values) {
            addCriterion("wchat_message in", values, "chatMessage");
            return (Criteria) this;
        }

        public Criteria andChatMessageNotIn(List<String> values) {
            addCriterion("wchat_message not in", values, "chatMessage");
            return (Criteria) this;
        }

        public Criteria andChatMessageBetween(String value1, String value2) {
            addCriterion("wchat_message between", value1, value2, "chatMessage");
            return (Criteria) this;
        }

        public Criteria andChatMessageNotBetween(String value1, String value2) {
            addCriterion("wchat_message not between", value1, value2, "chatMessage");
            return (Criteria) this;
        }

        public Criteria andChatCreateTimeIsNull() {
            addCriterion("wchat_create_time is null");
            return (Criteria) this;
        }

        public Criteria andChatCreateTimeIsNotNull() {
            addCriterion("wchat_create_time is not null");
            return (Criteria) this;
        }

        public Criteria andChatCreateTimeEqualTo(Date value) {
            addCriterion("wchat_create_time =", value, "chatCreateTime");
            return (Criteria) this;
        }

        public Criteria andChatCreateTimeNotEqualTo(Date value) {
            addCriterion("wchat_create_time <>", value, "chatCreateTime");
            return (Criteria) this;
        }

        public Criteria andChatCreateTimeGreaterThan(Date value) {
            addCriterion("wchat_create_time >", value, "chatCreateTime");
            return (Criteria) this;
        }

        public Criteria andChatCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("wchat_create_time >=", value, "chatCreateTime");
            return (Criteria) this;
        }

        public Criteria andChatCreateTimeLessThan(Date value) {
            addCriterion("wchat_create_time <", value, "chatCreateTime");
            return (Criteria) this;
        }

        public Criteria andChatCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("wchat_create_time <=", value, "chatCreateTime");
            return (Criteria) this;
        }

        public Criteria andChatCreateTimeIn(List<Date> values) {
            addCriterion("wchat_create_time in", values, "chatCreateTime");
            return (Criteria) this;
        }

        public Criteria andChatCreateTimeNotIn(List<Date> values) {
            addCriterion("wchat_create_time not in", values, "chatCreateTime");
            return (Criteria) this;
        }

        public Criteria andChatCreateTimeBetween(Date value1, Date value2) {
            addCriterion("wchat_create_time between", value1, value2, "chatCreateTime");
            return (Criteria) this;
        }

        public Criteria andChatCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("wchat_create_time not between", value1, value2, "chatCreateTime");
            return (Criteria) this;
        }

        public Criteria andChatStatusIsNull() {
            addCriterion("wchat_status is null");
            return (Criteria) this;
        }

        public Criteria andChatStatusIsNotNull() {
            addCriterion("wchat_status is not null");
            return (Criteria) this;
        }

        public Criteria andChatStatusEqualTo(Integer value) {
            addCriterion("wchat_status =", value, "chatStatus");
            return (Criteria) this;
        }

        public Criteria andChatStatusNotEqualTo(Integer value) {
            addCriterion("wchat_status <>", value, "chatStatus");
            return (Criteria) this;
        }

        public Criteria andChatStatusGreaterThan(Integer value) {
            addCriterion("wchat_status >", value, "chatStatus");
            return (Criteria) this;
        }

        public Criteria andChatStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("wchat_status >=", value, "chatStatus");
            return (Criteria) this;
        }

        public Criteria andChatStatusLessThan(Integer value) {
            addCriterion("wchat_status <", value, "chatStatus");
            return (Criteria) this;
        }

        public Criteria andChatStatusLessThanOrEqualTo(Integer value) {
            addCriterion("wchat_status <=", value, "chatStatus");
            return (Criteria) this;
        }

        public Criteria andChatStatusIn(List<Integer> values) {
            addCriterion("wchat_status in", values, "chatStatus");
            return (Criteria) this;
        }

        public Criteria andChatStatusNotIn(List<Integer> values) {
            addCriterion("wchat_status not in", values, "chatStatus");
            return (Criteria) this;
        }

        public Criteria andChatStatusBetween(Integer value1, Integer value2) {
            addCriterion("wchat_status between", value1, value2, "chatStatus");
            return (Criteria) this;
        }

        public Criteria andChatStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("wchat_status not between", value1, value2, "chatStatus");
            return (Criteria) this;
        }

        public Criteria andChatHandlerIsNull() {
            addCriterion("wchat_handler is null");
            return (Criteria) this;
        }

        public Criteria andChatHandlerIsNotNull() {
            addCriterion("wchat_handler is not null");
            return (Criteria) this;
        }

        public Criteria andChatHandlerEqualTo(String value) {
            addCriterion("wchat_handler =", value, "chatHandler");
            return (Criteria) this;
        }

        public Criteria andChatHandlerNotEqualTo(String value) {
            addCriterion("wchat_handler <>", value, "chatHandler");
            return (Criteria) this;
        }

        public Criteria andChatHandlerGreaterThan(String value) {
            addCriterion("wchat_handler >", value, "chatHandler");
            return (Criteria) this;
        }

        public Criteria andChatHandlerGreaterThanOrEqualTo(String value) {
            addCriterion("wchat_handler >=", value, "chatHandler");
            return (Criteria) this;
        }

        public Criteria andChatHandlerLessThan(String value) {
            addCriterion("wchat_handler <", value, "chatHandler");
            return (Criteria) this;
        }

        public Criteria andChatHandlerLessThanOrEqualTo(String value) {
            addCriterion("wchat_handler <=", value, "chatHandler");
            return (Criteria) this;
        }

        public Criteria andChatHandlerLike(String value) {
            addCriterion("wchat_handler like", value, "chatHandler");
            return (Criteria) this;
        }

        public Criteria andChatHandlerNotLike(String value) {
            addCriterion("wchat_handler not like", value, "chatHandler");
            return (Criteria) this;
        }

        public Criteria andChatHandlerIn(List<String> values) {
            addCriterion("wchat_handler in", values, "chatHandler");
            return (Criteria) this;
        }

        public Criteria andChatHandlerNotIn(List<String> values) {
            addCriterion("wchat_handler not in", values, "chatHandler");
            return (Criteria) this;
        }

        public Criteria andChatHandlerBetween(String value1, String value2) {
            addCriterion("wchat_handler between", value1, value2, "chatHandler");
            return (Criteria) this;
        }

        public Criteria andChatHandlerNotBetween(String value1, String value2) {
            addCriterion("wchat_handler not between", value1, value2, "chatHandler");
            return (Criteria) this;
        }

        public Criteria andChatSignIsNull() {
            addCriterion("wchat_sign is null");
            return (Criteria) this;
        }

        public Criteria andChatSignIsNotNull() {
            addCriterion("wchat_sign is not null");
            return (Criteria) this;
        }

        public Criteria andChatSignEqualTo(String value) {
            addCriterion("wchat_sign =", value, "chatSign");
            return (Criteria) this;
        }

        public Criteria andChatSignNotEqualTo(String value) {
            addCriterion("wchat_sign <>", value, "chatSign");
            return (Criteria) this;
        }

        public Criteria andChatSignGreaterThan(String value) {
            addCriterion("wchat_sign >", value, "chatSign");
            return (Criteria) this;
        }

        public Criteria andChatSignGreaterThanOrEqualTo(String value) {
            addCriterion("wchat_sign >=", value, "chatSign");
            return (Criteria) this;
        }

        public Criteria andChatSignLessThan(String value) {
            addCriterion("wchat_sign <", value, "chatSign");
            return (Criteria) this;
        }

        public Criteria andChatSignLessThanOrEqualTo(String value) {
            addCriterion("wchat_sign <=", value, "chatSign");
            return (Criteria) this;
        }

        public Criteria andChatSignLike(String value) {
            addCriterion("wchat_sign like", value, "chatSign");
            return (Criteria) this;
        }

        public Criteria andChatSignNotLike(String value) {
            addCriterion("wchat_sign not like", value, "chatSign");
            return (Criteria) this;
        }

        public Criteria andChatSignIn(List<String> values) {
            addCriterion("wchat_sign in", values, "chatSign");
            return (Criteria) this;
        }

        public Criteria andChatSignNotIn(List<String> values) {
            addCriterion("wchat_sign not in", values, "chatSign");
            return (Criteria) this;
        }

        public Criteria andChatSignBetween(String value1, String value2) {
            addCriterion("wchat_sign between", value1, value2, "chatSign");
            return (Criteria) this;
        }

        public Criteria andChatSignNotBetween(String value1, String value2) {
            addCriterion("wchat_sign not between", value1, value2, "chatSign");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table w_chat_info
     *
     * @mbg.generated do_not_delete_during_merge
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table w_chat_info
     *
     * @mbg.generated
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

    @Override
    public String toString(){
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}