package com.happy.review;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public class CompileTimeAnnotationDemo {

    @Retention(RetentionPolicy.SOURCE)
    @Target(ElementType.TYPE)
    public @interface GenerateBuilder {
    }

    @GenerateBuilder
    public static class User {
        private final String name;
        private final int age;

        public User(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }
    }

    public static boolean isSourceRetention() {
        return GenerateBuilder.class.getAnnotation(Retention.class) != null
                && GenerateBuilder.class.getAnnotation(Retention.class).value() == RetentionPolicy.SOURCE;
    }

    public static String describe() {
        return "Compile-time annotation example: annotation is retained only in source code and can be used by annotation processors to generate code.";
    }
}
