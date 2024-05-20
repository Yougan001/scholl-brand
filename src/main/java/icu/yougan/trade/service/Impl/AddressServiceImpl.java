package icu.yougan.trade.service.Impl;

import icu.yougan.trade.entity.Address;
import icu.yougan.trade.mapper.AddressMapper;
import icu.yougan.trade.service.AddressService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author:油柑
 * @Date:2024/5/20 8:54
 * @Version:v1.0.0
 * @Description:地址相关 服务实现类
 **/
public class AddressServiceImpl  implements AddressService {

    @Resource
    private AddressMapper addressMapper;

    @Override
    public List<Address> getAddressByUser(Long userId){
        return addressMapper.getAddressByUser(userId);
    }

    @Override
    public Address getAddressById(Long id, Long userId){
        Address address = addressMapper.selectByPrimaryKey(id);
        if(userId.equals(address.getUserId())){
            return address;
        }
        return null;
    }

    @Override
    public boolean addAddress(Address address){
        if(address.getDefaultFlag()){
            Address a=new Address();
            a.setDefaultFlag(false);
            a.setUserId(address.getUserId());
            //将一个用户的所有地址改为非默认地址，需要优化，sql增加判断条件default_flag=1，减少更新记录的数目
            addressMapper.updateByUserIdSelective(a);
        }else {
            //判断是否有默认地址，若无，则将当前地址设为默认地址
            List<Address> list= addressMapper.getDefaultAddress(address.getUserId());
            //可优化，改为count统计，不用返回地址数据，减少io
            if(null==list||0==list.size()){
                address.setDefaultFlag(true);
            }
        }
        return addressMapper.insert(address)==1;
    }

    @Override
    public boolean updateAddress(Address address){
        if(address.getDefaultFlag()){
            //同新增地址时的逻辑
            Address a=new Address();
            a.setDefaultFlag(false);
            a.setUserId(address.getUserId());
            addressMapper.updateByUserIdSelective(a);
        }else{
            //若取消默认地址，则将第一个地址设置为默认地址
            List<Address> list= addressMapper.getAddressByUser(address.getUserId());
            for(Address a:list){
                if(a.getDefaultFlag()&& a.getId().equals(address.getId())){
                    Address a1=new Address();
                    a1.setId(list.get(0).getId());
                    a1.setDefaultFlag(true);
                    return addressMapper.updateByPrimaryKeySelective(address)==1&&
                            addressMapper.updateByPrimaryKeySelective(a1)==1;
                }
            }
        }
        return addressMapper.updateByPrimaryKeySelective(address)==1;
    }

    @Override
    public boolean deleteAddress(Address address){
        return addressMapper.deleteByPrimaryKeyAndUser(address.getId(), address.getUserId())==1;
    }
}
