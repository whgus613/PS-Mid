package com.example.portal_service_mid.user;

import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.in;
import static org.hamcrest.core.Is.is;

public class UserDaoTests {
    @Test
    public void get() throws SQLException, ClassNotFoundException {
        Long id = 1l;
        String name = "hulk";
        String password = "1234";
        ConnectionMaker connectionMaker = new JejuConnectionMaker();
        UserDao userDao = new UserDao(connectionMaker);
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
        DaoFactory daoFactory = new DaoFactory();
        UserDao userDao = daoFactory.getUserDao();
        userDao.insert(user);
        assertThat(user.getId(), greaterThan(1l));

        User insertedUser = userDao.findById(user.getId());
        assertThat(insertedUser.getId(), is(user.getId()));
        assertThat(insertedUser.getName(), is(name));
        assertThat(insertedUser.getPassword(), is(password));
    }

}