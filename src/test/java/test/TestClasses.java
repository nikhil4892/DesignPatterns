package test;

import designpatterns.singletondesign.SingletonWithoutThreadSafety;

public class TestClasses {

    public static void main(String[] args) {
        SingletonWithoutThreadSafety singleton = SingletonWithoutThreadSafety.getInstance("first object");
        System.out.println(singleton.getAttribute());

        SingletonWithoutThreadSafety singleton2 = SingletonWithoutThreadSafety.getInstance("second object");
        System.out.println(singleton2.getAttribute());
    }
}
