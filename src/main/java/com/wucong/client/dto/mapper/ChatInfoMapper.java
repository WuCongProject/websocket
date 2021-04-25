package com.wucong.client.dto.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;
import com.wucong.client.dto.model.ChatInfo;
import com.wucong.client.dto.model.ChatInfoExample;

import java.util.List;

/**
 * @author kerwin
 * @date 2020/11/13  15:48
 */
@Repository
public interface ChatInfoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table w_chat_info
     *
     * @mbg.generated
     */
    long countByExample(ChatInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table w_chat_info
     *
     * @mbg.generated
     */
    int deleteByExample(ChatInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table w_chat_info
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(String chatId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table w_chat_info
     *
     * @mbg.generated
     */
    int insert(ChatInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table w_chat_info
     *
     * @mbg.generated
     */
    int insertSelective(ChatInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table w_chat_info
     *
     * @mbg.generated
     */
    List<ChatInfo> selectByExampleWithRowbounds(ChatInfoExample example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table w_chat_info
     *
     * @mbg.generated
     */
    List<ChatInfo> selectByExample(ChatInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table w_chat_info
     *
     * @mbg.generated
     */
    ChatInfo selectByPrimaryKey(String chatId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table w_chat_info
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") ChatInfo record, @Param("example") ChatInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table w_chat_info
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") ChatInfo record, @Param("example") ChatInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table w_chat_info
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(ChatInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table w_chat_info
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(ChatInfo record);

    /**
     * 查询接收的最近一条消息
     * @param receiverId
     * @return
     */
    List<ChatInfo> selectReceiverMaxCreateTime(@Param("receiverId") String receiverId);

    /**
     * 查询发送的最近一条消息
     * @param senderId
     * @return
     */
    List<ChatInfo> selectSenderMaxCreateTime(@Param("senderId") String senderId);

}