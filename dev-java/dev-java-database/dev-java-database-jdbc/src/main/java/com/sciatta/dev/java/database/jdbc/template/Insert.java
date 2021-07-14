package com.sciatta.dev.java.database.jdbc.template;

/**
 * Created by yangxiaoyu on 2021/7/14<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * Insert
 */
public class Insert extends AbstractJdbcExecute {
    public void execute() {
        execute("insert into users(name,nickname,password,id_number) values('root','admin','root','123456789')",
                System.out::println);
    }
    
    public static void main(String[] args) {
        Insert insert = new Insert();
        insert.execute();
    }
}
