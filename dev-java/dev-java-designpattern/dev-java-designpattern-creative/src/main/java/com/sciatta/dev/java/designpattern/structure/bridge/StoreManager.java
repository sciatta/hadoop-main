package com.sciatta.dev.java.designpattern.structure.bridge;


import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by yangxiaoyu on 2021/6/25<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * StoreManager
 */
public class StoreManager {
    // 保证线程安全
    // add的时候会copy一份全新的list，在其上操作，不会影响正在迭代的list
    // iterate的时候会指向旧的list迭代
    // add完成后，会将新的list赋值给array，这个array是volatile，所以对其他线程时可见的
    private static CopyOnWriteArrayList<Store> registeredStores = new CopyOnWriteArrayList();
    
    public static void register(Store store) {
        registeredStores.add(store);
    }
    
    public static Store getStore() {
        for (Store store : registeredStores) {
            if (store != null) {
                return store;
            }
        }
        
        return null;
    }
    
}
