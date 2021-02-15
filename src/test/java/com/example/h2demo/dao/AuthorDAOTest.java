package com.example.h2demo.dao;

import com.example.h2demo.models.Author;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

@DataJdbcTest
public class AuthorDAOTest {

    private JdbcTemplate jdbcTemplate;
    private AuthorJdbcDAO authorDAO;

    @Autowired
    public AuthorDAOTest(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.authorDAO = new AuthorJdbcDAO(jdbcTemplate);
    }

    @Test
    public void listAuthors_ShouldReturnAllAuthors()
    {
        List<Author> authors = authorDAO.list();
        assertEquals(3,authors.size());
    }
}
