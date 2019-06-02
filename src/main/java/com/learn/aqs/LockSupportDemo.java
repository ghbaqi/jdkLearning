package com.learn.aqs;

import java.util.concurrent.locks.LockSupport;

/**
 * @author gaohui
 * @date 2019/6/2 13:58
 * @description:
 */
public class LockSupportDemo {


    /**
     * 111
     * a
     * b
     * 2222
     */
    public static void main(String[] args) throws InterruptedException {

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("111");
                LockSupport.park();
                System.out.println("2222");
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("a");
                    Thread.sleep(2000);
                    System.out.println("b");
                    LockSupport.unpark(t1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });

        t1.start();
        t2.start();

    }

}
