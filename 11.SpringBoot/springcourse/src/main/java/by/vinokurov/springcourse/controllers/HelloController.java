package by.vinokurov.springcourse.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {
    @Value("${hello}")
     public String helloWorld;

    @GetMapping("/hello")
    public String hello() {
        System.out.println("Hello console:" + helloWorld);
        return "hello";
    }
}
