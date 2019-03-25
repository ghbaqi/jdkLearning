package com.learn.thread;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author : gaohui
 * @Date : 2019/2/28 14:38
 * @Description :
 */
public class bit {

    @Test
    public void test01() {

        int COUNT_BITS = Integer.SIZE - 3;
        System.out.println(COUNT_BITS);
        int RUNNING = -1 << COUNT_BITS;
        AtomicInteger ctl = new AtomicInteger(ctlOf(RUNNING, 0));

        System.out.println(RUNNING);
        System.out.println(ctl.get());

    }

    private static int ctlOf(int rs, int wc) {
        return rs | wc;
    }

    @Test
    public void test02() {
        int COUNT_BITS = Integer.SIZE - 3;
        int RUNNING = -1 << COUNT_BITS;
        int SHUTDOWN = 0 << COUNT_BITS;
        int STOP = 1 << COUNT_BITS;
        int TIDYING = 2 << COUNT_BITS;
        int TERMINATED = 3 << COUNT_BITS;

        // -536870912
        System.out.println(RUNNING);

        // 0
        System.out.println(SHUTDOWN);

        // 536870912
        System.out.println(STOP);

        // 1073741824
        System.out.println(TIDYING);

        // 1610612736
        System.out.println(TERMINATED);
    }


    @Test
    public void test03() {
        System.out.println((1 << 29) - 1);
    }

}
