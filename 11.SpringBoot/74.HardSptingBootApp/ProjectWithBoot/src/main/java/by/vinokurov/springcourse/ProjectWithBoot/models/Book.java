package by.vinokurov.springcourse.ProjectWithBoot.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.Date;

@Entity
@Table(name="Book")
public class Book {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "title")
    @NotEmpty(message = "The book name not be empty")
    @Size(min = 3, max = 200, message = "The size name of book should be between 3 and 200 characters")
    private String title;

    @Column(name = "author")
    @NotEmpty(message = "The author name not be empty")
    @Size(min = 3, max = 200, message = "The size author should be between 3 and 200 characters")
    private String author;

    @Column(name = "year_publication")
//    @NotEmpty(message = "The year of book not be empty")
    @Min(value = 1500, message = "Год должен быть больше, чем 1500")
    private int yearPublication;

    @ManyToOne
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private Person owner;

    @Column(name = "take_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date takenAt;

    @Transient //Hibernate не будет замечать данное поле. По умолчанию FALSE.
    private boolean expired;

    public Book(int id, String title, String author, int yearPublication) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.yearPublication = yearPublication;
    }

    public Book() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYearPublication() {
        return yearPublication;
    }

    public void setYearPublication(int year) {
        this.yearPublication = year;
    }

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }

    public Date getTakenAt() {
        return takenAt;
    }

    public void setTakenAt(Date takenAt) {
        this.takenAt = takenAt;
    }

    public boolean isExpired() {
        return expired;
    }

    public void setExpired(boolean expired) {
        this.expired = expired;
    }
}
