package com.sciatta.dev.java.designpattern.behavior.memo;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by yangxiaoyu on 2021/7/6<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * InputCharacter
 */
public class InputCharacter {
    private final StringBuilder currentInput = new StringBuilder();
    private final InputCharacterHolder inputHolder = new InputCharacterHolder();
    
    private class InputCharacterHolder {
        private LinkedList<String> redoList = new LinkedList<>();
        private LinkedList<String> undoList = new LinkedList<>();
        
        public void backUp(String str) {
            undoList.addLast(currentInput.toString());
            currentInput.append(str);
            
            redoList.clear();   // 只要正常键入后，清除原有的重做列表
        }
        
        public boolean undo() {
            if (undoList.size() == 0) {
                return false;
            }
            
            redoList.addLast(currentInput.toString());
            currentInput.replace(0, currentInput.toString().length(), undoList.removeLast());
            return true;
        }
        
        public boolean redo() {
            if (redoList.size() == 0) {
                return false;
            }
            
            undoList.addLast(currentInput.toString());
            currentInput.replace(0, currentInput.toString().length(), redoList.removeLast());
            return true;
        }
    }
    
    public void run() {
        Scanner scanner = new Scanner(System.in);
        
        while (scanner.hasNext()) {
            String next = scanner.next();
            
            if ("q".equals(next)) {
                // 退出
                return;
            } else if ("redo".equals(next)) {
                if (!inputHolder.redo()) {
                    System.out.println("不可重做");
                }
            } else if ("undo".equals(next)) {
                if (!inputHolder.undo()) {
                    System.out.println("不可取消");
                }
            } else {
                inputHolder.backUp(next);
            }
            System.out.println("当前输入是：" + currentInput);
        }
    }
    
    public static void main(String[] args) {
        InputCharacter input = new InputCharacter();
        input.run();
    }
}
