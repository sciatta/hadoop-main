package com.sciatta.dev.java.example.io.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by yangxiaoyu on 2021/3/19<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * BIO
 */
public class BIO {
    public static void main(String[] args) throws IOException {
        ServerSocket socket = new ServerSocket(8888);
        
        while (true) {
            Socket accept = socket.accept();
            System.out.println("====connect====" + accept);
            
            BufferedReader reader = new BufferedReader(new InputStreamReader(accept.getInputStream()));
            PrintWriter writer = new PrintWriter(accept.getOutputStream(), true);
            
            try {
                String line = reader.readLine();
                System.out.println("====request====" + line);
                
                writer.println(line);
                System.out.println("====response====" + line);
            } finally {
                reader.close();
                writer.close();
                accept.close();
            }
        }
    }
}
