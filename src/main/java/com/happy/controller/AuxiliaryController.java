package com.happy.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class AuxiliaryController {
    @GetMapping("/auxiliary/jave")
    public Map<String, String> getAuxiliary() {
        return Map.of("aim", "happy");
    }
}
