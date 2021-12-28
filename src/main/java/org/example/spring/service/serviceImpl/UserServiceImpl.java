package org.example.spring.service.serviceImpl;

import org.example.spring.dao.daoImpl.UserDaoImpl;
import org.example.spring.model.User;
import org.example.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

public class UserServiceImpl implements UserService {
    private UserDaoImpl userDaoImpl;

@Autowired
    public UserServiceImpl(UserDaoImpl userDaoImpl) {
        this.userDaoImpl = userDaoImpl;
    }

    @Override
    public User getUserById(long userId) {
        return userDaoImpl.getUserById(userId);
    }

    @Override
    public User getUserByEmail(String email) {
        return userDaoImpl.getUserByEmail(email);
    }

    @Override
    public List<User> getUsersByName(String name, int pageSize, int pageNum) {
        return userDaoImpl.getUsersByName(name, pageSize, pageNum);
    }

    @Override
    public User createUser(User user) {
        return userDaoImpl.createUser(user);
    }

    @Override
    public User updateUser(User user) {
        return userDaoImpl.updateUser(user);
    }

    @Override
    public boolean deleteUser(long userId) {
        return userDaoImpl.deleteUser(userId);
    }
}
