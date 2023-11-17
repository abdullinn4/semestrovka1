package com.example.semestrovka.DAO;

import com.example.semestrovka.helpers.DataBaseConnection;
import com.example.semestrovka.models.Company;
import com.example.semestrovka.models.Cooperation;
import com.example.semestrovka.models.Supplier;
import com.example.semestrovka.models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CooperationDAOImpl implements DAO<Cooperation> {
    public static void save(Cooperation cooperation){
        try(Connection connection = DataBaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement("insert into cooperation(supplier_id,partner_id) values (?,?)")){
            Supplier supplier = SupplierDAOImpl.getSupplyByName(cooperation.getSupplierName());
            Company company = CompanyDAOImpl.getCompanyByName(cooperation.getPartnerName());
            statement.setInt(1, supplier.getId());
            statement.setInt(2,company.getId());
            statement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        setIdCooperation(cooperation);
    }

    private static void setIdCooperation(Cooperation cooperation){
        SupplierDAOImpl supplierDAO = new SupplierDAOImpl();
        CompanyDAOImpl companyDAO = new CompanyDAOImpl();
        try{
            Connection connection = DataBaseConnection.getConnection();
            try{
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("select * from cooperation");
                while(resultSet.next()){
                    int idDb = resultSet.getInt(1);
                    int supplierIdDb = resultSet.getInt(2);
                    int partnerIdDb = resultSet.getInt(3);
                    Supplier supplier = supplierDAO.get(supplierIdDb);
                    Company partner = companyDAO.get(partnerIdDb);
                    if(supplier.getCompanyName().equals(cooperation.getSupplierName()) && partner.getName().equals(cooperation.getPartnerName())) {
                        cooperation.setId(idDb);
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
    public static boolean isExistCooperation(Cooperation cooperation) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection =  DataBaseConnection.getConnection();
            statement = connection.prepareStatement("select * from cooperation where supplier_id = ? and partner_id=?");
            Supplier supplier = SupplierDAOImpl.getSupplyByName(cooperation.getSupplierName());
            Company company = CompanyDAOImpl.getCompanyByName(cooperation.getPartnerName());
            statement.setInt(1, supplier.getId());
            statement.setInt(2,company.getId());
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
    public static boolean isSameCreateUser(Cooperation cooperation){
        Supplier supplier = SupplierDAOImpl.getSupplyByName(cooperation.getSupplierName());
        Company company = CompanyDAOImpl.getCompanyByName(cooperation.getPartnerName());
        if (supplier.getUserId() == company.getId()){
            return true;
        }
        return false;
    }
    public static List<Cooperation> getAllCooperations(User user){
        SupplierDAOImpl supplierDAO = new SupplierDAOImpl();
        CompanyDAOImpl companyDAO = new CompanyDAOImpl();
        List<Cooperation> cooperations = new ArrayList<>();
        try{
            Connection connection = DataBaseConnection.getConnection();
            try{
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("select * from cooperation");
                while(resultSet.next()){
                    int idDb = resultSet.getInt(1);
                    int supplierIdDb = resultSet.getInt(2);
                    int partnerIdDb = resultSet.getInt(3);
                    Supplier supplier = supplierDAO.get(supplierIdDb);
                    Company partner = companyDAO.get(partnerIdDb);
                    if (supplier.getUserId() == user.getId() || partner.getUser_id() == user.getId()){
                        Cooperation cooperation = new Cooperation(
                                idDb,
                                supplier.getCompanyName(),
                                partner.getName()
                        );
                        cooperations.add(cooperation);
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
        return cooperations;
    }

    @Override
    public void create(Cooperation u) {

    }

    @Override
    public Cooperation get(long id) {
        CompanyDAOImpl companyDAO = new CompanyDAOImpl();
        SupplierDAOImpl supplierDAO = new SupplierDAOImpl();
        try{
            PreparedStatement statement = DataBaseConnection.getConnection().prepareStatement("select * from cooperation where id = ? ");
            statement.setLong(1,id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()){
                Supplier supplier = supplierDAO.get(resultSet.getInt(2));
                Company company = companyDAO.get(resultSet.getInt(3));
                Cooperation c = new Cooperation(
                        resultSet.getInt(1),
                        supplier.getCompanyName(),
                        company.getName()
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
    public void update(Cooperation u) {

    }

    @Override
    public void delete(Cooperation u) {

    }

    @Override
    public List<Cooperation> getAll() {
        return null;
    }
}
