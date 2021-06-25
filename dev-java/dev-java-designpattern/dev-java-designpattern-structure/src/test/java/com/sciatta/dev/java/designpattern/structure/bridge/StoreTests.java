package com.sciatta.dev.java.designpattern.structure.bridge;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by yangxiaoyu on 2021/6/25<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * StoreTests
 */
public class StoreTests {
    @Test
    public void testNullStore() {
        Store store = StoreManager.getStore();
        assertNull(store);
    }
    
    @Test
    public void testHdfsStore() {
        try {
            Class.forName("com.sciatta.dev.java.designpattern.structure.bridge.HdfsStore"); // 加载driver，向StoreManager注册自己
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        
        Store store = StoreManager.getStore();
        assertNotNull(store);
        
        String path = "/test/test";
        String id = store.put(path);
        assertEquals(path, store.get(id));
    }
    
    @Test
    public void testMockStore() {
        class MockStore implements Store {
            
            @Override
            public String put(String localPath) {
                return localPath;
            }
            
            @Override
            public String get(String id) {
                return id;
            }
        }
        
        StoreManager.register(new MockStore()); // 注册
        
        Store store = StoreManager.getStore();
        assertEquals("1", store.put("1"));
        assertEquals("1", store.get("1"));
    }
}
