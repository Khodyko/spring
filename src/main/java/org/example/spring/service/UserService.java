package org.example.spring.service;

import org.example.spring.facade.BookingFacade;
import org.example.spring.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    public User getUserById(long userId);

    public User getUserByEmail(String email);

    public List<User> getUsersByName(String name, int pageSize, int pageNum);

    public User createUser(User user);

    public User updateUser(User user);

    public boolean deleteUser(long userId);
}
