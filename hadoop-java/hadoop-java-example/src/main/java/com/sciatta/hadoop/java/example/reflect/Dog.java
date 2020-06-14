package com.sciatta.hadoop.java.example.reflect;

import java.io.Serializable;

/**
 * Created by yangxiaoyu on 2020/6/11<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * Dog
 */
final class Dog<T, V> extends Animal<T, V> implements Serializable {

    private static final long serialVersionUID = -4750216764791636627L;

    public class InnerDogPublic {
    }

    protected class InnerDogProtected {

    }

    private class InnerDogPrivate {
    }

    public Character property = Character.valueOf('c');

    public Object anonymousProperty = new Object() {

    };
}
