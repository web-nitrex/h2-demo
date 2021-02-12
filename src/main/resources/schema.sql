
DROP TABLE IF EXISTS AUTHORS;
DROP TABLE IF EXISTS GENRE;
DROP TABLE IF EXISTS BOOKS;

CREATE TABLE AUTHORS (
                               id BIGINT AUTO_INCREMENT  PRIMARY KEY,
                               first_name VARCHAR(250) NOT NULL,
                               last_name VARCHAR(250) NOT NULL
);

CREATE TABLE GENRE (
                         id BIGINT AUTO_INCREMENT  PRIMARY KEY,
                         genre VARCHAR(100) NOT NULL
);

CREATE TABLE BOOKS (
                         id BIGINT AUTO_INCREMENT  PRIMARY KEY,
                         name VARCHAR(250) NOT NULL,
                         number_of_pages INT NOT NULL,
                         author_id BIGINT,
                         genre_id BIGINT,
                         FOREIGN KEY (author_id) REFERENCES AUTHORS,
                         FOREIGN KEY (genre_id) REFERENCES GENRE
);


