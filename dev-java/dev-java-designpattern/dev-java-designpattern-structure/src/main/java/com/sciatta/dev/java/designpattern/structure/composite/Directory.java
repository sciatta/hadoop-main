package com.sciatta.dev.java.designpattern.structure.composite;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangxiaoyu on 2021/6/25<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * Directory
 */
public class Directory extends Node {
    private List<Node> nodes = new ArrayList<>();
    
    public Directory(String name) {
        super(name);
    }
    
    public void addNode(Node node) {
        nodes.add(node);
    }
    
    public List<Node> getNodes() {
        return nodes;
    }
}
