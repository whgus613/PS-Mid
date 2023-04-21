package com.example.portal_service_mid.user;

import javax.sql.DataSource;
import java.sql.*;

public class UserDao {

    private DataSource dataSource;

    public UserDao(DataSource dataSource){
        this.dataSource = dataSource;
    }

    public User findById(Long id) throws ClassNotFoundException, SQLException {

        StatementStrategy statementStrategy = new FindStatementStrategy(id);

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        User user = null;

        try {
            connection = dataSource.getConnection();
            preparedStatement = statementStrategy.makeStatement(connection);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setPassword(resultSet.getString("password"));
            }
        } finally {
            try {
                resultSet.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                preparedStatement.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                connection.close();
            }
            catch (SQLException e){
                e.printStackTrace();
            }
        }

        return user;

    }

    public void insert(User user) throws ClassNotFoundException, SQLException{

        StatementStrategy statementStrategy = new InsertStatementStrategy(user);

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = dataSource.getConnection();
            preparedStatement = statementStrategy.makeStatement(connection);
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();
            user.setId(resultSet.getLong(1));
        } finally {
            try {
                resultSet.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                preparedStatement.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                connection.close();
            }
            catch (SQLException e){
                e.printStackTrace();
            }
        }
    }

    public void update(User user) throws SQLException{

        StatementStrategy statementStrategy = new UpdateStatementStrategy(user);
        jdbcContextForUpdate(statementStrategy);
    }

    public void delete(Long id) throws SQLException{

        StatementStrategy statementStrategy = new DeleteStatementStrategy(id);
        jdbcContextForUpdate(statementStrategy);

    }

    private void jdbcContextForUpdate(StatementStrategy statementStrategy) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = dataSource.getConnection();
            preparedStatement = statementStrategy.makeStatement(connection);
            preparedStatement.executeUpdate();
        } finally {
            try {
                preparedStatement.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                connection.close();
            }
            catch (SQLException e){
                e.printStackTrace();
            }
        }
    }

}