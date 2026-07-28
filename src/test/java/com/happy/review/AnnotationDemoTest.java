package com.happy.review;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AnnotationDemoTest {

    @Test
    public void compileTimeAnnotationShouldBeSourceRetention() {
        assertTrue(CompileTimeAnnotationDemo.isSourceRetention());
        assertTrue(CompileTimeAnnotationDemo.describe().contains("Compile-time"));
    }

    @Test
    public void runtimeAnnotationShouldInjectValuesByReflection() throws Exception {
        RuntimeAnnotationDemo.ServiceConfig config = RuntimeAnnotationDemo.createConfiguredService();
        assertEquals("jdbc:mysql://localhost:3306/test", config.getUrl());
        assertEquals("root", config.getUser());
        assertTrue(RuntimeAnnotationDemo.describe().contains("Runtime"));
    }
}
