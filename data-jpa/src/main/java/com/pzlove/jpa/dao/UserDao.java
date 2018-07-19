package com.pzlove.jpa.dao;

import com.pzlove.jpa.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author pzlove
 * @date 2018-07-13 下午3:45
 **/
@Repository
public interface UserDao extends JpaRepository<User,Long>{


}
