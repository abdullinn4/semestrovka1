package com.example.semestrovka.DAO;

import com.example.semestrovka.helpers.DataBaseConnection;
import com.example.semestrovka.models.Company;
import com.example.semestrovka.models.Forum;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ForumDAOImpl implements  DAO<Forum>{
    public static void save(Forum forum){
        try(Connection connection = DataBaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement("insert into forum (name,title," +
                    "topic, company_create_id,create_date) values (?,?,?,?,?)")){
            Company createCompany = CompanyDAOImpl.getCompanyFromDB(new Company(forum.getCompanyName()));
            if (createCompany!= null) {
                statement.setString(1, forum.getName());
                statement.setString(2, forum.getTitle());
                statement.setString(3, forum.getTopic());
                statement.setInt(4, createCompany.getId());
                statement.setDate(5, forum.getCreateDate());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        setIdForum(forum);
    }
    private static void setIdForum(Forum forum){
        try{
            Connection connection = DataBaseConnection.getConnection();
            try{
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("select * from forum");
                while(resultSet.next()){
                    int idForum = resultSet.getInt(1);
                    String name = resultSet.getString(2);
                    String title = resultSet.getString(3);
                    String topic = resultSet.getString(4);
                    int companyId = resultSet.getInt(5);
                    Date date = resultSet.getDate(6);
                    Company createCompany = CompanyDAOImpl.getCompanyFromDB(new Company(forum.getCompanyName()));
                    if (createCompany!= null) {
                        if (name.equals(forum.getName()) && title.equals(forum.getTitle()) && topic.equals(forum.getTopic())
                                && companyId == createCompany.getId() && date == forum.getCreateDate()) {
                            forum.setId(idForum);
                        }
                    }
                }resultSet.close();
                statement.close();
            }finally {
                connection.close();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public static List<Forum> getAllForums (){
        List<Forum> forums = new ArrayList<>();
        CompanyDAOImpl companyDAO = new CompanyDAOImpl();
        try(Connection connection = DataBaseConnection.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from forum")){
            while (resultSet.next()){
                Company createCompany = companyDAO.get(resultSet.getInt(5));
                Forum forum = new Forum(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        createCompany.getName(),
                        resultSet.getDate(6)
                );
                forums.add(forum);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return  forums;
    }

    @Override
    public void create(Forum u) {

    }

    @Override
    public Forum get(long id) {
        CompanyDAOImpl companyDAO = new CompanyDAOImpl();
        try{
            PreparedStatement statement = DataBaseConnection.getConnection().prepareStatement("select * from forum where id = ? ");
            statement.setLong(1,id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()){
                Company c = companyDAO.get(resultSet.getInt(5));
                Forum f = new Forum(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        c.getName(),
                        resultSet.getDate(6)
                );
                return f;
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
    public void update(Forum u) {

    }

    @Override
    public void delete(Forum u) {

    }

    @Override
    public List<Forum> getAll() {
        return null;
    }
}
