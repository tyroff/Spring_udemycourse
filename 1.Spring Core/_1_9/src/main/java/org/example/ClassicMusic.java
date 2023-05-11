package org.example;

public class ClassicMusic implements Music{
    public void doInit() {
        System.out.println("Init method is doing");
    }

    public void doDestroy() {
        System.out.println("Destroy method is doing");
    }
    @Override
    public String getSong() {
        return "Classic is classic";
    }
}
