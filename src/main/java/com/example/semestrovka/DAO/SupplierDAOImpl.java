package com.example.semestrovka.DAO;

import com.example.semestrovka.helpers.DataBaseConnection;
import com.example.semestrovka.models.Company;
import com.example.semestrovka.models.Forum;
import com.example.semestrovka.models.Supplier;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SupplierDAOImpl implements DAO<Supplier> {
    public static void save(Supplier supplier){
        try(Connection connection = DataBaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement("insert into supplier (user_id,company_name,title,contact_info,category,country) values (?,?,?,?,?,?)")){
            statement.setInt(1,supplier.getUserId());
            statement.setString(2, supplier.getCompanyName());
            statement.setString(3, supplier.getTitle());
            statement.setString(4, supplier.getContactInfo());
            statement.setString(5, supplier.getCategory());
            statement.setString(6, supplier.getCountry());
            statement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        setIdSupplier(supplier);
    }
    private static void setIdSupplier(Supplier supplier){
        try{
            Connection connection = DataBaseConnection.getConnection();
            try{
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("select * from supplier");
                while(resultSet.next()){
                    int idDb = resultSet.getInt(1);
                    String nameDb = resultSet.getString(3);
                    if(supplier.getCompanyName().equals(nameDb)) {
                        supplier.setId(idDb);
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

    public static List<Supplier> getAllSuppliers(){
        List<Supplier> suppliers = new ArrayList<>();
        try(Connection connection = DataBaseConnection.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from supplier")){
            while (resultSet.next()){
                Supplier supplier = new Supplier(
                        resultSet.getInt(1),
                        resultSet.getInt(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getString(6),
                        resultSet.getString(7)
                );
                suppliers.add(supplier);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return suppliers;
    }

    public static boolean isCompanyExistDB(Supplier supplier) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection =  DataBaseConnection.getConnection();
            statement = connection.prepareStatement("select * from supplier where company_name = ?");
            statement.setString(1, supplier.getCompanyName());
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
    public static Supplier getSupplyByName(String supplier){
        try{
            PreparedStatement statement = DataBaseConnection.getConnection().prepareStatement("select * from supplier where company_name = ? ");
            statement.setString(1,supplier);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()){
                Supplier s = new Supplier(
                        resultSet.getInt(1),
                        resultSet.getInt(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getString(6),
                        resultSet.getString(7)
                );
                return s;
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
    public void create(Supplier u) {

    }

    @Override
    public Supplier get(long id) {
        try{
            PreparedStatement statement = DataBaseConnection.getConnection().prepareStatement("select * from supplier where id = ? ");
            statement.setLong(1,id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()){
                Supplier s = new Supplier(
                        resultSet.getInt(1),
                        resultSet.getInt(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getString(6),
                        resultSet.getString(7)
                );
                return s;
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
    public void update(Supplier u) {

    }

    @Override
    public void delete(Supplier u) {

    }

    @Override
    public List<Supplier> getAll() {
        return null;
    }
}
