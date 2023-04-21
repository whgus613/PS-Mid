package com.example.portal_service_mid.user;

import org.hamcrest.core.IsNull;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.in;
import static org.hamcrest.core.Is.is;

public class UserDaoTests {

    private static UserDao userDao;

    @BeforeAll
    public static void UserDaoTests(){
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(DaoFactory.class);
        userDao = applicationContext.getBean("userDao", UserDao.class);
    }

    @Test
    public void get() throws SQLException, ClassNotFoundException {
        Long id = 1l;
        String name = "hulk";
        String password = "1234";
        User user = userDao.findById(id);
        assertThat(user.getId(), is(id));
        assertThat(user.getName(), is(name));
        assertThat(user.getPassword(), is(password));
    }

    @Test
    public void insert() throws SQLException, ClassNotFoundException{
        String name = "조현지";
        String password = "1111";
        User user = new User();
        user.setName(name);
        user.setPassword(password);
        userDao.insert(user);
        assertThat(user.getId(), greaterThan(1l));

        User insertedUser = userDao.findById(user.getId());
        assertThat(insertedUser.getId(), is(user.getId()));
        assertThat(insertedUser.getName(), is(name));
        assertThat(insertedUser.getPassword(), is(password));
    }

    @Test
    public void update() throws SQLException, ClassNotFoundException{
        String updateName = "현지";
        String updatePassword = "1212";
        User user = insertedUser();
        user.setName(updateName);
        user.setPassword(updatePassword);
        userDao.update(user);

        User updatedUser = userDao.findById(user.getId());
        assertThat(updatedUser.getName(), is(updateName));
        assertThat(updatedUser.getPassword(), is(updatePassword));
    }

    @Test
    public void delete() throws SQLException, ClassNotFoundException{
        User user = insertedUser();
        userDao.delete(user.getId());

        User deletedUser = userDao.findById(user.getId());
        assertThat(deletedUser, IsNull.nullValue());
    }

    @Test
    public User insertedUser() throws SQLException, ClassNotFoundException{
        String name = "조현지";
        String password = "1111";
        User user = new User();
        user.setName(name);
        user.setPassword(password);
        userDao.insert(user);
        return user;
    }

}