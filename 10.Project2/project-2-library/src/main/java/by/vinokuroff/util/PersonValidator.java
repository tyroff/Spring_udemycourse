package by.vinokuroff.util;

import by.vinokuroff.dao.PersonDAO;
import by.vinokuroff.models.Person;
import by.vinokuroff.services.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class PersonValidator implements Validator {
    private final PeopleService peopleService;

    @Autowired
    public PersonValidator(PeopleService peopleService) {
        this.peopleService = peopleService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return Person.class.equals(aClass) ;
    }

    @Override
    public void validate(Object o, Errors errors) {
        Person person = (Person) o;

        if(peopleService.getPersonByFullName(person.getName()).isPresent()) {
            errors.rejectValue("name", "", "Читатель с таким ФИО загеристрированн!");
        }
    }
}
