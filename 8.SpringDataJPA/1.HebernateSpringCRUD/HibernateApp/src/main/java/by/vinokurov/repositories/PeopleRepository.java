package by.vinokurov.repositories;

import by.vinokurov.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PeopleRepository extends JpaRepository<Person, Integer> {
    // в джинерике JpaRepository<Person, Integer> указывается сущность и тип id
}
