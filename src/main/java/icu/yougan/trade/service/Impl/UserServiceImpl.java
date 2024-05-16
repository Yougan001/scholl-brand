package icu.yougan.trade.service.Impl;


import icu.yougan.trade.entity.User;

import icu.yougan.trade.mapper.UserMapper;
import icu.yougan.trade.service.UserService;
import icu.yougan.trade.vo.PageVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author:油柑
 * @Date:2024/5/15 2:42
 * @Version:v1.0.0
 * @Description:TODO
 **/
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public User getUser(Long id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public User userLogin(String accountNumber, String userPassword) {
        return userMapper.userLogin(accountNumber, userPassword);
    }

    @Override
    public boolean userSignIn(User user) {
        return userMapper.insert(user) == 1;
    }

    @Override
    public boolean updateUserInfo(User user) {
        return userMapper.updateByPrimaryKeySelective(user) == 1;
    }

    @Override
    public boolean updatePassword(String newPassword, String oldPassword, Long id) {
        return userMapper.updatePassword(newPassword, oldPassword, id) == 1;
    }

    @Override
    public PageVo<User> getUserByStatus(int status, int page, int nums) {
        List<User> list;
        int count = 0;
        if (status == 0) {
            count = userMapper.countNormalUser();
            list = userMapper.getNormalUser((page - 1) * nums, nums);
        } else {
            count = userMapper.countBanUser();
            list = userMapper.getBanUser((page - 1) * nums, nums);
        }
        return new PageVo<>(list, count);
    }

}