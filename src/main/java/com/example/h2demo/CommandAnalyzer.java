package com.example.h2demo;

import com.example.h2demo.dao.DAO;
import com.example.h2demo.models.Author;
import com.example.h2demo.models.Genre;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Component
public class CommandAnalyzer {

    private DAO<Author> authorDAO;
    private DAO<Genre> genreDAO;

    private Scanner in = new Scanner(System.in);

    public CommandAnalyzer(DAO<Author> authorDAO, DAO<Genre> genreDAO)
    {
        this.authorDAO=authorDAO;
        this.genreDAO=genreDAO;
    }

    public  void analyzeCommandForAuthors()
    {
        System.out.println("Enter command (CREATE, READ, READ_ALL, UPDATE, DELETE):");
        String commandAuthors = in.next();

        if(commandAuthors.equals("CREATE"))
        {
            System.out.println("Enter first name:");
            String firstName = in.next();
            System.out.println("Enter last name:");
            String lastName = in.next();

            Author author = new Author(firstName,lastName);
            authorDAO.create(author);

        }else if(commandAuthors.equals("READ"))
        {
            System.out.println("Enter author id:");
            int id = in.nextInt();
            System.out.println("Author: ");
            Optional<Author> firstOne = authorDAO.get(id);

            if(firstOne.isPresent())
                System.out.println(firstOne.get());
            else
                System.out.println("Author not found!");

        }else if(commandAuthors.equals("READ_ALL"))
        {
            System.out.println("All authors: \n");
            List<Author> authors = authorDAO.list();
            authors.forEach(System.out::println);

        }else if(commandAuthors.equals("DELETE"))
        {
            System.out.println("Enter author id:");
            int id = in.nextInt();
            authorDAO.delete(id);

        }else if (commandAuthors.equals("UPDATE")){
            System.out.println("Enter author id:");
            int id = in.nextInt();
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
        String commandGenre = in.next();

        if(commandGenre.equals("CREATE"))
        {
            System.out.println("Enter genre name:");
            String genreName = in.next();

            Genre genre = new Genre(genreName);
            genreDAO.create(genre);

        }else if(commandGenre.equals("READ"))
        {
            System.out.println("Enter genre id:");
            int id = in.nextInt();
            System.out.println("Genre: ");
            Optional<Genre> firstOne = genreDAO.get(id);

            if(firstOne.isPresent())
                System.out.println(firstOne.get());
            else
                System.out.println("Genre not found!");

        }else if(commandGenre.equals("READ_ALL"))
        {
            System.out.println("All genres: \n");
            List<Genre> genres = genreDAO.list();
            genres.forEach(System.out::println);

        }else if(commandGenre.equals("DELETE"))
        {
            System.out.println("Enter genre id:");
            int id = in.nextInt();
            genreDAO.delete(id);

        }else if (commandGenre.equals("UPDATE")){
            System.out.println("Enter genre id:");
            int id = in.nextInt();
            System.out.println("Enter genre name:");
            String genreName = in.next();

            Genre genre = new Genre(genreName);
            genreDAO.update(genre,id);
        }else {
            System.out.println("Command not found!!!");
        }
    }

    public void run()
    {
        while (true)
        {
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
            else {
                System.out.println("Table not found!");
            }

        }

    }
}
