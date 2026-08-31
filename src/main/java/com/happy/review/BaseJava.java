package com.happy.review;

import java.util.stream.Stream;

public class BaseJava {

    //生成一个Java多态的案例

    // 父类：动物
    static class Animal {
        // 父类方法
        public void makeSound() {
            System.out.println("动物发出声音");
        }
    }

    // 子类：狗（继承自动物）
    static class Dog extends Animal {
        // 重写父类方法
        @Override
        public void makeSound() {
            System.out.println("汪汪汪");
        }
    }

    // 子类：猫（继承自动物）
    static class Cat extends Animal {
        // 重写父类方法
        @Override
        public void makeSound() {
            System.out.println("喵喵喵");
        }
    }

    // 测试类
    public static class PolymorphismExample {
        public static void main(String[] args) {
            // 多态体现：父类引用指向子类对象
            Animal animal1 = new Dog();
            Animal animal2 = new Cat();

            // 调用同一方法，表现出不同行为
            animal1.makeSound();  // 输出：汪汪汪
            animal2.makeSound();  // 输出：喵喵喵
        }
    }


    public static void main(String[] args) {

        var ss = Stream.of("A", "BB", "CCC")
                .filter(s -> {
                    System.out.println("no print filter : " + s);
                    return s.length() > 1;
                }); //不会打印数据的
        ss.findFirst(); // 执行到这里的是否，可以打印数据

        Stream.of("A", "BB", "CCC")
                .filter(s -> {
                    System.out.println("print filter : " + s);
                    return s.length() > 1;
                }).count(); //不会打印数据的


        //Testing the polymorphism example
        PolymorphismExample.main(args);

        //八种类型
        Byte b = 127;
        Short s = 32767;
        Integer a = Integer.MAX_VALUE;
        Long l = Long.MAX_VALUE;
        Float f = Float.MAX_VALUE;
        Double d = Double.MAX_VALUE;
        Character ch = 'a';
        Boolean bool = true;

        int intValue = 10;
        long longValue = intValue; // 自动转换，安全

        long l1 = 10L;
        int ll = (int) l1; //强制转换

        class Animal {}
        class Dog extends Animal {}

        Dog dog = new Dog();
        Animal animal = dog; // 自动向上转型

        Animal animal1 = new Animal();
        Dog dog1 = (Dog) animal1; // 运行时抛出ClassCastException

        if (animal instanceof Dog) {
            Dog dog2 = (Dog) animal; // 只有确认animal是Dog的实例时才进行转型
        }


    }
}
