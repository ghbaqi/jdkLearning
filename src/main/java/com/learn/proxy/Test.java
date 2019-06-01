package com.learn.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class Test {

    public static void main(String[] args) {

        Student student = new Student();
        Person proxy = (Person) Proxy.newProxyInstance(Student.class.getClassLoader(), Student.class.getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

                System.out.println("before");
                Object re = method.invoke(student, args);
                System.out.println("after");

                return re;
            }
        });

        proxy.say();
    }

}
