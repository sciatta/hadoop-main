package com.sciatta.hadoop.java.spring.core.aop.introduction;

/**
 * Created by yangxiaoyu on 2020/11/26<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * PrintImpl
 */
public class PrintImpl implements Print {
    @Override
    public void doPrint(String userName) {
        System.out.println("功能加强: " + userName);
    }
}
