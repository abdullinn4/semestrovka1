package com.example.semestrovka.DAO;

import com.example.semestrovka.helpers.DataBaseConnection;
import com.example.semestrovka.models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements DAO<User>{
    public static void saveUser(User user){
        try(Connection connection = DataBaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("insert into users (email,password) values (?,?)")){
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        setIdUser(user);
    }
    private static void setIdUser(User user){
        try{
            Connection connection = DataBaseConnection.getConnection();
            try{
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("select * from users");
                while(resultSet.next()){
                    int idDb = resultSet.getInt(1);
                    String emailDb = resultSet.getString(2);
                    if(user.getEmail().equals(emailDb)) {
                        user.setId(idDb);
                    }
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
    public static boolean isUserInDb(User user){
        try {
            Connection connection = DataBaseConnection.getConnection();
            try {
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("select * from users");
                while(resultSet.next()){
                    String emailDb = resultSet.getString(2);
                    String passwordDB = resultSet.getString(3);
                    if(user.getEmail().equals(emailDb) && user.getPassword().equals(passwordDB)){
                        return true;
                    }
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
        return false;
    }
    public static boolean isUsernameInDb(String login){
        try {
            Connection connection = DataBaseConnection.getConnection();
            try {
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("select * from users");
                while(resultSet.next()){
                    String emailDb = resultSet.getString(2);
                    if(login.equals(emailDb)){
                        return true;
                    }
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
        return false;
    }
    public static User getUserFromDb(User user){
        User userDB = null;
        try{
            Connection connection = DataBaseConnection.getConnection();
            try{
                PreparedStatement statement = connection.prepareStatement("select * from users where email = ?");
                statement.setString(1,user.getEmail());
                ResultSet resultSet = statement.executeQuery();
                if(resultSet.next()){
                    userDB = new User();
                    userDB.setId(resultSet.getInt("id"));
                    userDB.setEmail(resultSet.getString("email"));
                    userDB.setPassword(resultSet.getString("password"));
                    userDB.setFirstName(resultSet.getString("name"));
                    userDB.setLastName(resultSet.getString("surname"));
                    userDB.setCountry(resultSet.getString("country"));
                    return userDB;
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
        return null;
    }




    @Override
    public void create(User u) {

    }

    @Override
    public User get(long id) {
        try{
            PreparedStatement statement = DataBaseConnection.getConnection().prepareStatement("select * from users where id = ? ");
            ResultSet resultSet = statement.executeQuery();
            statement.setLong(1,id);
            if (resultSet.next()){
                User u = new User(
                        resultSet.getString(2)
                );
                return u;
            }else{
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(User u) {
        try(Connection connection = DataBaseConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement
                ("update users set name = ?, surname = ?, country = ? where email = ? ")){
            statement.setString(4,u.getEmail());
            PreparedStatement statement1 = connection.prepareStatement("select * from users where email = ?");
            statement1.setString(1,u.getEmail());
            ResultSet resultSet = statement1.executeQuery();
            if (resultSet.next()) {
                if (u.getFirstName() == null) {
                    statement.setString(1, resultSet.getString(4));
                } else {
                    statement.setString(1, u.getFirstName());
                }
                if (u.getLastName() == null) {
                    statement.setString(2, resultSet.getString(5));
                } else {
                    statement.setString(2, u.getLastName());
                }
                if (u.getCountry() == null) {
                    statement.setString(3, resultSet.getString(6));
                } else {
                    statement.setString(3, u.getCountry());
                }
            }
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(User u) {
        try(Connection connection = DataBaseConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement("delete from users where email = ?")){
            statement.setString(1, u.getEmail());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        try{
            PreparedStatement statement = DataBaseConnection.getConnection().prepareStatement("select * from users");
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                User u = new User(
                        resultSet.getString(2)
                );
                users.add(u);
            }
            return users;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
