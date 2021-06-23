package com.sciatta.dev.java.designpattern.creative.prototype;

import java.io.*;

/**
 * Created by yangxiaoyu on 2021/6/23<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * Copy
 */
public class Copy implements Cloneable, Serializable {
    
    static class NumberObj implements Serializable {
        private int number;
        
        public NumberObj(int number) {
            this.number = number;
        }
    }
    
    private int number;
    private NumberObj numberObj;
    
    public Copy(int number, NumberObj numberObj) {
        this.number = number;
        this.numberObj = numberObj;
        
        try {
            Thread.sleep(5000); // 创建对象的成本很高
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    public int getNumber() {
        return number;
    }
    
    public NumberObj getNumberObj() {
        return numberObj;
    }
    
    // 浅拷贝
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
    
    // 深拷贝
    public Object deepCopy() {
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
        
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(baos);
            oos.writeObject(this);
            
            ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
            ois = new ObjectInputStream(bais);
            
            return ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (oos != null) oos.close();
                if (ois != null) ois.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
        return null;
    }
}
