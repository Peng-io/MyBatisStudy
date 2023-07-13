package com.demo.Mapper;

import com.demo.pojo.User;

import java.util.List;
import java.util.Map;

public interface UserMapper {
    // 查询全部
    List<User> selectUser();

    // 根据id 查询
    User selectUserById(String id);

    User selectUserById2(Map<String, Object> map);

    //添加用户
    int addUser(User user);

    //更新用户信息
    int updateUser(User user);

    //删除用户
    int deleteUser(String id);
}
