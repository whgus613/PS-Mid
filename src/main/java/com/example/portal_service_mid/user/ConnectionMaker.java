package com.example.portal_service_mid.user;

import java.sql.Connection;
import java.sql.SQLException;

public interface ConnectionMaker {


    Connection getConnection() throws ClassNotFoundException, SQLException;
}
