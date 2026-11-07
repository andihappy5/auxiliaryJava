package com.happy.review;


import java.lang.annotation.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class AnnotationReview {

    @MyAnnotation("类级别注解")
    public static class AnnotatedClass {

        @MyAnnotation("字段级别注解")
        private String field;

        @MyAnnotation("方法级别注解")
        public void annotatedMethod() {
        }
    }

    public static void main(String[] args) throws Exception {

//        List<? extends Number> list1 = new ArrayList<Integer>();
//        List<? super Integer> list2 = new ArrayList<Number>();
//
//        list1.add(10); // 1
//        list2.add(10); // 2
//        Number n = list2.get(0); // 3

        Class<?> clazz = AnnotationReview.AnnotatedClass.class;

        // 读取类注解
        MyAnnotation classAnno = clazz.getAnnotation(MyAnnotation.class);
        System.out.println("类注解: " + classAnno.value());

        // 读取字段注解
        Field field = clazz.getDeclaredField("field");
        MyAnnotation fieldAnno = field.getAnnotation(MyAnnotation.class);
        System.out.println("字段注解: " + fieldAnno.value());

        // 读取方法注解
        Method method = clazz.getDeclaredMethod("annotatedMethod");
        MyAnnotation methodAnno = method.getAnnotation(MyAnnotation.class);
        System.out.println("方法注解: " + methodAnno.value());
    }
}