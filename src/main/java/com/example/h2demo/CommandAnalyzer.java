package com.example.h2demo;

import com.example.h2demo.dao.DAO;
import com.example.h2demo.models.Author;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Component
public class CommandAnalyzer {
    private DAO<Author> daoAuthor;
    private Scanner in = new Scanner(System.in);

    public CommandAnalyzer(DAO<Author> daoAuthor) {
        this.daoAuthor=daoAuthor;
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
            daoAuthor.create(author);

        }else if(commandAuthors.equals("READ"))
        {
            System.out.println("Enter author id:");
            int id = in.nextInt();
            System.out.println("Author: ");
            Optional<Author> firstOne = daoAuthor.get(id);

            if(firstOne.isPresent())
                System.out.println(firstOne.get());
            else
                System.out.println("Author not found!");

        }else if(commandAuthors.equals("READ_ALL"))
        {
            System.out.println("All authors: \n");
            List<Author> authors = daoAuthor.list();
            authors.forEach(System.out::println);

        }else if(commandAuthors.equals("DELETE"))
        {
            System.out.println("Enter author id:");
            int id = in.nextInt();
            daoAuthor.delete(id);

        }else if (commandAuthors.equals("UPDATE")){
            System.out.println("Enter author id:");
            int id = in.nextInt();
            System.out.println("Enter first name:");
            String firstName = in.next();
            System.out.println("Enter last name:");
            String lastName = in.next();

            Author author = new Author(firstName,lastName);
            daoAuthor.update(author,id);
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
            else {
                if(!command.equals(""))
                    System.out.println("Table not found!");
            }

        }

    }
}
