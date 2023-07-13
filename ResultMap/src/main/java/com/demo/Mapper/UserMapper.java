package com.demo.Mapper;

import com.demo.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    // 查询全部
    List<User> selectUser();

    // 根据id 查询
    User selectUserById(@Param("id") String id);
}
