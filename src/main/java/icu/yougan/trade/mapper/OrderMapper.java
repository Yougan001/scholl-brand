package icu.yougan.trade.mapper;

import icu.yougan.trade.entity.Order;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author:油柑
 * @Date:2024/5/18 22:30
 * @Version:v1.0.0
 * @Description:订单数据持久层
 **/
@Mapper
public interface OrderMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Order record);

    int insertSelective(Order record);

    Order selectByPrimaryKey(Long id);

    List<Order> getMyOrder(Long userId);

    List<Order> getAllOrder(int begin, int nums);

    int countAllOrder();

    List<Order> findOrderByIdleIdList(List<Long> idleIdList);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);
}
