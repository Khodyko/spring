package org.example.spring.service.serviceImpl;

import junit.framework.TestCase;
import org.example.spring.dao.ExceptionDao.DaoException;
import org.example.spring.dao.daoImpl.UserDaoImpl;
import org.example.spring.model.Entity.UserEntity;
import org.example.spring.model.User;
import org.example.spring.service.ServiceException.ServiceException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest extends TestCase {
    @Mock
    private UserDaoImpl userDaoImpl;

    @InjectMocks
    private UserServiceImpl userServiceImpl;

    @Test
    public void testGetUserById() {
        UserEntity userEntity=new UserEntity(12,"Sergei", "sergei@mail.ru");
        when(userDaoImpl.getUserById(12)).thenReturn(userEntity);
        assert(userServiceImpl.getUserById(12).getId()==12);
    }

    @Test
    public void testGetUserByEmail() {
        UserEntity userEntity=new UserEntity(12,"Sergei", "sergei@mail.ru");
        when(userDaoImpl.getUserByEmail("sergei@mail.ru")).thenReturn(userEntity);
        assert(userServiceImpl.getUserByEmail("sergei@mail.ru").getEmail().equals("sergei@mail.ru"));

    }

    @Test
    public void testGetUsersByName() {
        List<User> userList=new ArrayList<>();
        UserEntity userEntity=new UserEntity(12,"Sergei", "sergei@mail.ru");
        userList.add(userEntity);
        try {
            when(userDaoImpl.getUsersByName("Sergei", 100, 0)).thenReturn(userList);
        } catch (DaoException e) {
            //fix me
            e.printStackTrace();
        }
        List<User> usersFromMock= null;
        try {
            usersFromMock = userServiceImpl.getUsersByName("Sergei", 100, 0);
        } catch (ServiceException e) {
            //fix me
            e.printStackTrace();
        }
        assertNotNull(usersFromMock.get(0));
        assert(usersFromMock.size()>0);
    }

    @Test
    public void testCreateUser() {
        UserEntity userEntity=new UserEntity(12,"Sergei", "sergei@mail.ru");
        when(userDaoImpl.saveUser(userEntity)).thenReturn(userEntity);
        assert(userServiceImpl.createUser(userEntity).equals(userEntity));
    }

    @Test
    public void testUpdateUser() {
        UserEntity userEntity=new UserEntity(12,"Sergei", "sergei@mail.ru");
        when(userDaoImpl.updateUser(userEntity)).thenReturn(userEntity);
        assert(userServiceImpl.updateUser(userEntity).equals(userEntity));
    }

    @Test
    public void testDeleteUser() {
        UserEntity userEntity=new UserEntity(12,"Sergei", "sergei@mail.ru");
        when(userDaoImpl.updateUser(userEntity)).thenReturn(userEntity);
        assert(userServiceImpl.updateUser(userEntity).equals(userEntity));
    }
}