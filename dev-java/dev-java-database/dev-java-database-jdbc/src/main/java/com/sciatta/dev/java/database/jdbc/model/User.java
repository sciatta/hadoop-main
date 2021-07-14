package com.sciatta.dev.java.database.jdbc.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Created by yangxiaoyu on 2021/1/4<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * User
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private int id;
    private String name;
    private String nickname;
    private String password;
    private String idNumber;
}
