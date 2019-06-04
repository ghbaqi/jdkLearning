package com.learn.hash;

/**
 * @author gaohui
 * @date 2019/6/3 20:31
 * @description: 第一版实现
 * <p>
 * 不引入虚拟节点
 */

import java.util.Arrays;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * hash 环取值范围  [ 0 , 2^32 - 1 ]
 * <p>
 * <p>
 * 1. 如何构造整数环 ?
 * 2. 如何让 hash 值尽可能分散  0 - 4294967296
 */
public class ConsistentHash01 {


    private static String[] servers = {
            "192.168.0.0:111", "192.168.0.1:111", "192.168.0.2:111",
            "192.168.0.3:111", "192.168.0.4:111"
    };

    private static SortedMap<Integer, String> map = new TreeMap<>();

    static {
        for (String server : servers) {
            int hash = getHash(server);
            map.put(hash, server);
        }
    }

    private static int getHash(String str) {
        final int p = 16777619;
        int hash = (int) 2166136261L;
        for (int i = 0; i < str.length(); i++)
            hash = (hash ^ str.charAt(i)) * p;
        hash += hash << 13;
        hash ^= hash >> 7;
        hash += hash << 3;
        hash ^= hash >> 17;
        hash += hash << 5;

        // 如果算出来的值为负数则取其绝对值
        if (hash < 0)
            hash = Math.abs(hash);
        return hash;
    }


    private static String getServer(String val) {

        int hash = getHash(val);
        // 得到大于该Hash值的所有Map
        SortedMap<Integer, String> tailMap = map.tailMap(hash);

        if (tailMap.isEmpty()) {
            //            Integer max = map.lastKey();
            //            throw new RuntimeException("没有找到比当前 hash 值大的 server , 最大的 server hash = "+max);
            Integer min = map.firstKey();
            System.out.println("找最小的 server minHash = " + min);
            return map.get(min);
        }

        // todo 如果此处 tailMap 为空时 ，需要额外判断
        // 第一个Key就是顺时针过去离node最近的那个结点
        Integer key = tailMap.firstKey();
        return tailMap.get(key);
    }

    public static void main(String[] args) {
        List<String> list = Arrays.asList("a", "a1", "b", "b1", "abc", "def");

        for (String s : list) {
            System.out.print("hash = " + getHash(s));
            System.out.println(" , server = " + getServer(s));
        }
    }


}
