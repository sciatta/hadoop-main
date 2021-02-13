package com.sciatta.dev.java.database.cache.dao;

import com.sciatta.dev.java.database.cache.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by yangxiaoyu on 2021/2/13<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * UserMapper
 */
@Mapper
@Repository
public interface UserMapper {
    // http://localhost:8888/user/find\?id\=1
    User find(int id);
    
    // http://localhost:8888/user/list
    List<User> list();
}
