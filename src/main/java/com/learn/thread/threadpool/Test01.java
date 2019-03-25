package com.learn.thread.threadpool;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author : gaohui
 * @Date : 2019/2/27 10:14
 * @Description :
 */
public class Test01 {

    private ExecutorService threadPool02 = Executors.newFixedThreadPool(2);

    /**
     * 测试 newFixedThreadPool
     * 任务过多时 ， 并没有出现丢弃任务
     */
    @Test
    public void test02() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(100);
        for (int i = 1; i <= 100; i++) {
            int finalI = i;
            threadPool02.submit(() -> {
                System.out.println(" i = " + finalI);
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                latch.countDown();
            });
        }

        latch.await();
        System.out.println("finish ...");
    }

    /**
     * 测试无界队列引起 OOM
     */
    @Test
    public void test03() throws InterruptedException {

        for (; ; ) {
            threadPool02.submit(new Runnable() {
                @Override
                public void run() {
                    Object o = new Object();
                    try {
                        Thread.sleep(100000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    @Test
    public void test04() {
        tt:
        for (int i = 0; i < 2; i++) {
            System.out.print("out");
            for (int i1 = 0; i1 < 3; i1++) {
                if (i == 1) {
                    break tt;
                }
                System.out.print("in");
            }
            System.out.println();
        }
    }


    @Test
    public void test05() throws InterruptedException {

        threadPool02.submit(() -> System.out.println("run 1"));
        threadPool02.shutdown();
        threadPool02.submit(() -> System.out.println("run 2"));


        Thread.sleep(3000);

    }

    @Test
    public void test06()  {

       new Thread().start();

    }

}































