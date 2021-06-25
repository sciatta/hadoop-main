package com.sciatta.dev.java.designpattern.structure.facade;

import org.junit.Test;

/**
 * Created by yangxiaoyu on 2021/6/25<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * UserWalletFacadeTests
 */
public class UserWalletFacadeTests {
    @Test
    public void testService() {
        UserService userService = new UserServiceImpl();
        WalletService walletService = new WalletServiceImpl();
        
        // 客户端请求第一次api
        User user = userService.register("test");
        
        // 客户端请求第二次api
        Wallet wallet = walletService.createWallet(user);
        
        System.out.println("walletId " + wallet.getId());
    }
    
    @Test
    public void testFacade() {
        UserWalletFacade service = new UserWalletFacade(new UserServiceImpl(), new WalletServiceImpl());
    
        // 客户端请求第一次api
        String walletId = service.registerUserAndCreateWallet("test");
    
        System.out.println("walletId " + walletId);
    }
}
