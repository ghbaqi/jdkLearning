package com.learn.thread.threadlocal;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @author : gaohui
 * @Date : 2019/3/25 09:25
 * @Description :
 * <p>
 * 线程间隔离 , eg: 数据库连接在多线程环境下 , 方法间传递时 ,
 * 最终同一个线程可以使用的 connection 是相同的
 * <p>
 * ==> 变量在方法间传递 , 在线程之间隔离
 */
public class Demo01 {

    private static final Executor threadpool = Executors.newCachedThreadPool();

    public static void main(String[] args) {

        MyCount myCount = new MyCount();


        for (int j = 0; j < 1; j++) {

            int finalJ = j;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    ThreadLocal<Integer> threadLocal = MyCount.getThreadLocal();
                    threadLocal.set(finalJ);
                    Integer i = threadLocal.get();
                    System.out.println(i);


                    ThreadLocal<String> local = MyCount.getStringThreadLocal();
                    System.out.println(local.get());

                }
            }).start();
        }

    }
}
