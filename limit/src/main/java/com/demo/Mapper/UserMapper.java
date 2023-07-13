package com.demo.Mapper;

import com.demo.pojo.User;

import java.util.List;
import java.util.Map;

public interface UserMapper {

    List<User> selectUser(Map<String, Integer> map);
}
