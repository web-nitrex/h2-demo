package com.example.h2demo.dao;

import com.example.h2demo.models.Author;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class AuthorJdbcDAO implements DAO<Author>{

    private static final Logger log = LoggerFactory.getLogger(AuthorJdbcDAO.class);

    private final NamedParameterJdbcOperations jdbc;

    RowMapper<Author> rowMapper = (rs,rowNum)->{
        Author author = new Author();
        author.setId(rs.getLong("id"));
        author.setFirstName(rs.getString("first_name"));
        author.setLastName(rs.getString("last_name"));
        return author;
    };

    public AuthorJdbcDAO(NamedParameterJdbcOperations jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public List<Author> list() {
        String sql = "SELECT * FROM AUTHORS";
        return jdbc.query(sql, rowMapper);
    }

    @Override
    public void create(Author author) {
        String sql = "INSERT INTO AUTHORS (first_name, last_name) VALUES(:firstName, :lastName)";

        Map<String,Object> tempMap = new HashMap<>();
        tempMap.put("firstName", author.getFirstName());
        tempMap.put("lastName",author.getLastName());

        int insert = jdbc.update(sql, tempMap);

        if(insert==1)
            log.info("New author created: {}", author);
    }

    @Override
    public Optional get(long id) {
        String sql = "SELECT * FROM AUTHORS WHERE id = :id";
        Author author = null;

        Map<String,Object> tempMap = new HashMap<>();
        tempMap.put("id",id);

        try {
            author = jdbc.queryForObject(sql, tempMap, rowMapper);
        }catch (DataAccessException ex)
        {
            log.info("Author not found: {}",id);
        }
        return Optional.ofNullable(author);
    }

    @Override
    public void update(Author author, long id) {
        String sql = "UPDATE AUTHORS SET first_name = :firstName, last_name = :lastName WHERE id = :id";

        Map<String,Object> tempMap = new HashMap<>();
        tempMap.put("id",id);
        tempMap.put("firstName", author.getFirstName());
        tempMap.put("lastName",author.getLastName());

        int update = jdbc.update(sql,tempMap);
        if(update==1)
            log.info("Author updated: {}", author);
    }

    @Override
    public void delete(long id) {
        Map<String,Object> tempMap = new HashMap<>();
        tempMap.put("id",id);
        int delete = jdbc.update("DELETE FROM AUTHORS WHERE id= :id",tempMap);

        if(delete==1)
            log.info("Deleted author with id: {}", id);
    }
}
