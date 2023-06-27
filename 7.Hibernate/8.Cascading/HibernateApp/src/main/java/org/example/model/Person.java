package org.example.model;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "person")
public class Person {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY) //колонка генериться в БД автоматом БД  и поэтому Hibernet не нужно её трогать.
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private int age;

    //cascade = CascadeType.PERSIST - при сохранении Person будут сохраняться все связанные с ним Item
    //@OneToMany(mappedBy = "owner", cascade = CascadeType.PERSIST)
    //@Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE) - метод SAVE в классе APP будет каскадироваться
    @OneToMany(mappedBy = "owner", cascade = CascadeType.PERSIST)
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    private List<Item> items;

    public Person() {}

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public void addItem(Item item) {
        if (this.items == null) {
            this.items = new ArrayList<>();
        }
        this.items.add(item);
        item.setOwner(this);
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
