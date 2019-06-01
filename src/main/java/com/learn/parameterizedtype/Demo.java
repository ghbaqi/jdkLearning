package com.learn.parameterizedtype;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class Demo<T> {

    private Class<T> clazz;

    public T getDemo() throws IllegalAccessException, InstantiationException {
        return clazz.newInstance();
    }

    public T getDemo2() throws IllegalAccessException, InstantiationException {

        Type superClass = getClass().getGenericSuperclass();
        if (superClass instanceof ParameterizedType) {
            Type type = ((ParameterizedType) superClass).getActualTypeArguments()[0];
            this.clazz = (Class<T>) type;
        } else {
            System.out.println("不相等");
        }


        return clazz.newInstance();
    }

    public static void main(String[] args) throws InstantiationException, IllegalAccessException {

        String s = new String();
        Type type = s.getClass().getGenericSuperclass();
        System.out.println(type.getTypeName());

    }
}
