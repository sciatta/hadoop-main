package com.sciatta.dev.java.database.datasource.service.impl;

import com.sciatta.dev.java.database.datasource.dao.UserDao;
import com.sciatta.dev.java.database.datasource.lookup.CurrentDataSource;
import com.sciatta.dev.java.database.datasource.lookup.DataSourceEnum;
import com.sciatta.dev.java.database.datasource.model.User;
import com.sciatta.dev.java.database.datasource.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by yangxiaoyu on 2021/1/12<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * DynamicUserServiceImpl
 */
@Service("dynamic")
public class DynamicUserServiceImpl implements UserService {
    private final UserDao userDao;
    
    public DynamicUserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }
    
    @Override
    @CurrentDataSource
    public int insertUser(User user) {
        return userDao.insertUser(user);
    }
    
    @Override
    @CurrentDataSource
    public int deleteUser(int id) {
        return userDao.deleteUser(id);
    }
    
    @Override
    @CurrentDataSource(name = DataSourceEnum.MASTER)
    public int updateUserName(User user) {
        return userDao.updateUserName(user);
    }
    
    @Override
    @CurrentDataSource(name = DataSourceEnum.SLAVE)
    public User getUserById(int id) {
        return userDao.getUserById(id);
    }
    
    @Override
    @CurrentDataSource(name = DataSourceEnum.SLAVE)
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }
}
