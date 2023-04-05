package org.example;

import org.springframework.stereotype.Component;

@Component("musicBean")
public class AnnotationMusic implements Music{
    @Override
    public String getSong() {
        return "Pop is pop from annotation";
    }
}
