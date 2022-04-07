package me.doyoung.studyeffectivejava.chapter5.item32;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class SuperTypeTokenApplication {

    public static void main(String[] args) {
        SpringApplication.run(SuperTypeTokenApplication.class, args);
    }

    @RestController
    public static class MyController {

        @GetMapping("/users")
        public List<User> users() {
            return Arrays.asList(new User("A"), new User("B"));
        }
    }

    public static class User {
        private String name;

        public User() {
        }

        public User(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return "User[" + name + "]";
        }
    }
}
