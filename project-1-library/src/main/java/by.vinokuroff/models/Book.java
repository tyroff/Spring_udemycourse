package by.vinokuroff.models;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class Book {
    private int id;
    @NotEmpty(message = "The book name not be empty")
    @Size(min = 3, max = 200, message = "The size name of book should be between 3 and 200 characters")
    private String name;
    @NotEmpty(message = "The author name not be empty")
    @Size(min = 3, max = 200, message = "The size author should be between 3 and 200 characters")
    private String author;
    @NotEmpty(message = "The year of book not be empty")
    private int year;

    public Book(int id, String name, String author, int year) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.year = year;
    }

    public Book() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
