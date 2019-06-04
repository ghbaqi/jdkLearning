package com.learn.hash;

/**
 * @author gaohui
 * @date 2019/6/3 21:46
 * @description: 引入虚拟节点 解决负载不均衡问题
 * <p>
 * 将一个物理节点拆分为多个虚拟节点，并且同一个物理节点的虚拟节点尽量均匀分布在Hash环上。
 */

import java.util.Arrays;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

import lombok.extern.slf4j.Slf4j;

/**
 * 需要多少个虚拟节点 ？
 * 根据真正的物理机器的数量 ， 动态计算虚拟节点的数量 。 根据一个经验系数来计算 ?
 * <p>
 * 假设总节点数量为 16384
 * ===>  虚拟节点数量 = 16384 / 物理机器数量 ,   即为每台机器分配的虚拟节点个数
 *
 * <p>
 * 如何为真实节点映射虚拟节点 ？
 * 解决办法很简单 ，拼接字符串即可
 * <p>
 * 找到虚拟节点后如何对应上真实节点 ？
 */

// todo  1. 总虚拟节点数量可调节  2. 服务增加 减少时 , hash 环重新分配

@Slf4j
public class VirtualNodeConsistentHash {

    private static final int TOTAL_VIRTUAL_NODES = 16384;
    //    private static final int TOTAL_VIRTUAL_NODES = 20;

    private List<String>               servers;
    private int                        virtualNodeNum;
    private SortedMap<Integer, String> hashServerMap = new TreeMap<>();

    private static final String VIRTUAL_NODE_PREFIX = "virtualNode@";


    public VirtualNodeConsistentHash(List<String> servers) {
        this.servers = servers;
        if (servers.isEmpty()) {
            throw new RuntimeException("服务器数量不能为空");
        }
        virtualNodeNum = TOTAL_VIRTUAL_NODES / servers.size();

        // 初始化 hash 环 。将 servers 放到 hash 环中
        for (String s : servers) {
            // 增加虚拟节点
            String virtualKey;
            for (int i = 0; i < virtualNodeNum; i++) {
                virtualKey = i + VIRTUAL_NODE_PREFIX + s;
                hashServerMap.put(hashCode(virtualKey), s);
            }
        }

        log.info("hash 环初始化完毕 , 服务数量 = {} , 每个服务虚拟节点数 = {} , 总节点数 = {}", servers.size(), virtualNodeNum, hashServerMap.size());
    }

    /**
     * 根据要存储的 key ，找它应该被存储的服务器
     */
    public String getNode(String key) {
        int hashCode = hashCode(key);
        SortedMap<Integer, String> tailMap = hashServerMap.tailMap(hashCode);
        // 此范围内的存储对象 ， 找的是 hashCode 最小的 server
        if (tailMap.isEmpty()) {
            log.info("key hash 值比较大 = {} , 服务虚拟节点最大的 hash = {}", hashCode, hashServerMap.lastKey());
            String s = hashServerMap.get(hashServerMap.firstKey());
            return s == null ? null : s.substring(s.indexOf("@") + 1);
        }
        // 找下一个比当前 hash 大的 server 即可
        String s = tailMap.get(tailMap.firstKey());
        return s == null ? null : s.substring(s.indexOf("@") + 1);
    }


    private int hashCode(String key) {
        final int p = 16777619;
        int hash = (int) 2166136261L;
        for (int i = 0; i < key.length(); i++)
            hash = (hash ^ key.charAt(i)) * p;
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


    public static void main(String[] args) {
        List<String> serverList = Arrays.asList("192.168.0.1", "192.168.0.2", "192.168.0.3", "192.168.0.4", "192.168.0.5");
        VirtualNodeConsistentHash virtualConsistentHash = new VirtualNodeConsistentHash(serverList);

        List<String> keyList = Arrays.asList("a", "a1", "b", "b6", "c", "ab", "!", "@", "234334");

        for (String key : keyList) {
            String server = virtualConsistentHash.getNode(key);
            System.out.println(server);
        }

    }
}
