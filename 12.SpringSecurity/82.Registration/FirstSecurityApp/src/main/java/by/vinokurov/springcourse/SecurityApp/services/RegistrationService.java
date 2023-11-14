package by.vinokurov.springcourse.SecurityApp.services;

import by.vinokurov.springcourse.SecurityApp.models.Person;
import by.vinokurov.springcourse.SecurityApp.repositories.PeopleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RegistrationService {

    private final PeopleRepository peopleRepository;

    public RegistrationService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    @Transactional
    public void registration(Person person) {
        peopleRepository.save(person);
    }
}
