package icu.yougan.trade.service.Impl;

import icu.yougan.trade.entity.OrderAddress;
import icu.yougan.trade.mapper.OrderAddressMapper;
import icu.yougan.trade.service.OrderAddressService;

import javax.annotation.Resource;

/**
 * @Author:油柑
 * @Date:2024/5/21 11:03
 * @Version:v1.0.0
 * @Description:订单地址 服务实现类
 **/
public class OrderAddressServiceImpl implements OrderAddressService {
    @Resource
    private OrderAddressMapper orderAddressMapper;

    @Override
    public boolean addOrderAddress(OrderAddress orderAddressModel) {
        return orderAddressMapper.insert(orderAddressModel) == 1;
    }

    @Override
    public boolean updateOrderAddress(OrderAddress orderAddressModel) {
        orderAddressModel.setOrderId(null);
        return orderAddressMapper.updateByPrimaryKeySelective(orderAddressModel) == 1;
    }

    @Override
    public OrderAddress getOrderAddress(Long orderId) {
        return orderAddressMapper.selectByOrderId(orderId);
    }

}
