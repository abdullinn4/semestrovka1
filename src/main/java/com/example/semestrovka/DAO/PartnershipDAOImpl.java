package com.example.semestrovka.DAO;

import com.example.semestrovka.helpers.DataBaseConnection;
import com.example.semestrovka.models.Company;
import com.example.semestrovka.models.Partnership;
import com.example.semestrovka.models.User;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class PartnershipDAOImpl implements DAO<Partnership> {
    public static void save(Partnership partnership){
        try(Connection connection = DataBaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement("insert into partnership (initiator_id,partner_id,status, start_date,end_date,title) values (?,?,?,?,?,?)")){
            Company initiatorCompany = CompanyDAOImpl.getCompanyFromDB(new Company(partnership.getInitiator()));
            Company partnerCompany = CompanyDAOImpl.getCompanyFromDB(new Company(partnership.getPartner()));
            if (initiatorCompany != null && partnerCompany!= null){
                statement.setInt(1,initiatorCompany.getId());
                statement.setInt(2,partnerCompany.getId());
                statement.setString(3,"В ожидании");
                statement.setDate(4,partnership.getStartDate() );
                statement.setDate(5,partnership.getEndDate() );
                statement.setString(6,partnership.getTitle());
                statement.executeUpdate();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        setIdPartnership(partnership);
    }
    private static void setIdPartnership(Partnership partnership){
        try{
            Connection connection = DataBaseConnection.getConnection();
            try{
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("select * from partnership");
                while(resultSet.next()){
                    int idPartnership = resultSet.getInt(1);
                    int idInitiatorDb = resultSet.getInt(2);
                    int idPartnerDb = resultSet.getInt(3);
                    Company initiatorCompany = CompanyDAOImpl.getCompanyFromDB(new Company(partnership.getInitiator()));
                    Company partnerCompany = CompanyDAOImpl.getCompanyFromDB(new Company(partnership.getPartner()));
                    Date startDateDb = resultSet.getDate(5);
                    Date endDateDb = resultSet.getDate(6);
                    if (idInitiatorDb == initiatorCompany.getId() && idPartnerDb == partnerCompany.getId()
                    && startDateDb == partnership.getStartDate() && endDateDb == partnership.getEndDate()){
                        partnership.setId(idPartnership);
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
    public static List<Partnership> getAllIncomingApplications(User user){
        CompanyDAOImpl companyDAO = new CompanyDAOImpl();
        List<Partnership> partnerships = new ArrayList<>();
        List<Company> companies = CompanyDAOImpl.getAllUserCompaniesFromDB(user);
        try{
            Connection connection = DataBaseConnection.getConnection();
            try{
                PreparedStatement statement = connection.prepareStatement("select * from partnership where partner_id = ?");
                for (Company company : companies){
                    statement.setInt(1,company.getId());
                    ResultSet resultSet = statement.executeQuery();
                    while (resultSet.next()){
                        Company c = companyDAO.get(resultSet.getInt(2));
                        Partnership partnership = new Partnership(
                                resultSet.getInt(1),
                                c.getName(),
                                company.getName(),
                                resultSet.getString(4),
                                resultSet.getDate(5),
                                resultSet.getDate(6),
                                resultSet.getString(7)
                        );
                        partnerships.add(partnership);
                    }
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }finally {
                connection.close();
            }
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        return partnerships;
    }
    public static List<Partnership> getAllOutgoingApplications(User user){
        CompanyDAOImpl companyDAO = new CompanyDAOImpl();
        List<Partnership> partnerships = new ArrayList<>();
        List<Company> companies = CompanyDAOImpl.getAllUserCompaniesFromDB(user);
        try{
            Connection connection = DataBaseConnection.getConnection();
            try{
                PreparedStatement statement = connection.prepareStatement("select * from partnership where initiator_id = ?");
                for (Company company : companies){
                    statement.setInt(1,company.getId());
                    ResultSet resultSet = statement.executeQuery();
                    while (resultSet.next()){
                        Company c = companyDAO.get(resultSet.getInt(3));
                        Partnership partnership = new Partnership(
                                resultSet.getInt(1),
                                company.getName(),
                                c.getName(),
                                resultSet.getString(4),
                                resultSet.getDate(5),
                                resultSet.getDate(6),
                                resultSet.getString(7)
                        );
                        partnerships.add(partnership);
                    }

                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }finally {
                connection.close();
            }
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        return partnerships;
    }
    public static List<Partnership> getAllPartnerships(User user) {
        CompanyDAOImpl companyDAO = new CompanyDAOImpl();
        List<Partnership> partnerships = new ArrayList<>();
        List<Company> companies = CompanyDAOImpl.getAllUserCompaniesFromDB(user);
        try {
            Connection connection = DataBaseConnection.getConnection();
            try {
                PreparedStatement statement = connection.prepareStatement("select * from partnership where (initiator_id = ? or partner_id = ?) and status = ?");
                for (Company company : companies) {
                    statement.setInt(1, company.getId());
                    statement.setInt(2, company.getId());
                    statement.setString(3, "Достигнуто");
                    ResultSet resultSet = statement.executeQuery();
                    while (resultSet.next()) {
                        Company c1 = companyDAO.get(resultSet.getInt(2));
                        Company c2 = companyDAO.get(resultSet.getInt(3));
                        Partnership partnership = new Partnership(
                                resultSet.getInt(1),
                                c1.getName(),
                                c2.getName(),
                                resultSet.getString(4),
                                resultSet.getDate(5),
                                resultSet.getDate(6),
                                resultSet.getString(7)
                        );
                        partnerships.add(partnership);
                    }
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } finally {
                connection.close();
            }
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        return partnerships;
    }
    public static String getStatusById(int id) {
        String status = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DataBaseConnection.getConnection();
            String sql = "select status from partnership where id = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                status = resultSet.getString("status");
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
        return status;
    }
    public static boolean isExistingPartnership(Partnership partnership) {
        try (Connection connection = DataBaseConnection.getConnection()) {
            String sql = "select COUNT(*) from partnerships where initiator_id = ? and partner_id = ? and status = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                Company initiatorCompany = CompanyDAOImpl.getCompanyFromDB(new Company(partnership.getInitiator()));
                Company partnerCompany = CompanyDAOImpl.getCompanyFromDB(new Company(partnership.getPartner()));
                statement.setInt(1, initiatorCompany.getId());
                statement.setInt(2, partnerCompany.getId());
                statement.setString(3, partnership.getStatus());
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    int count = resultSet.getInt(1);
                    return count > 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return false;
    }
    @Override
    public void create(Partnership u) {

    }

    @Override
    public Partnership get(long id) {
        CompanyDAOImpl companyDAO = new CompanyDAOImpl();
        try{
            PreparedStatement statement = DataBaseConnection.getConnection().prepareStatement("select * from partnership where id = ? ");
            statement.setLong(1,id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()){
                Company initiator = companyDAO.get(resultSet.getInt(2));
                Company partner = companyDAO.get(resultSet.getInt(3));
                Partnership p = new Partnership(
                        resultSet.getInt(1),
                        initiator.getName(),
                        partner.getName(),
                        resultSet.getString(4),
                        resultSet.getDate(5),
                        resultSet.getDate(6),
                        resultSet.getString(7)
                );
                return p;
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
    public void update(Partnership p) {
        try(Connection connection = DataBaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement
                    ("update partnership set status = ? where id = ? ")){
            statement.setInt(2,p.getId());
            PreparedStatement statement1 = connection.prepareStatement("select * from partnership where id = ?");
            statement1.setInt(1,p.getId());
            ResultSet resultSet = statement1.executeQuery();
            if (resultSet.next()) {
                if (p.getStatus() == null) {
                    statement.setString(1, resultSet.getString(4));
                } else {
                    statement.setString(1, p.getStatus());
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
    public void delete(Partnership u) {

    }

    @Override
    public List<Partnership> getAll() {
        return null;
    }
}
