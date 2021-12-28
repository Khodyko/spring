package org.example.spring.dao;

import org.example.spring.model.User;

import java.util.List;

public interface UserDao {
    public User getUserById(long userId);

    public User getUserByEmail(String email);

    public List<User> getUsersByName(String name, int pageSize, int pageNum);

    public User createUser(User user);

    public User updateUser(User user);

    public boolean deleteUser(long userId);
}
