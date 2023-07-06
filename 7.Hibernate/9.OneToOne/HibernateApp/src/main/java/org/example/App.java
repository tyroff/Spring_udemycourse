package org.example;

import org.example.model.Item;
import org.example.model.Passport;
import org.example.model.Person;
import org.hibernate.PessimisticLockException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class App
{
    public static void main( String[] args )
    {
        // Configuration читает файл hibernate.properties (лишь с таким названием по умолчанию)
        Configuration configuration = new Configuration()
                .addAnnotatedClass(Person.class)
                .addAnnotatedClass(Passport.class);

        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();

        try {
            session.beginTransaction();

//            Person person = new Person("TestOneToOne_2", 77);
//          //Passport passport = new Passport(person, 123456789);
//            Passport passport = new Passport(123456789);
//            person.setPassport(passport); //это нужно для кэша Hibernate, если её не будет связь будет только в БД
//            session.save(person);

//            //Читаем чел с БД
//            Person person = session.get(Person.class, 7);
//            System.out.println(person.getPassport().getPassportNumber());

//            //Читаем паспорт с БД
//            Passport passport = session.get(Passport.class, 7); // 7 - id привязанного чела
//            System.out.println(passport.getPerson().getName());

//            //Обновление номера паспорта для чела с БД
//            Person person = session.get(Person.class, 7);
//            person.getPassport().setPassportNumber(777);

            session.getTransaction().commit();
        } finally {
            sessionFactory.close();
        }
    }
}
