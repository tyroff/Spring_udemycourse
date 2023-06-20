package org.example;

import org.example.model.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class App
{
    public static void main( String[] args )
    {
        // Configuration читает файл hibernate.properties (лишь с таким названием по умолчанию)
        Configuration configuration = new Configuration().addAnnotatedClass(Person.class);

        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();

        try {
            session.beginTransaction();
            // Обновить поле в БД
//            Person person = session.get(Person.class, 2);
//            person.setName("New name");

            // Удалить объект в БД
//            Person person = session.get(Person.class, 3);
//            session.delete(person);

            //Получить ID нового Person из БД
            Person person = new Person("Need ID", 666);
            session.save(person);

            session.getTransaction().commit();
            //После коммита сесии можно получить ID person
            System.out.println("id = " + person.getId());
        } finally {
            sessionFactory.close();
        }
    }
}
