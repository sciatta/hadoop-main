package com.sciatta.hadoop.java.example.lambda;

import java.time.LocalDate;

/**
 * Created by yangxiaoyu on 2018/4/4<br>
 * All Rights Reserved(C) 2017 - 2018 SCIATTA<br><p/>
 * Person
 */
public class Person implements Comparable<Person> {

    public enum Sex {
        MALE, FEMALE
    }

    private String name;
    private LocalDate birthday;
    private Sex gender;
    private String emailAddress;

    public Person() {
    }

    public Person(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public Sex getGender() {
        return gender;
    }

    public void setGender(Sex gender) {
        this.gender = gender;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    @Override
    public int compareTo(Person o) {
        return this.name.compareTo(o.name);
    }

    public int asc(Person a, Person b) {
        return a.getName().compareTo(b.getName());
    }

    public static int desc(Person a, Person b) {
        return -a.getName().compareTo(b.getName());
    }
}
