
DROP TABLE IF EXISTS AUTHORS;
DROP TABLE IF EXISTS GENRE;
DROP TABLE IF EXISTS BOOKS;

CREATE TABLE AUTHORS (
                               id INT AUTO_INCREMENT  PRIMARY KEY,
                               first_name VARCHAR(250) NOT NULL,
                               last_name VARCHAR(250) NOT NULL
);

INSERT INTO AUTHORS (first_name, last_name) VALUES
('Lev', 'Tolstoy'),
('Fedya', 'Petrov'),
('Ben', 'Forta');

CREATE TABLE GENRE (
                         id INT AUTO_INCREMENT  PRIMARY KEY,
                         genre VARCHAR(100) NOT NULL
);

INSERT INTO GENRE (genre) VALUES ( 'Classic' );
INSERT INTO GENRE (genre) VALUES ( 'Sport' );
INSERT INTO GENRE (genre) VALUES ( 'Computer science' );

CREATE TABLE BOOKS (
                         id INT AUTO_INCREMENT  PRIMARY KEY,
                         name VARCHAR(250) NOT NULL,
                         number_of_pages INT NOT NULL,
                         author_id INT,
                         genre_id INT,
                         FOREIGN KEY (author_id) REFERENCES AUTHORS,
                         FOREIGN KEY (genre_id) REFERENCES GENRE
);

INSERT INTO BOOKS (name, number_of_pages, author_id, genre_id) VALUES ( 'War and peace', 5000, 1, 1 );
INSERT INTO BOOKS (name, number_of_pages, author_id, genre_id) VALUES ( 'Sports sambo', 560, 2, 2 );
INSERT INTO BOOKS (name, number_of_pages, author_id, genre_id) VALUES ( 'SQL in 10 minutes', 280, 3, 3 );


