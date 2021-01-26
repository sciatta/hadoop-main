package com.sciatta.dev.java.algorithm.linear.array.impl;

/**
 * Created by yangxiaoyu on 2020/10/10<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * 非顺序表，不保证原有元素的逻辑位置
 */
public class NoneSequenceList extends SequenceList {
    public NoneSequenceList(int maxSize) {
        super(maxSize);
    }

    /**
     * 时间复杂度O(1)，均摊时间复杂度O(1)
     * @param data
     */
    @Override
    public void insert(Integer data) {
        super.insert(data);

        // 碎片整理
        reorganize();
    }

    /**
     * 时间复杂度O(1)，均摊时间复杂度O(1)
     *
     * @param data
     * @param i
     */
    @Override
    public void insert(Integer data, int i) {
        if (i > count) {
            throw new IllegalArgumentException("插入元素位置不合法 " + i);
        }

        if (count == length) {
            throw new IndexOutOfBoundsException("超出最大长度 " + length);
        }

        // 原插入位置元素放到数组最后，再插入元素
        datas[count] = datas[i];
        datas[i] = data;
        count++;

        // 碎片整理
        reorganize();
    }

    /**
     * 时间复杂度O(1)，均摊时间复杂度O(1)
     *
     * @param data
     */
    @Override
    public void delete(Integer data) {
        int index = find(data);

        if (index == -1) {
            System.out.println("没有找到待删除数据 " + data);
            return;
        }

        // 删除数据只打标记，不进行搬移提高删除效率；但会产生碎片
        datas[index] = null;

        // 碎片整理
        reorganize();
    }

    private void reorganize() {
        if (count == length) {
            // 顺序表被耗尽，尝试清除碎片
            Integer[] newDatas = new Integer[this.length];
            int newCount = 0;
            for (int i = 0; i < count; i++) {
                if (datas[i] != null) {
                    newDatas[newCount++] = datas[i];
                }
            }
            datas = newDatas;
            count = newCount;
        }
    }
}
