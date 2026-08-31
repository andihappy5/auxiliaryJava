package com.happy.review;

public class Singleton {

//    单例实现	懒加载	线程安全	抵抗反射破坏	抵抗序列化破坏
//    静态内部类 Singleton	✅	✅	❌ 可被反射破坏	❌
//    饿汉 Singleton2	❌	✅	❌ 可被反射破坏	❌
//    DCL Singleton3	✅	✅	❌ 可被反射破坏	❌
//    枚举 Singleton4	❌	✅	✅ JDK 底层禁止反射	✅

    // JVM在加载这个类的时候就会对它进行初始化, 这里包含对静态变量的初始化;
    //Java的语义包证了在引用这个字段之前并不会初始化它, 并且访问这个字段的任何线程都将看到初始化这个字段所产生的所有写入操作.
    //If the singleton you are creating is static
    //  (i.e., there will only be one Helper created),
    // as opposed to a property of another object
    //  (e.g., there will be one Helper for each Foo object, there is a simple and elegant solution.
    //Just define the singleton as a static field in a separate class.
    // The semantics of Java guarantee that the field will
    //  not be initialized until the field is referenced,
    // and that any thread which accesses the field
    //  will see all of the writes resulting from initializing that field.
    /**
     * 一个私有的静态内部类，用于初始化一个静态final实例
     */
    private static class StaticSingletonHolder {
        private static final Singleton instance = new Singleton();
    }
    private Singleton(){}

    public static Singleton getInstance(){
        return StaticSingletonHolder.instance;
    }
}

final class Singleton2 {
    private static final Singleton2 instance = new Singleton2();
    private Singleton2() {
    }
    public static Singleton2 getInstance() {
        return instance;
    }
}

final class Singleton3 {
    // volatile 的意义主要在于它可以防止避免拿到没完成初始化的对象，从而保证了线程安全
    // 避免指令的重排，因为instance = new Singleton()，非原子行的操作，避免拿到未初始化的Singleton 使用而报错
    private static volatile Singleton3 instance;
    private Singleton3(){}
    public static Singleton3 getInstance(){
        if (instance == null){
            synchronized (Singleton3.class){
                if (instance == null){
                    instance = new Singleton3();
                }
            }
        }
        return instance;
    }
}

final class Singleton4 {
    public static void main(String[] args) {
        S4 single = S4.SINGLE;
        single.print();
    }

    enum S4 {
        SINGLE;
        private S4() {
        }
        public void print() {
            System.out.println("hello world");
        }
    }
}
