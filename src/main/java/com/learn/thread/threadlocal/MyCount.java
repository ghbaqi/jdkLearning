package com.learn.thread.threadlocal;

/**
 * @author : gaohui
 * @Date : 2019/3/25 09:25
 * @Description :
 * <p>
 * MyCount
 */
public class MyCount {

    private static final ThreadLocal<Integer> threadLocal = new ThreadLocal<Integer>() {
        //        @Override
        //        protected Integer initialValue() {
        //            return 10000;
        //        }
    };


    private static final ThreadLocal<String> STRING_THREAD_LOCAL = new ThreadLocal<String>() {
        @Override
        protected String initialValue() {
            return "aaaa";
        }
    };

    public static ThreadLocal<Integer> getThreadLocal() {
        return threadLocal;
    }

    public static ThreadLocal<String> getStringThreadLocal() {
        return STRING_THREAD_LOCAL;
    }
}
