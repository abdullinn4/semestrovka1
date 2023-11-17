package com.example.semestrovka.DAO;

import com.example.semestrovka.helpers.DataBaseConnection;
import com.example.semestrovka.models.Token;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TokenDAOImpl implements DAO<Token>{
    public static void saveCookies(int userId, String sessionId){
        try(Connection connection = DataBaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("insert into tokens (user_id,session_id) values (?,?)")){
            preparedStatement.setString(1, String.valueOf(userId));
            preparedStatement.setString(2,sessionId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public static void getCookiesFromDB(){
        try{
            Connection connection = DataBaseConnection.getConnection();
            try{
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("select * from tokens");
                while(resultSet.next()){
                    System.out.println(resultSet.getString(1) + " | " + resultSet.getString(2));
                }resultSet.close();
                statement.close();
            }finally {
                connection.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public static List<Integer> getUserIdInCookiesFromDB(){
        List<Integer> allId = new ArrayList<>();
        try{
            Connection connection = DataBaseConnection.getConnection();
            try{
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("select * from tokens");
                while(resultSet.next()){
                    allId.add(Integer.valueOf(resultSet.getString(1)));
                }resultSet.close();
                statement.close();
            }finally {
                connection.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return allId;
    }
    @Override
    public void create(Token u) {

    }

    @Override
    public Token get(long id) {
        return null;
    }

    @Override
    public void update(Token u) {

    }

    @Override
    public void delete(Token u) {

    }

    @Override
    public List<Token> getAll() {
        return null;
    }
}
