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
                break;
            }
        }
        return getPagedList(userList, pageSize, pageNum);
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
            return user;
        }
        return null;
    }

    @Override
    public boolean deleteUser(long userId) {
        Map<String, UserEntity> userEntityMap = storage.getUserMap();
        return userEntityMap.remove("user:" + userId, this.getUserById(userId));
    }

    @Override
    public <T> List<T> getPagedList(List<T> list, int pageSize, int pageNum) throws DaoException {
        List<T> pagedList;
        Integer sizeFullList = list.size();
        try {
            if (pageSize < 0 || pageNum < 0) {
                throw new IllegalArgumentException("page must be positive number");
            } else if (pageSize <= sizeFullList || sizeFullList == 0 || list == null) {
                return list;
            } else if (sizeFullList % pageSize < pageNum) {
                throw new IllegalArgumentException("We have only " + sizeFullList % pageSize +
                        " pages and page № " + pageNum + " is not exist");
            } else {
                pagedList = new ArrayList<>();
                pagedList = (List<T>) list.stream().skip(pageSize * pageNum).limit(pageNum).collect(Collectors.toList());
                return pagedList;
            }
        } catch (IllegalArgumentException e) {
            throw new DaoException(e.getMessage(), e);
        }
    }
}
