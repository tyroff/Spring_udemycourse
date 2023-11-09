package by.vinokurov.springcourse.ProjectWithBoot.services;

import by.vinokurov.springcourse.ProjectWithBoot.models.Book;
import by.vinokurov.springcourse.ProjectWithBoot.models.Person;
import by.vinokurov.springcourse.ProjectWithBoot.repositories.PeopleRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PeopleService {

    private final PeopleRepository peopleRepository;

    @Autowired
    public PeopleService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    public List<Person> findAll() {
        return peopleRepository.findAll();
    }

    public Person findOne(int id) {
        Optional<Person> foundPerson = peopleRepository.findById(id);
        return foundPerson.orElse(null);
    }

    @Transactional
    public void save(Person person) {
        peopleRepository.save(person);
    }

    @Transactional
    public void update(int id, Person updatePerson) {
        updatePerson.setId(id);
        peopleRepository.save(updatePerson);
    }

    @Transactional
    public void delete(int id) {
        peopleRepository.deleteById(id);
    }

    public Optional<Person> getPersonByFullName(String fullName) {
        return peopleRepository.findByName(fullName);
    }

    public List<Book> getBooksByPersonId(int id) {
        Optional<Person> person = peopleRepository.findById(id);

        if(person.isPresent()) {
            Hibernate.initialize(person.get().getBooks());
            /*
            * Внизу итерируемсчя по книгам, поэтому они точно будут загружены,
            * но на всякий случай не мешает всегда вызывать Hibernate.initialize()
            * (на всякий случай, например, если код в дальнейшем поменяется и
            * итерация по книгам удалиться)
            */

            //Проверка просроченности книг
            person.get().getBooks().forEach(book ->{
                long diffInMillies = Math.abs(book.getTakenAt().getTime() - new Date().getTime());
                // 864000000 милисекунд = 10 суток
                if (diffInMillies > 864000000) {
                    book.setExpired(true); //книга просрочена
                }
            });

            return person.get().getBooks();

        } else {
            return Collections.emptyList();
        }
    }
}