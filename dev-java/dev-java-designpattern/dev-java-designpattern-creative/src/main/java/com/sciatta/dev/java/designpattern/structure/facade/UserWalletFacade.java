package com.sciatta.dev.java.designpattern.structure.facade;

/**
 * Created by yangxiaoyu on 2021/6/25<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * UserWalletFacade
 */
public class UserWalletFacade {
    private final UserService userService;
    private final WalletService walletService;
    
    public UserWalletFacade(UserService userService, WalletService walletService) {
        this.userService = userService;
        this.walletService = walletService;
    }
    
    public String registerUserAndCreateWallet(String userName) {
        User user = userService.register(userName);
        Wallet wallet = walletService.createWallet(user);
        return wallet.getId();
    }
}
