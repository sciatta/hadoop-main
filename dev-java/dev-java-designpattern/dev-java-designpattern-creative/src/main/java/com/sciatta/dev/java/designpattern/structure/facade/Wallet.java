package com.sciatta.dev.java.designpattern.structure.facade;

import java.math.BigDecimal;

/**
 * Created by yangxiaoyu on 2021/6/25<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * Wallet
 */
public class Wallet {
    private String id;
    private String userId;
    private BigDecimal balance;
    
    public Wallet(String id, String userId, BigDecimal balance) {
        this.id = id;
        this.userId = userId;
        this.balance = balance;
    }
    
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getUserId() {
        return userId;
    }
    
    public void setUserId(String userId) {
        this.userId = userId;
    }
    
    public BigDecimal getBalance() {
        return balance;
    }
    
    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}
