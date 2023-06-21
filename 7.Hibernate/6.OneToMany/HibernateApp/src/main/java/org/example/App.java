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

            //Получить все товары person_id = 5
//            Person person = session.get(Person.class, 5);
//            System.out.println(person);
//            List<Item> items = person.getItems();
//            System.out.println(items);

            //Получить товар с id = 6 и его заказчика
//            Item item = session.get(Item.class, 6);
//            System.out.println(item);
//            Person person = item.getOwner();
//            System.out.println(person);

            //Создание нового товара и привязка к существующему заказчику person
//            Person person = session.get(Person.class, 2);
//            Item newItem = new Item("Item from Hibernate", person);
//            person.getItems().add(newItem); //это нужно для обновления кэша Hibernate, т.к. в БД изменения произойдут.
//            session.save(newItem);

            //Создаём нового заказчика и новый товар и привязываем их между собой.
//            Person newPerson = new Person("New person", 33);
//            Item newItem = new Item("New item from Hibernate", newPerson);
//            newPerson.setItems(new ArrayList<Item>(Collections.singletonList(newItem)));//это нужно для обновления кэша Hibernate
//            session.save(newPerson);
//            session.save(newItem);

            //Удалить все товары для заказчика
//            Person person = session.get(Person.class, 1);
//            List<Item> items = person.getItems();
//            for (Item item : items)
//                session.remove(item);
//            //изменения кэша Hibernate, очистим список товаров у заказчика
//            person.getItems().clear();

            //Удалить заказчика с заказами
//            Person person = session.get(Person.class, 5);
//            session.remove(person);
//            //Хорошая практика, изменение кэша Hibernate
//            person.getItems().forEach(i -> i.setOwner(null));

            //Заменить заказчика у товара
//            Person person = session.get(Person.class, 1);
//            Item item = session.get(Item.class, 7);
//            item.getOwner().getItems().remove(item);//изменение кэша Hibernate
//            item.setOwner(person);
//            person.getItems().add(item);

            session.getTransaction().commit();
        } finally {
            sessionFactory.close();
        }
    }
}
