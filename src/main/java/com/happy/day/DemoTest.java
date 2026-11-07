package com.happy.day;

public class DemoTest {
    public static void main(String[] args) {
        System.out.println("start");
        Runnable r = () -> {
            System.out.println("lambda execute");
        };
        System.out.println("end");
        // 不调用run()，里面代码永远不会执行！
        //  r.run();
    }
}
