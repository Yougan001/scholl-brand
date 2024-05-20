package icu.yougan.trade.mapper;

import icu.yougan.trade.entity.Address;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author:油柑
 * @Date:2024/5/20 8:53
 * @Version:v1.0.0
 * @Description:地址持久层
 **/
@Mapper
public interface AddressMapper {
    int deleteByPrimaryKeyAndUser(Long id,Long userId);

    int insert(Address record);

    int insertSelective(Address record);

    Address selectByPrimaryKey(Long id);

    List<Address> getAddressByUser(Long userId);

    List<Address> getDefaultAddress(Long userId);

    int updateByPrimaryKeySelective(Address record);

    int updateByUserIdSelective(Address record);

    int updateByPrimaryKey(Address record);
}
