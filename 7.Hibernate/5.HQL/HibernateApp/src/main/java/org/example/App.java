package org.example;

import org.example.model.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

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

            //в запросе указывается java-сущность/объект
//            List<Person> list = session.createQuery("FROM Person").getResultList();
//            List<Person> list = session.createQuery("FROM Person where age > 30").getResultList();
            //LIKE 'N%' - % это любое количество любых символов
//            List<Person> list = session.createQuery("FROM Person WHERE name LIKE 'N%'").getResultList();

//            for (Person person : list)
//                System.out.println(person);

//            session.createQuery("update Person set name='New' where age<30").executeUpdate();
            session.createQuery("delete Person where age=33").executeUpdate();

            session.getTransaction().commit();
        } finally {
            sessionFactory.close();
        }
    }
}
