package com.pzlove.mybatis.mapper.cluster;

import com.pzlove.mybatis.domain.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author pzlove
 * @date 2018-07-13 下午4:16
 **/
@Repository
public interface UserMapper {

    User findUserById(Long id);

    List<User> findAllUser();

    void saveUser(User user);

    void updateUser(User user);

    void deleteUser(Long id);

}
