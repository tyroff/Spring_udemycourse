package by.vinokuroff.dao;

import by.vinokuroff.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

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
        return jdbcTemplate.query("SELECT * FROM Person", new PersonMapper());
    }

    public Person show(int id) {
        return jdbcTemplate.query("SELECT * FROM Person WHERE id=?", new Object[]{id}, new PersonMapper())
                .stream()
                .findAny()
                .orElse(null);
    }

    public void save(Person person) {
        jdbcTemplate.update("INSERT INTO Person(name, year_birth) VALUES (?, ?)", person.getFullName(), person.getYearOfBirth());
    }

    public void update(int id, Person person) {
        jdbcTemplate.update("UPDATE Person SET name=?, year_birth=? WHERE id=?", person.getFullName(), person.getYearOfBirth(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Person WHERE id=?", id);
    }
}
