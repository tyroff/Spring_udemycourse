package by.vinokuroff.dao;

import by.vinokuroff.models.PersonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import by.vinokuroff.models.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class PersonDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public List<Person> index() {
//        свой мапер можно не создавать, если наименование полей в таблице БД совподают с сеторами в маппере
//        например(person.setName((resultSet.getString("name")));),
//        тогда можно использовать BeanPropertyRowMapper<>(Person.class):
//        return jdbcTemplate.query("SELECT * FROM Person", new BeanPropertyRowMapper<>(Person.class));
        return jdbcTemplate.query("SELECT * FROM person", new PersonMapper());
    }

    public Person show(int id) {
        return jdbcTemplate.query("SELECT * FROM person WHERE id=?", new Object[]{id}, new PersonMapper())
                .stream()
                .findAny()
                .orElse(null);
    }

    public void save(Person person) {
        jdbcTemplate.update("INSERT INTO person(name, year_birth) VALUES (?, ?)", person.getName(), person.getYearBirth());
    }

    public void update(int id, Person person) {
        jdbcTemplate.update("UPDATE person SET name=?, year_birth=? WHERE id=?", person.getName(), person.getYearBirth(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM person WHERE id=?", id);
    }

    public Optional<Person> getPersonByName(String name) {
        return jdbcTemplate.query("SELECT * FROM person WHERE name=?", new Object[]{name}, new PersonMapper()).stream().findAny();
    }
}
