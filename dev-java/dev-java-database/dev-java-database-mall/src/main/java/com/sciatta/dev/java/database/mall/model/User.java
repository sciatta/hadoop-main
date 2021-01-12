package com.sciatta.dev.java.database.mall.model;

import lombok.Data;

/**
 * Created by yangxiaoyu on 2021/1/4<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * User
 */
@Data
public class User {
    private int id;
    private String name;
    private String nickname;
    private String password;
    private String idNumber;
}
