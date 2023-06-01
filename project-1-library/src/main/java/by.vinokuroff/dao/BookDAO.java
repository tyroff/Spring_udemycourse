package by.vinokuroff.dao;

import by.vinokuroff.models.Book;
import by.vinokuroff.models.BookMapper;
import by.vinokuroff.models.Person;
import by.vinokuroff.models.PersonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class BookDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> index() {
//        свой мапер можно не создавать, если наименование полей в таблице БД совподают с сеторами в маппере
//        например(person.setName((resultSet.getString("name")));),
//        тогда можно использовать BeanPropertyRowMapper<>(Person.class):
//        return jdbcTemplate.query("SELECT * FROM Person", new BeanPropertyRowMapper<>(Person.class));
        return jdbcTemplate.query("SELECT * FROM book", new BookMapper());
    }

    public Book show(int id) {
        return jdbcTemplate.query("SELECT * FROM book WHERE id=?", new Object[]{id}, new BookMapper())
                .stream()
                .findAny()
                .orElse(null);
    }

    public void save(Book book) {
        jdbcTemplate.update("INSERT INTO book (name, author, year_publication) VALUES (?, ?, ?)", book.getName(), book.getAuthor(), book.getYear());
    }

    public void update(int id, Book book) {
        jdbcTemplate.update("UPDATE book SET name=?, author=?, year_publication=? WHERE id=?", book.getName(), book.getAuthor(), book.getYear(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM book WHERE id=?", id);
    }

    public Optional<Book> getBookByName(String name) {
        return jdbcTemplate.query("SELECT * FROM book WHERE name=?", new Object[]{name}, new BookMapper()).stream().findAny();
    }
}
