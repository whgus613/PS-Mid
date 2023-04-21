package com.example.portal_service_mid.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface StatementStrategy {
    PreparedStatement makeStatement(Connection connection) throws SQLException;
}
