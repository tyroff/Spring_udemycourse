package org.example;

import org.example.model.Item;
import org.example.model.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class App
{
    public static void main( String[] args )
    {
        // Configuration читает файл hibernate.properties (лишь с таким названием по умолчанию)
        Configuration configuration = new Configuration()
                .addAnnotatedClass(Person.class)
                .addAnnotatedClass(Item.class);

        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();

        try {
            session.beginTransaction();

            Person person = new Person("Name_3", 33);
            person.addItem(new Item("Item_1"));
            person.addItem(new Item("Item_2"));
            person.addItem(new Item("Item_3"));
//            Item item = new Item("Item_Cascating2");
//            person.setItems(new ArrayList<>(Collections.singletonList(item)));

            //persist - сохранение объекта в БД с каскадирование cascade = CascadeType.PERSIST
            //session.persist(person);
            session.save(person);

            session.getTransaction().commit();
        } finally {
            sessionFactory.close();
        }
    }
}
