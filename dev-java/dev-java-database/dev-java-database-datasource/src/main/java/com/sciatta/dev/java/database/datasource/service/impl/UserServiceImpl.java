package com.sciatta.dev.java.database.datasource.service.impl;

import com.sciatta.dev.java.database.datasource.dao.UserMasterDao;
import com.sciatta.dev.java.database.datasource.dao.UserSlaveDao;
import com.sciatta.dev.java.database.datasource.model.User;
import com.sciatta.dev.java.database.datasource.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by yangxiaoyu on 2021/1/11<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * UserServiceImpl
 */
@Service
public class UserServiceImpl implements UserService {
    private UserMasterDao userMasterDao;
    private UserSlaveDao userSlaveDao;
    
    public UserServiceImpl(UserMasterDao userMasterDao, UserSlaveDao userSlaveDao) {
        this.userMasterDao = userMasterDao;
        this.userSlaveDao = userSlaveDao;
    }
    
    @Override
    public int insertUser(User user) {
        return userMasterDao.insertUser(user);
    }
    
    @Override
    public int deleteUser(int id) {
        return userMasterDao.deleteUser(id);
    }
    
    @Override
    public int updateUserName(User user) {
        return userMasterDao.updateUserName(user);
    }
    
    @Override
    public User getUserById(int id) {
        return userSlaveDao.getUserById(id);
    }
    
    @Override
    public List<User> getAllUsers() {
        return userSlaveDao.getAllUsers();
    }
}
