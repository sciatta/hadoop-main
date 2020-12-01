package com.sciatta.hadoop.java.spring.core.aop.jdk;

import com.sciatta.hadoop.java.spring.core.aop.common.Name;
import sun.misc.ProxyGenerator;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;

/**
 * Created by yangxiaoyu on 2020/12/1<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * ProxyClass
 */
public class ProxyClass {
    public static void main(String[] args) throws IOException, URISyntaxException {
        String name = "ProxyClassOutput";
        byte[] outs = ProxyGenerator.generateProxyClass(name, new Class[]{Name.class});
        FileOutputStream outputStream = new FileOutputStream(getOutPutPath() + name + ".class");
        outputStream.write(outs);
    }
    
    private static String getOutPutPath() throws URISyntaxException {
        String path = ProxyClass.class.getResource("").toURI().getRawPath();
        System.out.println(path);
        return path;
    }
}
