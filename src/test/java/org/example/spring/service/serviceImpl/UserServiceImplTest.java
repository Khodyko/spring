package org.example.spring.service.serviceImpl;

import junit.framework.TestCase;
import org.example.spring.dao.daoImpl.UserDaoImpl;
import org.example.spring.model.Entity.UserEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

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
    }

    @Test
    public void testGetUsersByName() {
    }

    @Test
    public void testCreateUser() {
    }

    @Test
    public void testUpdateUser() {
    }

    @Test
    public void testDeleteUser() {
    }
}