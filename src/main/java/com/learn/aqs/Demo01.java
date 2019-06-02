package com.learn.aqs;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author gaohui
 * @date 2019/5/29 20:11
 * @description:
 */
public class Demo01 {

    public static void main(String[] args) throws InterruptedException {
        ReentrantLock lock = new ReentrantLock();
        System.out.println("1111");
        lock.lock();
        System.out.println("2222");
        Thread.sleep(3000);
        lock.unlock();

        System.out.println("333");
    }
}
