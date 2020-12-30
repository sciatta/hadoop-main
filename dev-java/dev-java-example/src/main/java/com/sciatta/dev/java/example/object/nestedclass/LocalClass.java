package com.sciatta.dev.java.example.object.nestedclass;

/**
 * Created by yangxiaoyu on 2019/1/20<br>
 * All Rights Reserved(C) 2017 - 2019 SCIATTA<br><p/>
 * LocalClass
 */
public class LocalClass {
    private static final int VALID_LENGTH = 8;

    public static void checkLength(String test) {
        // 局部类定义在方法内
        class Validator {
            String data;

            Validator(String test) {
                if (test != null && test.length() == VALID_LENGTH) {
                    this.data = test;
                } else {
                    this.data = null;
                }
            }

            public String getData() {
                return this.data;
            }

        }

        Validator validator = new Validator(test);

        if (validator.getData() == null) {
            throw new RuntimeException(test + " invalid");
        } else {
            // do nothing
        }

    }
}
