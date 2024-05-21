package icu.yougan.trade.mapper;

import icu.yougan.trade.entity.OrderAddress;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author:油柑
 * @Date:2024/5/21 11:01
 * @Version:v1.0.0
 * @Description:订单 数据持久层
 **/
@Mapper
public interface OrderAddressMapper {
    int deleteByPrimaryKey(Long id);

    int insert(OrderAddress record);

    int insertSelective(OrderAddress record);

    OrderAddress selectByPrimaryKey(Long id);

    OrderAddress selectByOrderId(Long orderId);

    int updateByPrimaryKeySelective(OrderAddress record);

    int updateByPrimaryKey(OrderAddress record);
}
