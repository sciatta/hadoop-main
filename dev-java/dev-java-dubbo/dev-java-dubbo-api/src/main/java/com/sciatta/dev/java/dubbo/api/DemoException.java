package com.sciatta.dev.java.dubbo.api;

/**
 * Created by yangxiaoyu on 2021/2/2<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * DemoException
 */
public class DemoException extends Exception {
    
    private static final long serialVersionUID = 3721534024568966521L;
    
    public DemoException() {
        super();
    }
    
    public DemoException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public DemoException(String message) {
        super(message);
    }
    
    public DemoException(Throwable cause) {
        super(cause);
    }
    
}
