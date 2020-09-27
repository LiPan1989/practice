package com.lee.test.collections.list;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author cdlipan5
 * @create 2020-01-02 上午9:35
 **/
public class ListTest {
    @Test
    public void test1() {
        List<Integer> allList = Lists.newArrayList(0, 1, 2, 3, 4, 5, 6, 7, 8);
        List<List<Integer>> partitions = Lists.partition(allList, 2);
        partitions = new ArrayList<>(partitions);
//        System.out.println(partitions);
        for (List<Integer> partition : partitions) {
            ArrayList<Integer> newList = Lists.newArrayList(partition);
            Sets.newHashSet(partition);
            newList.removeAll(Lists.newArrayList(3));
            System.out.println(newList);
        }
    }
}
