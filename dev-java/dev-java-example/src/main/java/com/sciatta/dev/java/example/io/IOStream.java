package com.sciatta.dev.java.example.io;

import java.io.*;

/**
 * Created by yangxiaoyu on 2019-02-23<br>
 * All Rights Reserved(C) 2017 - 2019 SCIATTA<br><p/>
 * IOStream
 */
public class IOStream {
    public static void copyBytesFromFile(String src, String dest) throws IOException {
        InputStream fis = null;
        OutputStream fos = null;

        try {
            fis = new BufferedInputStream(new FileInputStream(src));
            fos = new BufferedOutputStream(new FileOutputStream(dest));

            // DataStream filter example
            // fis = new DataInputStream(fis);
            // fos = new DataOutputStream(fos);

            // ObjectStream filter example
            // fis = new ObjectInputStream(fis);
            // fos = new ObjectOutputStream(fos);

            int c;
            while ((c = fis.read()) != -1) {
                fos.write(c);
            }
        } catch (FileNotFoundException e) {
            System.err.println(e);
            throw e;
        } catch (IOException e) {
            System.err.println(e);
            throw e;
        } finally {
            try {
                if (fis != null) {
                    fis.close();
                }
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException e) {
                System.err.println(e);
                throw e;
            }
        }
    }

    public static void copyCharactersFromFile(String src, String dest) throws IOException {
        Reader fr = null;
        Writer fw = null;

        try {
            fr = new BufferedReader(new FileReader(src));
            fw = new BufferedWriter(new FileWriter(dest));

            int c;
            while ((c = fr.read()) != -1) {
                fw.write(c);
            }
        } finally {
            if (fr != null) {
                fr.close();
            }
            if (fw != null) {
                fw.close();
            }
        }
    }

    public static String copyBytesFromString(String from) throws IOException {
        if (from == null) {
            throw new IllegalArgumentException();
        }
        InputStream is = null;
        OutputStream os = null;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] fromBytes = from.getBytes();
        byte[] toBytes;

        try {
            is = new BufferedInputStream(new ByteArrayInputStream(fromBytes));
            os = new BufferedOutputStream(baos);

            int c;
            while ((c = is.read()) != -1) {
                os.write(c);
            }

            // 刷新BufferedOutputStream内部缓存数据到ByteArrayOutputStream
            os.flush();
            toBytes = baos.toByteArray();
        } finally {
            if (is != null) {
                is.close();
            }
            if (os != null) {
                os.close();
            }
        }

        return new String(toBytes, 0, toBytes.length);
    }

    public static String copyCharactersFromString(String from) throws IOException {
        Reader r = null;
        Writer w = null;
        CharArrayWriter caw = new CharArrayWriter();
        char[] charFrom = from.toCharArray();
        char[] charTo = null;

        try {
            r = new BufferedReader(new CharArrayReader(charFrom));
            w = new BufferedWriter(caw);
            int c;
            while ((c = r.read()) != -1) {
                w.write(c);
            }

            w.flush();
            charTo = caw.toCharArray();
        } finally {
            if (r != null) {
                r.close();
            }
            if (w != null) {
                w.close();
            }
        }

        return new String(charTo);
    }

    public static String replaceTwoNumberAsOneAsterisk(String str) throws IOException {
        byte[] from = str.getBytes();
        byte[] to = new byte[from.length];  // 最大长度from.length
        PushbackInputStream pis = null;
        int length = 0;

        try {
            pis = new PushbackInputStream(new ByteArrayInputStream(from));
            int c;
            int i = 0;
            while ((c = pis.read()) != -1) {
                if (Character.isDigit(c)) {
                    // 预读一个字节
                    int nextC = pis.read();
                    if (Character.isDigit(nextC)) {
                        to[i++] = '*';
                    } else {
                        to[i++] = (byte) c;
                        // 退回预读的一个字节
                        pis.unread(nextC);
                    }
                } else {
                    to[i++] = (byte) c;
                }
            }
            length = i;
        } finally {
            if (pis != null) {
                pis.close();
            }
        }
        return new String(to, 0, length);
    }
}
