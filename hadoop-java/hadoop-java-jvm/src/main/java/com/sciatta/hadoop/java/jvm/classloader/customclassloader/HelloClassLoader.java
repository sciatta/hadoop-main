package com.sciatta.hadoop.java.jvm.classloader.customclassloader;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Base64;

/**
 * Created by yangxiaoyu on 2020/10/19<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * load class 自下至上先从缓存中查找 -> find class 自上至下委托给父ClassLoader查找
 */
public class HelloClassLoader extends ClassLoader {
    public HelloClassLoader() {
        // 默认的parent是AppClassLoader
        super();
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        // base64 Hello.class
        String base64HelloClass = "yv66vgAAADQALwoACwAaCQAbABwHAB0KAAMAGggAHgoAAwAfCgADACAKACEAIggAIwcAJAcAJQEABjxpbml0Pg" +
                "EAAygpVgEABENvZGUBAA9MaW5lTnVtYmVyVGFibGUBABJMb2NhbFZhcmlhYmxlVGFibGUBAAR0aGlzAQBBTGNvbS9zY2lhdHRhL2hhZG" +
                "9vcC9qYXZhL2p2bS9jbGFzc2xvYWRlci9jdXN0b21jbGFzc2xvYWRlci9IZWxsbzsBAARlY2hvAQAVKExqYXZhL2xhbmcvU3RyaW5nOy" +
                "lWAQABcwEAEkxqYXZhL2xhbmcvU3RyaW5nOwEACDxjbGluaXQ+AQAKU291cmNlRmlsZQEACkhlbGxvLmphdmEMAAwADQcAJgwAJwAoAQ" +
                "AXamF2YS9sYW5nL1N0cmluZ0J1aWxkZXIBAAZlY2hvOiAMACkAKgwAKwAsBwAtDAAuABQBABhIZWxsbyBDbGFzcyBJbml0aWFsaXplZC" +
                "EBAD9jb20vc2NpYXR0YS9oYWRvb3AvamF2YS9qdm0vY2xhc3Nsb2FkZXIvY3VzdG9tY2xhc3Nsb2FkZXIvSGVsbG8BABBqYXZhL2xhbm" +
                "cvT2JqZWN0AQAQamF2YS9sYW5nL1N5c3RlbQEAA291dAEAFUxqYXZhL2lvL1ByaW50U3RyZWFtOwEABmFwcGVuZAEALShMamF2YS9sYW" +
                "5nL1N0cmluZzspTGphdmEvbGFuZy9TdHJpbmdCdWlsZGVyOwEACHRvU3RyaW5nAQAUKClMamF2YS9sYW5nL1N0cmluZzsBABNqYXZhL2" +
                "lvL1ByaW50U3RyZWFtAQAHcHJpbnRsbgAhAAoACwAAAAAAAwABAAwADQABAA4AAAAvAAEAAQAAAAUqtwABsQAAAAIADwAAAAYAAQAAAA" +
                "gAEAAAAAwAAQAAAAUAEQASAAAAAQATABQAAQAOAAAAUgADAAIAAAAasgACuwADWbcABBIFtgAGK7YABrYAB7YACLEAAAACAA8AAAAKAA" +
                "IAAAAOABkADwAQAAAAFgACAAAAGgARABIAAAAAABoAFQAWAAEACAAXAA0AAQAOAAAAJQACAAAAAAAJsgACEgm2AAixAAAAAQAPAAAACg" +
                "ACAAAACgAIAAsAAQAYAAAAAgAZ";

        byte[] bytes = Base64.getDecoder().decode(base64HelloClass);
        return defineClass(name, bytes, 0, bytes.length);
    }

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        // 注意base64位加密的class文件有package信息，必须一致
        // 需要删除此Hello源文件，否则优先加载AppClassLoader下的Hello文件，不会走到HelloClassLoader

        //        public class Hello {
        //            static {
        //                System.out.println("Hello Class Initialized!");
        //            }
        //
        //            public void echo(String s) {
        //                System.out.println("echo: " + s);
        //            }
        //        }

        Class c = new HelloClassLoader().loadClass("com.sciatta.hadoop.java.jvm.classloader.customclassloader.Hello");
        // 获得当前类和父类的public方法
        Method echo = c.getMethod("echo", String.class);

        echo.invoke(c.newInstance(), "hello");
    }
}
