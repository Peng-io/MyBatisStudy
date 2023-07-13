package com.demo.Mapper;

import com.demo.pojo.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface UserMapper {
    // 查询全部
    @Select("select * from user")
    List<User> selectUser();

    // 根据id 查询
    @Select("select * from user where id = #{id}")
    User selectUserById(@Param("id") String id);

    //添加用户
    @Insert("insert into user(id, name, password) values (#{id}, #{name}, #{password})")
    int addUser(User user);

    //更新用户信息
    @Update("update user set name = #{name},password = #{password} where id = #{id}")
    int updateUser(User user);

    //删除用户
    @Delete("delete from user  where id = #{id}")
    int deleteUser(@Param("id") String id);
}
