package com.learn.list;

import java.util.ArrayList;

public class Demo01 {

    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        list.add("a");
        String s = list.get(0);
        System.out.println(s);
    }
}
