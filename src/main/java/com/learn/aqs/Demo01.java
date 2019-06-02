package com.learn.aqs;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author gaohui
 * @date 2019/5/29 20:11
 * @description:
 */
public class Demo01 {

    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();
        lock.lock();
    }
}
