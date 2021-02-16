package com.example.h2demo.dao;

import com.example.h2demo.models.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class BookJdbcDAO implements DAO<Book> {

    private static final Logger log = LoggerFactory.getLogger(BookJdbcDAO.class);

    private JdbcTemplate jdbcTemplate;

    RowMapper<Book> rowMapper = (rs, rowNum)->{
        Book book = new Book();
        book.setId(rs.getLong("id"));
        book.setName(rs.getString("name"));
        book.setNumberOfPages(rs.getInt("number_of_pages"));
        book.setAuthorId(rs.getLong("author_id"));
        book.setGenreId(rs.getLong("genre_id"));
        return book;
    };

    public BookJdbcDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Book> list() {
        String sql = "SELECT * FROM BOOKS";
        return jdbcTemplate.query(sql, rowMapper);
    }

    @Override
    public void create(Book book) {
        String sql = "INSERT INTO BOOKS (name, number_of_pages, author_id, genre_id) VALUES(?, ?, ?, ?)";
        int insert = jdbcTemplate.update(sql, book.getName(), book.getNumberOfPages(), book.getAuthorId(), book.getGenreId());
        if(insert==1)
            log.info("New book created: {}", book);
    }

    @Override
    public Optional<Book> get(long id) {
        String sql = "SELECT * FROM BOOKS WHERE id = ?";
        Book book = null;
        try {
            book = jdbcTemplate.queryForObject(sql, new Object[]{id},rowMapper);
        }catch (DataAccessException ex)
        {
            log.info("Book not found: {}",id);
        }
        return Optional.ofNullable(book);
    }

    @Override
    public void update(Book book, long id) {
        String sql = "UPDATE BOOKS SET name=?, number_of_pages=?, author_id=?, genre_id=? WHERE id=?";
        int update = jdbcTemplate.update(sql,book.getName(), book.getNumberOfPages(), book.getAuthorId(), book.getGenreId(),id);
        if(update==1)
            log.info("Book updated: {}", book);
    }

    @Override
    public void delete(long id) {
        int delete = jdbcTemplate.update("DELETE FROM BOOKS WHERE id=?",id);

        if(delete==1)
            log.info("Deleted book with id: {}", id);
    }
}
