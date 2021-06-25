package com.sciatta.dev.java.designpattern.structure.composite;

/**
 * Created by yangxiaoyu on 2021/6/25<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * FileSystem
 */
public class FileSystem {
    private Node root;
    private int fileNum;
    private int nodeNum;
    
    public FileSystem(Node root) {
        this.root = root;
    }
    
    public boolean findFile(String name) {
        if (root instanceof File) {
            return root.getName().equals(name);
        } else {
            return doFind((Directory) root, name);
        }
    }
    
    private boolean doFind(Directory dir, String name) {
        
        for (Node node : dir.getNodes()) {
            if (node instanceof File) {
                if (node.getName().equals(name)) {
                    return true;
                }
            } else {
                if (doFind((Directory) node, name)) {
                    return true;
                }
            }
        }
        
        return false;
    }
    
    public int countFile() {
        fileNum = 0;    // 初始化
        
        if (root instanceof File) {
            return 1;
        } else {
            doCountFile((Directory) root);
        }
        return fileNum;
    }
    
    public void doCountFile(Directory dir) {
        for (Node node : dir.getNodes()) {
            if (node instanceof File) {
                fileNum++;
            } else {
                doCountFile((Directory) node);
            }
        }
    }
    
    public int countNode() {
        nodeNum = 0;
        
        if (root instanceof File) {
            return 1;
        } else {
            nodeNum++;
            doCountNode((Directory) root);
        }
        return nodeNum;
    }
    
    public void doCountNode(Directory dir) {
        for (Node node : dir.getNodes()) {
            nodeNum++;
            if (node instanceof Directory) {
                doCountNode((Directory) node);
            }
        }
    }
}
