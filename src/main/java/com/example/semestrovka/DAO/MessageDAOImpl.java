package com.example.semestrovka.DAO;

import com.example.semestrovka.helpers.DataBaseConnection;
import com.example.semestrovka.models.Company;
import com.example.semestrovka.models.Message;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MessageDAOImpl implements DAO<Message> {
    public static void save(Message message){
        try(Connection connection = DataBaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement("insert into message (forum_id,sender,text,timestamp) values (?,?,?,?)")){
            statement.setInt(1,message.getForumId());
            statement.setString(2,message.getSender());
            statement.setString(3,message.getText());
            statement.setString(4,message.getTimestamp() );
            statement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public static List<Message> getAllMessages(int idForum){
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Message> messages = new ArrayList<>();

        try {
            connection =  DataBaseConnection.getConnection();
            statement = connection.prepareStatement("select * from message where forum_id = ?");
            statement.setInt(1, idForum);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Message message = new Message();
                message.setId(resultSet.getInt(1));
                message.setForumId(resultSet.getInt(2));
                message.setSender(resultSet.getString(3));
                message.setText(resultSet.getString(4));
                message.setTimestamp(resultSet.getString(5));
                messages.add(message);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return messages;
    }

    @Override
    public void create(Message u) {

    }

    @Override
    public Message get(long id) {
        return null;
    }

    @Override
    public void update(Message u) {

    }

    @Override
    public void delete(Message u) {

    }

    @Override
    public List<Message> getAll() {
        return null;
    }
}
