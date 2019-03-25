package com.learn.thread.atomic;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author : gaohui
 * @Date : 2019/2/26 15:40
 * @Description : AtomicInteger 类学习
 */

public class Test01 {

    ExecutorService threadPool = Executors.newCachedThreadPool();

    /**
     * AtomicInteger
     */
    @Test
    public void test01() throws InterruptedException {
        final AtomicInteger atomicInteger = new AtomicInteger();
        final CountDownLatch latch = new CountDownLatch(12);
        for (int i = 0; i < 12; i++) {
            threadPool.submit(() -> {
                System.out.println(atomicInteger.getAndIncrement());
                latch.countDown();
            });
        }
        latch.await();
        System.out.println("finish ...");
        System.out.println(atomicInteger.get());

    }

}
