package icu.yougan.trade.service;

import icu.yougan.trade.entity.IdleItem;
import icu.yougan.trade.vo.PageVo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author:油柑
 * @Date:2024/5/18 22:21
 * @Version:v1.0.0
 * @Description:限制物品服务层
 **/
public interface IdleItemService {
    /**
     * 发布闲置
     *
     * @param idleItem id
     * @return 添加结果
     */
    boolean addIdleItem(IdleItem idleItem);

    /**
     * 获取某个闲置的信息
     *
     * @param id id
     * @return 闲置信息
     */
    IdleItem getIdleItem(Long id);

    /**
     * 获取某个用户的所有闲置信息
     *
     * @param userId 用户id
     * @return 闲置商品列表
     */
    List<IdleItem> getAllIdelItem(Long userId);

    /**
     * 搜索闲置商品
     *
     * @param findValue 值
     * @param page      分页大小
     * @param nums      页数
     * @return 结果
     */
    PageVo<IdleItem> findIdleItem(String findValue, int page, int nums);

    /**
     * 按分类获取闲置，分页器
     *
     * @param idleLabel 标签
     * @param page      分页大小
     * @param nums      页数
     * @return 闲置商品
     */
    PageVo<IdleItem> findIdleItemByLable(int idleLabel, int page, int nums);

    /**
     * 更新闲置的状态信息
     *
     * @param idleItem 闲置商品
     * @return 结果
     */
    boolean updateIdleItem(IdleItem idleItem);

    PageVo<IdleItem> adminGetIdleList(int status, int page, int nums);
}
