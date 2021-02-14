package com.nzavod.springbooteducation.service;

import com.nzavod.springbooteducation.model.User;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Component
public class UserServiceImpl implements UserService{
    private static File listOfUsers = new File("UserList");

    @Override
    public void write(User user) throws IOException {
        FileWriter fileWriter = new FileWriter(listOfUsers, true);
        fileWriter.write(user.toString());
        fileWriter.close();
    }

    @Override
    public List<User> userList() throws FileNotFoundException {
        List<String> users = new ArrayList<>();
        List<User> userList = new ArrayList<>();
        Scanner scanner = new Scanner(listOfUsers);
        while (scanner.hasNext()){
            users.add(scanner.nextLine());
        }
        scanner.close();
        for (String s : users){
            Scanner scanner1 = new Scanner(s);
            scanner1.useDelimiter(" ");
            while(scanner1.hasNext()){
                String firstName = scanner1.next();
                String secondName = scanner1.next();
                String lastName = scanner1.next();
                int age = Integer.parseInt(scanner1.next());
                int salary = Integer.parseInt(scanner1.next());
                String email = scanner1.next();
                String work = scanner1.next();
                User user = User.builder()
                        .firstName(firstName)
                        .secondName(secondName.equals("Middle-Name-Absent") ? "" : secondName)
                        .lastName(lastName)
                        .age(age)
                        .salary(salary)
                        .email(email)
                        .work(work)
                        .build();
                userList.add(user);
            }
        }
        return userList;
    }

    @Override
    public User getByName(String firstName, String lastName) throws FileNotFoundException {
        User user = null;
        List<User> userList = userList();
        for (User u: userList) {
            if(u.getFirstName().equals(firstName) && u.getLastName().equals(lastName)){
                user = u;
                break;
            }
        }
        return user;
    }
}
