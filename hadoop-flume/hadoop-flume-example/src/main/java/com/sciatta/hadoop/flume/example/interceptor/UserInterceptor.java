package com.sciatta.hadoop.flume.example.interceptor;

import org.apache.commons.codec.Charsets;
import org.apache.flume.Context;
import org.apache.flume.Event;
import org.apache.flume.interceptor.Interceptor;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangxiaoyu on 2020/5/8<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * UserInterceptor
 */
public class UserInterceptor implements Interceptor {
    private int encrypted_field_index;
    private int out_index;

    public UserInterceptor(int encrypted_field_index, int out_index) {
        this.encrypted_field_index = encrypted_field_index;
        this.out_index = out_index;
    }

    @Override
    public void initialize() {

    }

    @Override
    public Event intercept(Event event) {
        String line = new String(event.getBody(), Charsets.UTF_8);
        String newLine = "";
        String[] fields = line.split(",");

        for (int i = 0; i < fields.length; i++) {
            if (i == encrypted_field_index) {
                try {
                    newLine += md5(fields[i]) + ",";
                } catch (NoSuchAlgorithmException e) {
                    throw new RuntimeException(e);
                }
            } else if (i == out_index) {
                // do nothing
            } else {
                newLine += fields[i] + ",";
            }
        }

        newLine = newLine.substring(0, newLine.length() - 1);
        event.setBody(newLine.getBytes(Charsets.UTF_8));

        return event;
    }

    @Override
    public List<Event> intercept(List<Event> list) {
        List<Event> newList = new ArrayList();
        for (Event e : list) {
            Event test = intercept(e);
            if (test != null) {
                newList.add(test);
            }
        }
        return newList;
    }

    @Override
    public void close() {

    }

    private String md5(String plainText) throws NoSuchAlgorithmException {
        byte[] secretBytes = null;

        MessageDigest md5 = MessageDigest.getInstance("MD5");
        md5.update(plainText.getBytes());
        secretBytes = md5.digest();

        String result = new BigInteger(1, secretBytes).toString(16);

        for (int i = 0; i < 32 - result.length(); i++) {
            result = "0" + result;
        }

        return result;
    }

    public static class Builder implements Interceptor.Builder {
        private int encrypted_field_index;
        private int out_index;

        @Override
        public Interceptor build() {
            return new UserInterceptor(encrypted_field_index, out_index);
        }

        @Override
        public void configure(Context context) {
            encrypted_field_index = context.getInteger("encrypted_field_index");
            out_index = context.getInteger("out_index");
        }
    }
}