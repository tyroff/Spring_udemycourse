package org.example;


import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
@Scope("singleton")
public class ClassicalMusic implements Music {

    @PostConstruct
    public void doInit() {
        System.out.println("Doing init method");
    }

    @PreDestroy
    public void doDestroy() {
        System.out.println("Doing destroy method");
    }
    @Override
    public String getSong() {
        return "Classic music is doing the song";
    }
}
