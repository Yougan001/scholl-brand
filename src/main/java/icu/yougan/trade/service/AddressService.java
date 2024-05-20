package icu.yougan.trade.service;

import icu.yougan.trade.entity.Address;

import java.util.List;

/**
 * @Author:油柑
 * @Date:2024/5/20 8:54
 * @Version:v1.0.0
 * @Description:地址相关服务类
 **/
public interface AddressService {
    /**
     * 获取一个用户的所有地址信息
     *
     * @param userId 用户id
     * @return 地址列表
     */
    List<Address> getAddressByUser(Long userId);

    /**
     * 获取单个地址的信息
     *
     * @param id     地址id
     * @param userId 用户id
     * @return 地址信息
     */
    Address getAddressById(Long id, Long userId);

    /**
     * 新增地址信息
     *
     * @param address 地址信息
     * @return 结果
     */
    boolean addAddress(Address address);

    /**
     * 修改地址信息
     *
     * @param address 地址信息
     * @return 结果
     */
    boolean updateAddress(Address address);

    /**
     * 删除地址信息
     *
     * @param address 地址信息
     * @return 结果
     */
    boolean deleteAddress(Address address);
}
