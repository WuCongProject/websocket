package com.wucong.client.dto.model;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.util.Date;

public class ChatInfo {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column w_chat_info.wchat_id
     *
     * @mbg.generated Wed Mar 31 11:41:04 CST 2021
     */
    private String chatId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column w_chat_info.wchat_sender_id
     *
     * @mbg.generated Wed Mar 31 11:41:04 CST 2021
     */
    private String chatSenderId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column w_chat_info.wchat_sender_name
     *
     * @mbg.generated Wed Mar 31 11:41:04 CST 2021
     */
    private String chatSenderName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column w_chat_info.wchat_receiver_id
     *
     * @mbg.generated Wed Mar 31 11:41:04 CST 2021
     */
    private String chatReceiverId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column w_chat_info.wchat_receiver_name
     *
     * @mbg.generated Wed Mar 31 11:41:04 CST 2021
     */
    private String chatReceiverName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column w_chat_info.wchat_message
     *
     * @mbg.generated Wed Mar 31 11:41:04 CST 2021
     */
    private String chatMessage;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column w_chat_info.wchat_message_type
     *
     * @mbg.generated Wed Mar 31 11:41:04 CST 2021
     */
    private String chatMessageType;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column w_chat_info.wchat_create_time
     *
     * @mbg.generated Wed Mar 31 11:41:04 CST 2021
     */
    private Date chatCreateTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column w_chat_info.wchat_status
     *
     * @mbg.generated Wed Mar 31 11:41:04 CST 2021
     */
    private Integer chatStatus;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column w_chat_info.wchat_handler
     *
     * @mbg.generated Wed Mar 31 11:41:04 CST 2021
     */
    private String chatHandler;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column w_chat_info.wchat_sign
     *
     * @mbg.generated Wed Mar 31 11:41:04 CST 2021
     */
    private String chatSign;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column w_chat_info.wchat_id
     *
     * @return the value of w_chat_info.wchat_id
     *
     * @mbg.generated Wed Mar 31 11:41:04 CST 2021
     */
    public String getChatId() {
        return chatId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column w_chat_info.wchat_id
     *
     * @param chatId the value for w_chat_info.wchat_id
     *
     * @mbg.generated Wed Mar 31 11:41:04 CST 2021
     */
    public void setChatId(String chatId) {
        this.chatId = chatId == null ? null : chatId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column w_chat_info.wchat_sender_id
     *
     * @return the value of w_chat_info.wchat_sender_id
     *
     * @mbg.generated Wed Mar 31 11:41:04 CST 2021
     */
    public String getChatSenderId() {
        return chatSenderId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column w_chat_info.wchat_sender_id
     *
     * @param chatSenderId the value for w_chat_info.wchat_sender_id
     *
     * @mbg.generated Wed Mar 31 11:41:04 CST 2021
     */
    public void setChatSenderId(String chatSenderId) {
        this.chatSenderId = chatSenderId == null ? null : chatSenderId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column w_chat_info.wchat_sender_name
     *
     * @return the value of w_chat_info.wchat_sender_name
     *
     * @mbg.generated Wed Mar 31 11:41:04 CST 2021
     */
    public String getChatSenderName() {
        return chatSenderName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column w_chat_info.wchat_sender_name
     *
     * @param chatSenderName the value for w_chat_info.wchat_sender_name
     *
     * @mbg.generated Wed Mar 31 11:41:04 CST 2021
     */
    public void setChatSenderName(String chatSenderName) {
        this.chatSenderName = chatSenderName == null ? null : chatSenderName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column w_chat_info.wchat_receiver_id
     *
     * @return the value of w_chat_info.wchat_receiver_id
     *
     * @mbg.generated Wed Mar 31 11:41:04 CST 2021
     */
    public String getChatReceiverId() {
        return chatReceiverId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column w_chat_info.wchat_receiver_id
     *
     * @param chatReceiverId the value for w_chat_info.wchat_receiver_id
     *
     * @mbg.generated Wed Mar 31 11:41:04 CST 2021
     */
    public void setChatReceiverId(String chatReceiverId) {
        this.chatReceiverId = chatReceiverId == null ? null : chatReceiverId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column w_chat_info.wchat_receiver_name
     *
     * @return the value of w_chat_info.wchat_receiver_name
     *
     * @mbg.generated Wed Mar 31 11:41:04 CST 2021
     */
    public String getChatReceiverName() {
        return chatReceiverName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column w_chat_info.wchat_receiver_name
     *
     * @param chatReceiverName the value for w_chat_info.wchat_receiver_name
     *
     * @mbg.generated Wed Mar 31 11:41:04 CST 2021
     */
    public void setChatReceiverName(String chatReceiverName) {
        this.chatReceiverName = chatReceiverName == null ? null : chatReceiverName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column w_chat_info.wchat_message
     *
     * @return the value of w_chat_info.wchat_message
     *
     * @mbg.generated Wed Mar 31 11:41:04 CST 2021
     */
    public String getChatMessage() {
        return chatMessage;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column w_chat_info.wchat_message
     *
     * @param chatMessage the value for w_chat_info.wchat_message
     *
     * @mbg.generated Wed Mar 31 11:41:04 CST 2021
     */
    public void setChatMessage(String chatMessage) {
        this.chatMessage = chatMessage == null ? null : chatMessage.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column w_chat_info.wchat_message_type
     *
     * @return the value of w_chat_info.wchat_message_type
     *
     * @mbg.generated Wed Mar 31 11:41:04 CST 2021
     */
    public String getChatMessageType() {
        return chatMessageType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column w_chat_info.wchat_message_type
     *
     * @param chatMessageType the value for w_chat_info.wchat_message_type
     *
     * @mbg.generated Wed Mar 31 11:41:04 CST 2021
     */
    public void setChatMessageType(String chatMessageType) {
        this.chatMessageType = chatMessageType == null ? null : chatMessageType.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column w_chat_info.wchat_create_time
     *
     * @return the value of w_chat_info.wchat_create_time
     *
     * @mbg.generated Wed Mar 31 11:41:04 CST 2021
     */
    public Date getChatCreateTime() {
        return chatCreateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column w_chat_info.wchat_create_time
     *
     * @param chatCreateTime the value for w_chat_info.wchat_create_time
     *
     * @mbg.generated Wed Mar 31 11:41:04 CST 2021
     */
    public void setChatCreateTime(Date chatCreateTime) {
        this.chatCreateTime = chatCreateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column w_chat_info.wchat_status
     *
     * @return the value of w_chat_info.wchat_status
     *
     * @mbg.generated Wed Mar 31 11:41:04 CST 2021
     */
    public Integer getChatStatus() {
        return chatStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column w_chat_info.wchat_status
     *
     * @param chatStatus the value for w_chat_info.wchat_status
     *
     * @mbg.generated Wed Mar 31 11:41:04 CST 2021
     */
    public void setChatStatus(Integer chatStatus) {
        this.chatStatus = chatStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column w_chat_info.wchat_handler
     *
     * @return the value of w_chat_info.wchat_handler
     *
     * @mbg.generated Wed Mar 31 11:41:04 CST 2021
     */
    public String getChatHandler() {
        return chatHandler;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column w_chat_info.wchat_handler
     *
     * @param chatHandler the value for w_chat_info.wchat_handler
     *
     * @mbg.generated Wed Mar 31 11:41:04 CST 2021
     */
    public void setChatHandler(String chatHandler) {
        this.chatHandler = chatHandler == null ? null : chatHandler.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column w_chat_info.wchat_sign
     *
     * @return the value of w_chat_info.wchat_sign
     *
     * @mbg.generated Wed Mar 31 11:41:04 CST 2021
     */
    public String getChatSign() {
        return chatSign;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column w_chat_info.wchat_sign
     *
     * @param chatSign the value for w_chat_info.wchat_sign
     *
     * @mbg.generated Wed Mar 31 11:41:04 CST 2021
     */
    public void setChatSign(String chatSign) {
        this.chatSign = chatSign == null ? null : chatSign.trim();
    }

    @Override
    public String toString(){
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}