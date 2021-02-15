package com.example.h2demo.dao;

import com.example.h2demo.models.Author;
import com.example.h2demo.models.Genre;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class GenreJdbcDAO implements DAO<Genre>{

    private static final Logger log = LoggerFactory.getLogger(GenreJdbcDAO.class);

    private JdbcTemplate jdbcTemplate;

    RowMapper<Genre> rowMapper = (rs, rowNum)->{
        Genre genre = new Genre();
        genre.setId(rs.getLong("id"));
        genre.setNameGenre(rs.getString("genre"));
        return genre;
    };

    public GenreJdbcDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Genre> list() {
        String sql = "SELECT * FROM GENRE";
        return jdbcTemplate.query(sql, rowMapper);
    }

    @Override
    public void create(Genre genre) {
        String sql = "INSERT INTO GENRE (genre) VALUES(?)";
        int insert = jdbcTemplate.update(sql,genre.getNameGenre());
        if(insert==1)
            log.info("New genre created: {}", genre);
    }

    @Override
    public Optional<Genre> get(long id) {
        String sql = "SELECT * FROM GENRE WHERE id = ?";
        Genre genre = null;
        try {
            genre = jdbcTemplate.queryForObject(sql, new Object[]{id},rowMapper);
        }catch (DataAccessException ex)
        {
            log.info("Genre not found: {}",id);
        }
        return Optional.ofNullable(genre);
    }

    @Override
    public void update(Genre genre, long id) {
        String sql = "UPDATE GENRE SET genre=? WHERE id=?";
        int update = jdbcTemplate.update(sql,genre.getNameGenre(), id);
        if(update==1)
            log.info("Genre updated: {}", genre);
    }

    @Override
    public void delete(long id) {
        int delete = jdbcTemplate.update("DELETE FROM GENRE WHERE id=?",id);

        if(delete==1)
            log.info("Deleted genre with id: {}", id);
    }
}
