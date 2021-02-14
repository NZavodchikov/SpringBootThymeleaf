package com.nzavod.springbooteducation.service;

import com.nzavod.springbooteducation.model.User;
import org.apache.catalina.LifecycleState;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface UserService {
    public void write(User user) throws IOException;
    public List<User> userList() throws FileNotFoundException;
    public User getByName(String firstName, String lastName) throws FileNotFoundException;
}
