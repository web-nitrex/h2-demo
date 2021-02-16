package com.example.h2demo;

import com.example.h2demo.dao.DAO;
import com.example.h2demo.models.Author;
import com.example.h2demo.models.Book;
import com.example.h2demo.models.Genre;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Component
public class CommandAnalyzer implements CommandLineRunner {

    private final DAO<Author> authorDAO;
    private final DAO<Genre> genreDAO;
    private final DAO<Book> bookDAO;

    private Scanner in = new Scanner(System.in);

    public CommandAnalyzer(DAO<Author> authorDAO, DAO<Genre> genreDAO, DAO<Book> bookDAO)
    {
        this.authorDAO=authorDAO;
        this.genreDAO=genreDAO;
        this.bookDAO=bookDAO;
    }

    public  void analyzeCommandForAuthors()
    {
        System.out.println("Enter command (CREATE, READ, READ_ALL, UPDATE, DELETE):");
        String commandForAuthors = in.next();

        if(commandForAuthors.equals("CREATE"))
        {
            System.out.println("Enter first name:");
            String firstName = in.next();
            System.out.println("Enter last name:");
            String lastName = in.next();

            Author author = new Author(firstName,lastName);
            authorDAO.create(author);

        }else if(commandForAuthors.equals("READ"))
        {
            System.out.println("Enter author id:");
            long id = in.nextLong();
            System.out.println("Author: ");
            Optional<Author> firstOne = authorDAO.get(id);

            if(firstOne.isPresent())
                System.out.println(firstOne.get());
            else
                System.out.println("Author not found!");

        }else if(commandForAuthors.equals("READ_ALL"))
        {
            System.out.println("All authors: \n");
            List<Author> authors = authorDAO.list();
            authors.forEach(System.out::println);

        }else if(commandForAuthors.equals("DELETE"))
        {
            System.out.println("Enter author id:");
            long id = in.nextLong();
            authorDAO.delete(id);

        }else if (commandForAuthors.equals("UPDATE")){
            System.out.println("Enter author id:");
            long id = in.nextLong();
            System.out.println("Enter first name:");
            String firstName = in.next();
            System.out.println("Enter last name:");
            String lastName = in.next();

            Author author = new Author(firstName,lastName);
            authorDAO.update(author,id);
        }else {
            System.out.println("Command not found!!!");
        }

    }

    public void analyzeCommandForGenre()
    {
        System.out.println("Enter command (CREATE, READ, READ_ALL, UPDATE, DELETE):");
        String commandForGenre = in.next();

        if(commandForGenre.equals("CREATE"))
        {
            System.out.println("Enter genre name:");
            String genreName = in.next();

            Genre genre = new Genre(genreName);
            genreDAO.create(genre);

        }else if(commandForGenre.equals("READ"))
        {
            System.out.println("Enter genre id:");
            long id = in.nextLong();
            System.out.println("Genre: ");
            Optional<Genre> firstOne = genreDAO.get(id);

            if(firstOne.isPresent())
                System.out.println(firstOne.get());
            else
                System.out.println("Genre not found!");

        }else if(commandForGenre.equals("READ_ALL"))
        {
            System.out.println("All genres: \n");
            List<Genre> genres = genreDAO.list();
            genres.forEach(System.out::println);

        }else if(commandForGenre.equals("DELETE"))
        {
            System.out.println("Enter genre id:");
            long id = in.nextLong();
            genreDAO.delete(id);

        }else if (commandForGenre.equals("UPDATE")){
            System.out.println("Enter genre id:");
            long id = in.nextLong();
            System.out.println("Enter genre name:");
            String genreName = in.next();

            Genre genre = new Genre(genreName);
            genreDAO.update(genre,id);
        }else {
            System.out.println("Command not found!!!");
        }
    }

    public  void analyzeCommandForBooks()
    {
        System.out.println("Enter command (CREATE, READ, READ_ALL, UPDATE, DELETE):");
        String commandForBooks = in.next();

        if(commandForBooks.equals("CREATE"))
        {
            System.out.println("Enter book name:");
            in.nextLine();
            String name = in.nextLine();

            System.out.println("Enter number of pages:");
            int numberOfPages = in.nextInt();

            System.out.println("Enter author ID:");
            long authorId = in.nextLong();

            System.out.println("Enter genre ID:");
            long genreId = in.nextLong();

            Optional<Author> isAuthor = authorDAO.get(authorId);
            Optional<Genre> isGenre = genreDAO.get(genreId);

            //проверяем что существуют такие id для автора и жанра
            if(isAuthor.isPresent() && isGenre.isPresent())
            {
                Book book = new Book(name, numberOfPages, authorId, genreId);
                bookDAO.create(book);
            }
            else
            {
                System.out.println("Error create book! Bad author ID or genre ID!");
            }



        }else if(commandForBooks.equals("READ"))
        {
            System.out.println("Enter book id:");
            long id = in.nextLong();
            System.out.println("Book: ");
            Optional<Book> firstOne = bookDAO.get(id);

            if(firstOne.isPresent())
                System.out.println(firstOne.get());
            else
                System.out.println("Book not found!");

        }else if(commandForBooks.equals("READ_ALL"))
        {
            System.out.println("All books: \n");
            List<Book> books = bookDAO.list();
            books.forEach(System.out::println);

        }else if(commandForBooks.equals("DELETE"))
        {
            System.out.println("Enter book id:");
            long id = in.nextLong();
            bookDAO.delete(id);

        }else if (commandForBooks.equals("UPDATE")){
            System.out.println("Enter book id:");
            long id = in.nextLong();
            in.nextLine();

            System.out.println("Enter book name:");
            String name = in.nextLine();

            System.out.println("Enter number of pages:");
            int numberOfPages = in.nextInt();

            System.out.println("Enter author ID:");
            long authorId = in.nextLong();

            System.out.println("Enter genre ID:");
            long genreId = in.nextLong();

            Optional<Author> isAuthor = authorDAO.get(authorId);
            Optional<Genre> isGenre = genreDAO.get(genreId);

            //проверяем что существуют такие id для автора и жанра
            if(isAuthor.isPresent() && isGenre.isPresent())
            {
                Book book = new Book(name, numberOfPages, authorId, genreId);
                bookDAO.update(book, id);
            }
            else
            {
                System.out.println("Error update book! Bad author ID or genre ID!");
            }
        }else {
            System.out.println("Command not found!!!");
        }

    }

//    public void run()
//    {
//        while (true)
//        {
//            System.out.println("<==========================================================>\n");
//            System.out.println("Enter table name :");
//            String command = in.next();
//
//            if(command.equals("AUTHORS"))
//            {
//                analyzeCommandForAuthors();
//            }
//            else if(command.equals("GENRE"))
//            {
//                analyzeCommandForGenre();
//            }
//            else if(command.equals("BOOKS"))
//            {
//                analyzeCommandForBooks();
//            }
//            else {
//                System.out.println("Table not found!");
//            }
//
//        }
//
//    }

    @Override
    public void run(String... args) throws Exception {
        while (true)
        {
            System.out.println("<==========================================================>\n");
            System.out.println("Enter table name :");
            String command = in.next();

            if(command.equals("AUTHORS"))
            {
                analyzeCommandForAuthors();
            }
            else if(command.equals("GENRE"))
            {
                analyzeCommandForGenre();
            }
            else if(command.equals("BOOKS"))
            {
                analyzeCommandForBooks();
            }
            else {
                System.out.println("Table not found!");
            }

        }
    }
}
