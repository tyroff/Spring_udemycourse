package by.vinokurov.dao;

import by.vinokurov.models.Person;
import org.hibernate.Session;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Component
public class PersonDAO {

    private final EntityManager entityManager;

    public PersonDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional(readOnly = true)
    public void testNPlus1(){
        Session session = entityManager.unwrap(Session.class);

//        //1 запрос
//        List<Person> people = session.createQuery("select p from Person p", Person.class).getResultList();
//
//        //N запросов в БД, для каждого Peron-a
//        for (Person person : people) {
//            System.out.println("Person" + person.getName() + "has: " + person.getItems());
//        }

        //Solution
        //SQL: A LEFT JOIN B
        List<Person> people = session.createQuery("select p from Person p LEFT JOIN FETCH p.items").getResultList();
        for (Person person : people) {
            System.out.println("Person name: " + person.getName() + " has: " + person.getItems());
        }
    }
}
