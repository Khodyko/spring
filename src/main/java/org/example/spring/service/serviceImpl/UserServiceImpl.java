package org.example.spring.service.serviceImpl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.spring.dao.ExceptionDao.DaoException;
import org.example.spring.dao.daoImpl.UserDaoImpl;
import org.example.spring.model.User;
import org.example.spring.service.ServiceException.ServiceException;
import org.example.spring.service.UserService;

import java.util.List;

import static org.apache.logging.log4j.Level.DEBUG;
import static org.apache.logging.log4j.Level.INFO;

public class UserServiceImpl implements UserService {
    private UserDaoImpl userDaoImpl;
    private final static Logger logger = LogManager.getLogger();

    public UserServiceImpl(UserDaoImpl userDaoImpl) {
        this.userDaoImpl = userDaoImpl;
        logger.log(DEBUG, this.getClass().getSimpleName() + " was created");
    }

    @Override
    public User getUserById(long userId) {
        logger.log(DEBUG, Thread.currentThread().getStackTrace()[1].getMethodName() + " method start");
        return userDaoImpl.getUserById(userId);
    }

    @Override
    public User getUserByEmail(String email) {
        logger.log(DEBUG, Thread.currentThread().getStackTrace()[1].getMethodName() + " method start");
        return userDaoImpl.getUserByEmail(email);
    }

    @Override
    public List<User> getUsersByName(String name, int pageSize, int pageNum) throws ServiceException {
        logger.log(DEBUG, Thread.currentThread().getStackTrace()[1].getMethodName() + " method start");
        try {
            return userDaoImpl.getUsersByName(name, pageSize, pageNum);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public User createUser(User user) {
        logger.log(DEBUG, Thread.currentThread().getStackTrace()[1].getMethodName() + " method start");
        return userDaoImpl.saveUser(user);
    }

    @Override
    public User updateUser(User user) {
        logger.log(DEBUG, Thread.currentThread().getStackTrace()[1].getMethodName() + " method start");
        return userDaoImpl.updateUser(user);
    }

    @Override
    public boolean deleteUser(long userId) {
        logger.log(DEBUG, Thread.currentThread().getStackTrace()[1].getMethodName() + " method start");
        return userDaoImpl.deleteUser(userId);
    }
}
