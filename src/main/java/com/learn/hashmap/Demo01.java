package com.learn.hashmap;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author : gaohui
 * @Date : 2019/3/29 11:57
 * @Description :
 */
public class Demo01 {

    public static void main(String[] args) {

        HashMap<String, Integer> hashMap = new HashMap<>();
        hashMap.put("aa", 1);
        hashMap.put("bb", 2);
        hashMap.put("cc", 3);
        System.out.println(hashMap.get("cc"));

        ArrayList<Object> list = new ArrayList<>();
        list.add("1");
        System.out.println(list.get(0));

    }

}
