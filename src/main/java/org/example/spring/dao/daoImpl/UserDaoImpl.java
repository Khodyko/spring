package org.example.spring.dao.daoImpl;

import org.example.spring.Storage;
import org.example.spring.dao.ExceptionDao.DaoException;
import org.example.spring.dao.UserDao;
import org.example.spring.model.Entity.EventEntity;
import org.example.spring.model.Entity.UserEntity;
import org.example.spring.model.Event;
import org.example.spring.model.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class UserDaoImpl implements UserDao {
    private Storage storage;
    private ValidatorDao validatorDao;

    public ValidatorDao getValidatorDao() {
        return validatorDao;
    }

    public void setValidatorDao(ValidatorDao validatorDao) {
        this.validatorDao = validatorDao;
    }

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
        UserEntity user = null;
        Map<String, UserEntity> userEntityMap = storage.getUserMap();
        for (Map.Entry<String, UserEntity> entry : userEntityMap.entrySet()) {
            if (entry.getValue().getId() == userId) {
                user = entry.getValue();
                break;
            }
        }
        return user;
    }

    @Override
    public User getUserByEmail(String email) {
        UserEntity user = null;
        Map<String, UserEntity> userEntityMap = storage.getUserMap();
        for (Map.Entry<String, UserEntity> entry : userEntityMap.entrySet()) {
            if (entry.getValue().getEmail().equals(email)) {
                user = entry.getValue();
                break;
            }
        }
        return user;
    }

    @Override
    public List<User> getUsersByName(String name, int pageSize, int pageNum) throws DaoException {
        List<User> userList = new ArrayList<>();
        Map<String, UserEntity> userEntityMap = storage.getUserMap();
        for (Map.Entry<String, UserEntity> entry : userEntityMap.entrySet()) {
            if (entry.getValue().getName().equals(name)) {
                userList.add(entry.getValue());
            }
        }
        if (validatorDao.validateListForPage(userList, pageSize, pageNum)) {
            return getPagedList(userList, pageSize, pageNum);
        }
        return null;
    }

    @Override
    public User createUser(User user) {
        Map<String, UserEntity> userEntityMap = storage.getUserMap();
        userEntityMap.put("user:" + user.getId(), (UserEntity) user);
        return user;
    }

    @Override
    public User updateUser(User user) {
        Map<String, UserEntity> userEntityMap = storage.getUserMap();
        if (userEntityMap.containsKey("user:" + user.getId())) {
            userEntityMap.put("user:" + user.getId(), (UserEntity) user);
            List<?> list = new ArrayList<>();
            list.add(null);
            return user;
        }
        return null;
    }

    @Override
    public boolean deleteUser(long userId) {
        Map<String, UserEntity> userEntityMap = storage.getUserMap();
        return userEntityMap.remove("user:" + userId, this.getUserById(userId));
    }

    private List<User> getPagedList(List<User> userList, Integer pageSize, Integer pageNum) {
        List<User> pagedList = new ArrayList<>();
        pagedList = (List<User>) userList.stream().
                skip(pageSize * pageNum).limit(pageNum).
                collect(Collectors.toList());
        return pagedList;
    }

}
