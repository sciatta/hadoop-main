package com.sciatta.dev.java.designpattern.structure.proxy.staticproxy;

/**
 * Created by yangxiaoyu on 2021/6/25<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * BasedClass
 */
public class BasedClass {
    static class User {
        private String id;
        
        public User(String id) {
            this.id = id;
        }
    
        public String getId() {
            return id;
        }
    }
    
    static class UserDao {
        public User queryUserById(String id) {
            System.out.println("query database..." + id);
            return new User(id);
        }
    }
    
    static class UserDaoProxy extends UserDao {
        @Override
        public User queryUserById(String id) {
            try {
                System.out.println("in proxy");
                return super.queryUserById(id);
            } finally {
                System.out.println("out proxy");
            }
        }
    }
}
