package by.vinokurov.springcourse.SecurityApp.unit;

import by.vinokurov.springcourse.SecurityApp.models.Person;
import by.vinokurov.springcourse.SecurityApp.services.PersonDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class PersonValidator implements Validator {

    private final PersonDetailsService personDetailsService;

    @Autowired
    public PersonValidator(PersonDetailsService personDetailsService) {
        this.personDetailsService = personDetailsService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return Person.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Person person = (Person)o;
        // ниже не правильное решение определения наличия пользователя в БД
        // это сделано для ускорения изучения нового материала
        // и не тратить время на написанике нового функционала
        try {
            personDetailsService.loadUserByUsername(person.getUsername());
        } catch (UsernameNotFoundException ignored) {
            return; //т.е. пользователь не найден.
        }

        errors.rejectValue("username", "","Пользователь с таким именем уже занят. Выберите другое имя.");
    }
}
