package com.happy.review;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class StreamDemo {


    static class Employee {
        private String name;
        private String part;
        private Integer age;
        private Integer salary;

        public Employee(String name, String part, Integer age, Integer salary) {
            this.name = name;
            this.part = part;
            this.age = age;
            this.salary = salary;
        }

        @Override
        public String toString() {
            return "Employee [name=" + name + ", part=" + part + ", age=" + age + ", salary=" + salary + "]";
        }

        public String getAprt() {
            return part;
        }

        public String getName() {
            return name;
        }
    }

    static List<Employee> employees = Arrays.asList(
            new Employee("Tom", "IT", 30, 20000),
            new Employee("Alice", "IT", 28, 18000),
            new Employee("Bob", "HR", 35, 15000),
            new Employee("Jerry", "HR", 32, 16000)
    );

    public static void main(String[] args) {
        List<Employee> employees2 = employees.stream().filter(
                e -> e.age >= 30
        ).collect(Collectors.toList());
//        System.out.println(Arrays.toString(employees2.toArray()));

        List<String> names = employees.stream()
                .map(e -> e.name)
                .collect(Collectors.toList());
//        System.out.println(names);

        List<String> names2 = employees.stream()
                .filter(e -> "IT".equals(e.part))
                .map(e -> e.name)
                .collect(Collectors.toList());

        Map<String,List<Employee>> map = employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::getAprt
                ));

        Map<String,Long> result = employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::getAprt,
                        Collectors.counting()
                ));

        Map<String,String> map2 =
                employees.stream().collect(
                        Collectors.toMap(
                                Employee::getAprt,
                                Employee::getName,
                                (o,n)-> o
                        )
                );

        Map<String, Employee> employeeMap =
                employees.stream()
                        .collect(Collectors.toMap(
                                Employee::getName,
                                Function.identity()
                        ));
        System.out.println(employeeMap);
    }


}
