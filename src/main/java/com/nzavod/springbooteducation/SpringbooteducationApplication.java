package com.nzavod.springbooteducation;

import javax.servlet.annotation.WebServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
public class SpringbooteducationApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringbooteducationApplication.class, args);
    }
}
