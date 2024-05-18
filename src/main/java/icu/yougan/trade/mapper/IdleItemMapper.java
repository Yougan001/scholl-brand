package icu.yougan.trade.mapper;

import icu.yougan.trade.entity.IdleItem;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author:油柑
 * @Date:2024/5/18 22:20
 * @Version:v1.0.0
 * @Description:限制物品数据持久层
 **/
@Mapper
public interface IdleItemMapper {
    int deleteByPrimaryKey(Long id);

    int insert(IdleItem record);

    int insertSelective(IdleItem record);

    IdleItem selectByPrimaryKey(Long id);

    List<IdleItem> getAllIdleItem(Long userId);

    int countIdleItem(String findValue);

    int countIdleItemByLable(int idleLabel);

    int countIdleItemByStatus(int status);

    List<IdleItem> findIdleItem(String findValue, int begin, int nums);

    List<IdleItem> findIdleItemByLable(int idleLabel, int begin, int nums);

    List<IdleItem> getIdleItemByStatus(int status, int begin, int nums);

    int updateByPrimaryKeySelective(IdleItem record);

    int updateByPrimaryKey(IdleItem record);

    List<IdleItem> findIdleByList(List<Long> idList);
}
