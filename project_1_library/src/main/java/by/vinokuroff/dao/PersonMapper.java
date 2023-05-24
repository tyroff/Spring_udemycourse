package by.vinokuroff.dao;

import by.vinokuroff.models.Person;
import org.springframework.jdbc.core.RowMapper;


import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonMapper implements RowMapper<Person> {
    @Override
    public Person mapRow(ResultSet resultSet, int i) throws SQLException {
        Person person = new Person();
        person.setId((resultSet.getInt("id")));
        person.setFullName((resultSet.getString("name")));
        person.setYearOfBirth((resultSet.getInt("year_birth")));
        return person;
    }
}
