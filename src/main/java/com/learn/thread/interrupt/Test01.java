package com.learn.thread.interrupt;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;

/**
 * @author : gaohui
 * @Date : 2019/2/27 10:20
 * @Description :
 */

/**
 * 测试线程 Interrupt
 * 线程在 sleep 或 wait 状态时， 调用 interrupt 方法 会抛出中断异常
 */
public class Test01 {

    @Test
    public void test01() {
        CountDownLatch latch = new CountDownLatch(1);
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(" runing ...");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    System.err.println(" InterruptedException = " + e.getMessage());
                }
                System.out.println("finish ");
                latch.countDown();
            }
        });

        thread.start();

        try {
            // 测试 InterruptedException
            Thread.sleep(100);
            thread.interrupt();
            System.out.println("thread.isInterrupted = " + thread.isInterrupted());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
