package com.example.semestrovka.DAO;

import com.example.semestrovka.helpers.DataBaseConnection;
import com.example.semestrovka.models.Company;
import com.example.semestrovka.models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CompanyDAOImpl implements DAO<Company> {
    public static void saveCompany(Company company, User user){
        try(Connection connection = DataBaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement("insert into company (user_id,name,business_sector," +
                    "country, title) values (?,?,?,?,?)")){
            statement.setInt(1,user.getId());
            statement.setString(2,company.getName());
            statement.setString(3,company.getBusinessSector());
            statement.setString(4,company.getCountry());
            statement.setString(5,company.getTitle());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        setIdCompany(company);
    }
    private static void setIdCompany(Company company){
        try{
            Connection connection = DataBaseConnection.getConnection();
            try{
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("select * from company");
                while(resultSet.next()){
                    int idDb = resultSet.getInt(1);
                    int userIdDb = resultSet.getInt(2);
                    String nameDb = resultSet.getString(3);
                    if(company.getUser_id() == userIdDb && company.getName().equals(nameDb)) {
                        company.setId(idDb);
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
    public static List<Company> getAllUserCompaniesFromDB(User user){
        List<Company> allCompanies = new ArrayList<>();
        try{
            Connection connection = DataBaseConnection.getConnection();
            try{
                PreparedStatement statement = connection.prepareStatement("select * from company where user_id = ?");
                statement.setInt(1,user.getId());
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()){
                    Company company = new Company(resultSet.getInt(1),
                            resultSet.getInt(2),
                            resultSet.getString(3),
                            resultSet.getString(4),
                            resultSet.getString(5),
                            resultSet.getString(6)
                    );
                    allCompanies.add(company);
                }
                return allCompanies;
            }finally {
                connection.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static Company getCompanyFromDB(Company company){
        Company companyDB = null;
        try{
            Connection connection = DataBaseConnection.getConnection();
            try{
                PreparedStatement statement = connection.prepareStatement("select * from company where name = ?");
                statement.setString(1,company.getName());
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()){
                    companyDB = new Company(resultSet.getInt(1),
                            resultSet.getInt(2),
                            resultSet.getString(3),
                            resultSet.getString(4),
                            resultSet.getString(5),
                            resultSet.getString(6));
                    return companyDB;
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
    public static Company getCompanyByName(String company){
        Company companyDB = null;
        try{
            Connection connection = DataBaseConnection.getConnection();
            try{
                PreparedStatement statement = connection.prepareStatement("select * from company where name = ?");
                statement.setString(1,company);
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()){
                    companyDB = new Company(resultSet.getInt(1),
                            resultSet.getInt(2),
                            resultSet.getString(3),
                            resultSet.getString(4),
                            resultSet.getString(5),
                            resultSet.getString(6));
                    return companyDB;
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
    public static List<String> getAllCountries(){
        List<String> countries = new ArrayList<>();
        try{
            Connection connection = DataBaseConnection.getConnection();
            try{
                PreparedStatement statement = connection.prepareStatement("select distinct country from company");
                ResultSet resultSet = statement.executeQuery();
                while(resultSet.next()){
                    countries.add(resultSet.getString("country"));
                }
                return countries;
            }finally {
                connection.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public static List<String> getAllBusinessSector(){
        List<String> businessSectors = new ArrayList<>();
        try{
            Connection connection = DataBaseConnection.getConnection();
            try{
                PreparedStatement statement = connection.prepareStatement("select distinct business_sector from company");
                ResultSet resultSet = statement.executeQuery();
                while(resultSet.next()){
                    businessSectors.add(resultSet.getString("business_sector"));
                }
                return businessSectors;
            }finally {
                connection.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public static boolean isExistCompany(Company company) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection =  DataBaseConnection.getConnection();
            statement = connection.prepareStatement("select * from company where name = ?");
            statement.setString(1, company.getName());
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return true;
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return false;
    }
    @Override
    public void create(Company c) {

    }

    @Override
    public Company get(long id) {
        try{
            PreparedStatement statement = DataBaseConnection.getConnection().prepareStatement("select * from company where id = ? ");
            statement.setLong(1,id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()){
                Company c = new Company(
                        resultSet.getInt(1),
                        resultSet.getInt(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getString(6)
                );
                return c;
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
    public void update(Company c) {

    }

    @Override
    public void delete(Company c) {
        try(Connection connection = DataBaseConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement("delete from company where name = ?")){
            statement.setString(1,c.getName());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Company> getAll() {
         List<Company> allCompanies = new ArrayList<>();
         try {
             Connection connection = DataBaseConnection.getConnection();
             try{
                 PreparedStatement statement = connection.prepareStatement("select * from company");
                 ResultSet resultSet = statement.executeQuery();
                 while (resultSet.next()){
                     Company company = new Company(resultSet.getInt(1),
                             resultSet.getInt(2),
                             resultSet.getString(3),
                             resultSet.getString(4),
                             resultSet.getString(5),
                             resultSet.getString(6)
                     );
                     allCompanies.add(company);
                 }
                 return allCompanies;
             } catch (SQLException e) {
                 throw new RuntimeException(e);
             } finally {
                 connection.close();
             }
         } catch (ClassNotFoundException | SQLException e) {
             throw new RuntimeException(e);
         }
    }
}
