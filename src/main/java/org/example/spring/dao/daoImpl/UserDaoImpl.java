package org.example.spring.dao.daoImpl;

import org.example.spring.Storage;
import org.example.spring.dao.UserDao;
import org.example.spring.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;

public class UserDaoImpl implements UserDao {
    private Storage storage;

    public Storage getStorage() {
        return storage;
    }

    public void setStorage(Storage storage) {
        this.storage = storage;
    }
    public UserDaoImpl() {
    }

    @Override
    public User getUserById(long userId) {
        return null;
    }

    @Override
    public User getUserByEmail(String email) {
        return null;
    }

    @Override
    public List<User> getUsersByName(String name, int pageSize, int pageNum) {
        return null;
    }

    @Override
    public User createUser(User user) {
        return null;
    }

    @Override
    public User updateUser(User user) {
        return null;
    }

    @Override
    public boolean deleteUser(long userId) {
        return false;
    }
}
