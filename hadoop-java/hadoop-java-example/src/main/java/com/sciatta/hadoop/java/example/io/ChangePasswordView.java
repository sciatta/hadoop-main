package com.sciatta.hadoop.java.example.io;

import java.io.Console;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by yangxiaoyu on 2020/6/7<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * ChangePasswordView
 */
public class ChangePasswordView {
    private Console console;
    private Scanner scanner;

    private ChangePasswordView() {
        // 需在控制台（com同级目录）运行 java com.sciatta.hadoop.java.example.io.ChangePasswordView
        console = System.console();
        if (console == null) {
            // 当不是命令行启动，或输出重定向，则无法获得Console实例
            System.err.println("No console.");
            scanner = new Scanner(System.in);
        }
    }

    public static ChangePasswordView getInstance() {
        return new ChangePasswordView();
    }

    private String readLine(String hint) {
        if (console != null) {
            return console.readLine(hint);
        }

        System.out.println(hint);
        return scanner.nextLine();
    }

    private char[] readPassword(String hint) {
        if (console != null) {
            return console.readPassword(hint);
        }

        System.out.println(hint);
        return scanner.nextLine().toCharArray();
    }

    private void format(String fmt, Object... args) {
        if (console != null) {
            console.format(fmt, args);
            return;
        }

        System.out.format(fmt, args);
    }


    public void changePassword() {

        String login = readLine("Enter your login: ");
        char[] oldPassword = readPassword("Enter your old password: ");

        if (verify(login, oldPassword)) {
            boolean noMatch;
            do {
                char[] newPassword1 = readPassword("Enter your new password: ");
                char[] newPassword2 = readPassword("Enter new password again: ");
                noMatch = !Arrays.equals(newPassword1, newPassword2);
                if (noMatch) {
                    format("Passwords don't match. Try again.%n");
                } else {
                    change(login, newPassword1);
                    format("Password for %s changed.%n", login);
                }

                // 擦除新密码
                Arrays.fill(newPassword1, ' ');
                Arrays.fill(newPassword2, ' ');
            } while (noMatch);
        }

        // 擦除旧密码
        Arrays.fill(oldPassword, ' ');
    }

    private boolean verify(String login, char[] password) {
        return true;
    }

    private void change(String login, char[] newPassword) {

    }

    public static void main(String[] args) {
        ChangePasswordView passwordView = ChangePasswordView.getInstance();
        passwordView.changePassword();
    }
}
