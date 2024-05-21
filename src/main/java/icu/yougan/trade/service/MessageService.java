package icu.yougan.trade.service;

import icu.yougan.trade.entity.Message;

import java.util.List;

/**
 * @Author:油柑
 * @Date:2024/5/21 11:44
 * @Version:v1.0.0
 * @Description:留言 业务服务
 **/
public interface MessageService {
    /**
     * 发送留言
     *
     * @param message 留言内容
     * @return 结果
     */
    boolean addMessage(Message message);

    /**
     * 删除留言
     *
     * @param id 留言id
     * @return 结果
     */
    boolean deleteMessage(Long id);

    /**
     * 获取某个留言
     *
     * @param id 留言id
     * @return 结果
     */
    Message getMessage(Long id);

    /**
     * 获取某个用户收到的所有留言
     *
     * @param userId 用户id
     * @return 留言列表
     */
    List<Message> getAllMyMessage(Long userId);

    /**
     * 获取某个闲置的所有留言
     *
     * @param idleId 闲置id
     * @return 结果
     */
    List<Message> getAllIdleMessage(Long idleId);
}
