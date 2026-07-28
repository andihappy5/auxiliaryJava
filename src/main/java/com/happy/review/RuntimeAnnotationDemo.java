package com.happy.review;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;

public class RuntimeAnnotationDemo {

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.FIELD)
    public @interface Inject {
        String value();
    }

    public static class ServiceConfig {
        @Inject("database.url")
        private String url;

        @Inject("database.user")
        private String user;

        public String getUrl() {
            return url;
        }

        public String getUser() {
            return user;
        }
    }

    public static ServiceConfig createConfiguredService() throws IllegalAccessException {
        ServiceConfig config = new ServiceConfig();
        for (Field field : ServiceConfig.class.getDeclaredFields()) {
            Inject inject = field.getAnnotation(Inject.class);
            if (inject != null) {
                field.setAccessible(true);
                if ("database.url".equals(inject.value())) {
                    field.set(config, "jdbc:mysql://localhost:3306/test");
                } else if ("database.user".equals(inject.value())) {
                    field.set(config, "root");
                }
            }
        }
        return config;
    }

    public static String describe() {
        return "Runtime annotation example: annotation metadata is read at runtime via reflection and used to inject values.";
    }
}
