package com.pzlove.mybatis.service;

import com.pzlove.mybatis.domain.User;
import com.pzlove.mybatis.mapper.cluster.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author pzlove
 * @date 2018-07-11 上午10:49
 **/
@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public User findUserById(Long id) {
        return userMapper.findUserById(id);
    }

    public List<User> findAllUser() {
        return userMapper.findAllUser();
    }

    public void saveUser(User user) {
        userMapper.saveUser(user);
    }

    public void updateUser(User user) {
        userMapper.updateUser(user);
    }

    public void deleteUser(Long id) {
        userMapper.deleteUser(id);
    }
}
