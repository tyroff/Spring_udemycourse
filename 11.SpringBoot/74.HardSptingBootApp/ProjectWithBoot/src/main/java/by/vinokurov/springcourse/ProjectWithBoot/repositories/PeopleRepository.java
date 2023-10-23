package by.vinokurov.springcourse.ProjectWithBoot.repositories;


import by.vinokurov.springcourse.ProjectWithBoot.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PeopleRepository extends JpaRepository<Person, Integer> {
    Optional<Person> findByName(String name);
}
