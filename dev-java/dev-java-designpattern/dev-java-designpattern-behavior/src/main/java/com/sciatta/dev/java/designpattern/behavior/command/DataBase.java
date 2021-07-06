package com.sciatta.dev.java.designpattern.behavior.command;

/**
 * Created by yangxiaoyu on 2021/7/6<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * DataBase
 */
public class DataBase {
    public void addData() {
        System.out.println("DataBase add data");
    }
    
    public void deleteData() {
        System.out.println("DataBase delete data");
    }
    
    public void modifyData() {
        System.out.println("DataBase modify data");
    }
    
    public void queryData() {
        System.out.println("DataBase query data");
    }
}
