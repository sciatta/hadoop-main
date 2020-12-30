package com.sciatta.dev.java.lombok;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Created by yangxiaoyu on 2020/12/19<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * Person
 */
@Data   // get | set | equals | hashCode | toString
@NoArgsConstructor  // 无参构造函数
@AllArgsConstructor // 所有参数的构造函数
public class User {
    private String name;
}
