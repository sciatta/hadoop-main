package com.sciatta.dev.java.spring.core.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangxiaoyu on 2020/12/14<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * Klass
 */
public class Klass {
    private String Name;
    private List<Student> students = new ArrayList<>();
    
    public String getName() {
        return Name;
    }
    
    public void setName(String name) {
        Name = name;
    }
    
    public List<Student> getStudents() {
        return students;
    }
    
    public void setStudents(List<Student> students) {
        this.students = students;
    }
}
