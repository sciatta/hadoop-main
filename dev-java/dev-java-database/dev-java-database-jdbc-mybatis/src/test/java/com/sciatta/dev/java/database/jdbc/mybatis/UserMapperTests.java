package com.sciatta.dev.java.database.jdbc.mybatis;

import com.sciatta.dev.java.database.jdbc.mybatis.dao.UserMapper;
import com.sciatta.dev.java.database.jdbc.mybatis.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.io.Reader;

/**
 * Created by yangxiaoyu on 2021/7/16<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * UserMapperTests
 */
public class UserMapperTests {
    private static SqlSession sqlSession;
    
    @BeforeClass
    public static void initUserMapper() throws IOException {
        String resource = "mybatis-config.xml";
        Reader reader = Resources.getResourceAsReader(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        sqlSession = sqlSessionFactory.openSession();
    }
    
    @Test
    public void testInsert() {
        try {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            mapper.insertUser(newUser());
            sqlSession.commit();    // 提交事务
        } finally {
            sqlSession.close();
        }
    }
    
    private User newUser() {
        return new User("rain", "BigRain", "secret", "1234567890");
    }
    
    @Test
    public void testQuery() {
        try {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            User user = mapper.getUser(1);
            System.out.println(user);
        } finally {
            sqlSession.close();
        }
    }
}
