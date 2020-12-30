package com.sciatta.dev.java.spring.boot.schoolstarter.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangxiaoyu on 2020/12/14<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * School
 */
public class School {
    private String name;
    private List<Klass> klasses = new ArrayList<>();
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public List<Klass> getKlasses() {
        return klasses;
    }
    
    public void setKlasses(List<Klass> klasses) {
        this.klasses = klasses;
    }
}
