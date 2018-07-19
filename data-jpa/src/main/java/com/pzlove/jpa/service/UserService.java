package com.pzlove.jpa.service;

import com.pzlove.jpa.dao.UserDao;
import com.pzlove.jpa.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @author pzlove
 * @date 2018-07-13 下午3:44
 **/
@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    public void saveUser(User user) {
        userDao.save(user);
    }

    public User findUser(Long id) {
        return userDao.findById(id).get();

    }
}
