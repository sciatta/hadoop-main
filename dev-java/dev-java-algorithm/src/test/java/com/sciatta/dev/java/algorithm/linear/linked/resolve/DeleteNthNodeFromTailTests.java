package com.sciatta.dev.java.algorithm.linear.linked.resolve;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by yangxiaoyu on 2021/1/27<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * DeleteNthNodeFromTailTests
 */
public class DeleteNthNodeFromTailTests {
    @Test
    public void testZeroNode() {
        DeleteNthNodeFromTail deleteNthNodeFromTail = new DeleteNthNodeFromTail(new Integer[]{});
        assertNull(deleteNthNodeFromTail.deleteNthNode(0));
    }
    
    @Test
    public void testOutOfRange() {
        DeleteNthNodeFromTail deleteNthNodeFromTail = new DeleteNthNodeFromTail(new Integer[]{1, 2});
        assertNull(deleteNthNodeFromTail.deleteNthNode(3));
    }
    
    @Test
    public void testDeleteTailOne() {
        DeleteNthNodeFromTail deleteNthNodeFromTail = new DeleteNthNodeFromTail(new Integer[]{1, 2, 3, 5});
        assertEquals(5, deleteNthNodeFromTail.deleteNthNode(1).v.intValue());
        assertArrayEquals(new Integer[]{1, 2, 3}, deleteNthNodeFromTail.toArray());
    }
    
    @Test
    public void testDeleteTailLast() {
        DeleteNthNodeFromTail deleteNthNodeFromTail = new DeleteNthNodeFromTail(new Integer[]{1, 2, 3});
        assertEquals(1, deleteNthNodeFromTail.deleteNthNode(3).v.intValue());
        assertArrayEquals(new Integer[]{2, 3}, deleteNthNodeFromTail.toArray());
    }
    
    @Test
    public void testDeleteMiddle() {
        DeleteNthNodeFromTail deleteNthNodeFromTail = new DeleteNthNodeFromTail(new Integer[]{4, 1, 2, 3});
        assertEquals(2, deleteNthNodeFromTail.deleteNthNode(2).v.intValue());
        assertArrayEquals(new Integer[]{4, 1, 3}, deleteNthNodeFromTail.toArray());
    }
}
