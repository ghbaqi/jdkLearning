package com.learn.hash;

/**
 * @author gaohui
 * @date 2019/6/3 19:32
 * @description: 一致性 hash 算法
 * <p>
 * <p>
 * https://blog.csdn.net/cywosp/article/details/23397179
 * https://www.cnblogs.com/xrq730/p/5186728.html
 */


/**
 * 判断 hash 算法好坏的四个标准
 * 1. 平衡性
 *    哈希的结果能够尽可能分布到所有的缓冲中去，这样可以使得所有的缓冲空间都得到利用。很多哈希算法都能够满足这一条件。
 * 2. 单调性
 *     如果已经有一些内容通过哈希分派到了相应的缓冲机器中，又有新的缓存机器加入到系统中。
 *     哈希的结果应能够保证原有已分配的内容可以被映射到原有的或者新的机器中去，而不会被映射到旧的缓冲集合中的其他缓冲区。
 * 3. 分散性
 *      不同的终端应该将相同的缓存 ， 存储到同一个缓冲区。避免同一份缓存被存储到不同地方。
 */

// 虚拟节点 的引入解决了  平衡性问题

public interface ConsistentHash {

    // 解决分布式环境下 ， 热点缓存问题

}
