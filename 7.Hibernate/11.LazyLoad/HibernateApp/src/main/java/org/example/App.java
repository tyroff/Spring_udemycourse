package org.example;

import org.example.model.Item;
import org.example.model.Person;
import org.hibernate.Hibernate;
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

            //OneToMany по умолчанию (если не указывать) загрузка связанных сущностей fetch = FetchType.Lazy
            //ManyToOne по умолчанию (если не указывать) загрузка связанной сущности fetch = FetchType.EAGER

            Person person = session.get(Person.class, 1);
            System.out.println("Получили покупателя");
            System.out.println(person);

            //Для подгрузки связанных сущностей, можно использовать класс Hibernate
//            Hibernate.initialize(person.getItems());

            session.getTransaction().commit();
            //Внесессии товары для данного покупателя можно получить,
            // т.к. они был уже загруженны вместе с покупателем,
            // т.к. в Person.getItems указан fetch = FetchType.EAGER - но это плохая практика

//            System.out.println(person.getItems());

            session = sessionFactory.getCurrentSession();
            session.beginTransaction();

            System.out.println("Вторая сессия");
            person = (Person) session.merge(person);
            Hibernate.initialize(person.getItems());
            session.getTransaction().commit();

            System.out.println(person.getItems());


        } finally {
            sessionFactory.close();
        }
    }
}
