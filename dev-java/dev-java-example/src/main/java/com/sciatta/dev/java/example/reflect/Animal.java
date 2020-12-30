package com.sciatta.dev.java.example.reflect;

import java.io.Serializable;

/**
 * Created by yangxiaoyu on 2020/6/11<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * Animal
 */
abstract class Animal<T, V> implements Serializable {

    private static final long serialVersionUID = 8271745142711170456L;

    public static class InnerAnimalPublic {

    }

    protected class InnerAnimalProtected {

    }

    private class InnerAnimalPrivate {

    }
}
