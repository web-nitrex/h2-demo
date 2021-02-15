package com.example.h2demo.dao;

import com.example.h2demo.models.Author;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class AuthorJdbcDAO implements DAO<Author>{

    private static final Logger log = LoggerFactory.getLogger(AuthorJdbcDAO.class);

    private JdbcTemplate jdbcTemplate;

    RowMapper<Author> rowMapper = (rs,rowNum)->{
        Author author = new Author();
        author.setId(rs.getLong("id"));
        author.setFirstName(rs.getString("first_name"));
        author.setLastName(rs.getString("last_name"));
        return author;
    };

    public AuthorJdbcDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Author> list() {
        String sql = "SELECT * FROM AUTHORS";
        return jdbcTemplate.query(sql, rowMapper);
    }

    @Override
    public void create(Author author) {
        String sql = "INSERT INTO AUTHORS (first_name, last_name) VALUES(?, ?)";
        int insert = jdbcTemplate.update(sql,author.getFirstName(),author.getLastName());
        if(insert==1)
            log.info("New author created: {}", author);
    }

    @Override
    public Optional get(long id) {
        String sql = "SELECT * FROM AUTHORS WHERE id = ?";
        Author author = null;
        try {
            author = jdbcTemplate.queryForObject(sql, new Object[]{id},rowMapper);
        }catch (DataAccessException ex)
        {
            log.info("Author not found: {}",id);
        }
        return Optional.ofNullable(author);
    }

    @Override
    public void update(Author author, long id) {
        String sql = "UPDATE AUTHORS SET first_name=?, last_name=? WHERE id=?";
        int update = jdbcTemplate.update(sql,author.getFirstName(),author.getLastName(),id);
        if(update==1)
            log.info("Author updated: {}", author);
    }

    @Override
    public void delete(long id) {
        int delete = jdbcTemplate.update("DELETE FROM AUTHORS WHERE id=?",id);

        if(delete==1)
            log.info("Deleted author with id: {}", id);
    }
}
