package com.example.portal_service_mid.user;


public class DaoFactory {

    public UserDao getUserDao(){
        UserDao userDao = new UserDao(connectionMaker());
        return userDao;
    }

    private ConnectionMaker connectionMaker() {
        return new JejuConnectionMaker();
    }


}
