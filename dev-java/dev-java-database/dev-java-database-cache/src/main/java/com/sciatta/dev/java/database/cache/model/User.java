package com.sciatta.dev.java.database.cache.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Created by yangxiaoyu on 2021/2/13<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * User
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {
    private static final long serialVersionUID = -4146867365669905L;
    
    private Integer id;
    private String name;
}
