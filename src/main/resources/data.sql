INSERT INTO AUTHORS (first_name, last_name) VALUES
('Lev', 'Tolstoy'),
('Fedya', 'Petrov'),
('Ben', 'Forta');

INSERT INTO GENRE (genre) VALUES ( 'Classic' );
INSERT INTO GENRE (genre) VALUES ( 'Sport' );
INSERT INTO GENRE (genre) VALUES ( 'Computer science' );

INSERT INTO BOOKS (name, number_of_pages, author_id, genre_id) VALUES ( 'War and peace', 5000, 1, 1 );
INSERT INTO BOOKS (name, number_of_pages, author_id, genre_id) VALUES ( 'Sports sambo', 560, 2, 2 );
INSERT INTO BOOKS (name, number_of_pages, author_id, genre_id) VALUES ( 'SQL in 10 minutes', 280, 3, 3 );