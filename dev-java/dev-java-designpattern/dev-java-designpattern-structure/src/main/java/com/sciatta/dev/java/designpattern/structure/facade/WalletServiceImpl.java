package com.sciatta.dev.java.designpattern.structure.facade;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * Created by yangxiaoyu on 2021/6/25<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * WalletServiceImpl
 */
public class WalletServiceImpl implements WalletService {
    @Override
    public Wallet createWallet(User user) {
        System.out.println(user.getUserName() + " create wallet success");
        
        return new Wallet(UUID.randomUUID().toString(), user.getId(), BigDecimal.valueOf(100));
    }
}
