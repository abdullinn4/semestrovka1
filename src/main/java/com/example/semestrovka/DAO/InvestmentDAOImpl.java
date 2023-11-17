package com.example.semestrovka.DAO;

import com.example.semestrovka.helpers.DataBaseConnection;
import com.example.semestrovka.models.Company;
import com.example.semestrovka.models.Investment;
import com.example.semestrovka.models.Partnership;
import com.example.semestrovka.models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InvestmentDAOImpl implements DAO<Investment> {
    public static void save(Investment investment){
        try(Connection connection = DataBaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement("insert into investment (investor_id, recipient_id, status, amount, investment_date,title) values (?,?,?,?,?,?)")){
            Company initiatorCompany = CompanyDAOImpl.getCompanyFromDB(new Company(investment.getInvestorName()));
            Company partnerCompany = CompanyDAOImpl.getCompanyFromDB(new Company(investment.getRecipientName()));
            if (initiatorCompany != null && partnerCompany!= null){
                statement.setInt(1,initiatorCompany.getId());
                statement.setInt(2,partnerCompany.getId());
                statement.setString(3,"В ожидании");
                statement.setString(4,investment.getAmount() );
                statement.setDate(5,investment.getInvestmentDate());
                statement.setString(6, investment.getTitle());
                statement.executeUpdate();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        setIdInvestment(investment);
    }
    private static void setIdInvestment(Investment investment){
        try{
            Connection connection = DataBaseConnection.getConnection();
            try{
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("select * from partnership");
                while(resultSet.next()){
                    int idInvestment = resultSet.getInt(1);
                    int idInvestorDB = resultSet.getInt(2);
                    int idRecipientDb = resultSet.getInt(3);
                    Company initiatorCompany = CompanyDAOImpl.getCompanyFromDB(new Company(investment.getInvestorName()));
                    Company partnerCompany = CompanyDAOImpl.getCompanyFromDB(new Company(investment.getRecipientName()));
                    if (idInvestorDB == initiatorCompany.getId() && idRecipientDb == partnerCompany.getId()){
                        investment.setId(idInvestment);
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
    public static List<Investment> getAllOutgoingApplications(User user){
        CompanyDAOImpl companyDAO = new CompanyDAOImpl();
        List<Investment> investments = new ArrayList<>();
        List<Company> companies = CompanyDAOImpl.getAllUserCompaniesFromDB(user);
        try{
            Connection connection = DataBaseConnection.getConnection();
            try{
                PreparedStatement statement = connection.prepareStatement("select * from investment where investor_id = ?");
                for (Company company : companies){
                    statement.setInt(1,company.getId());
                    ResultSet resultSet = statement.executeQuery();
                    while (resultSet.next()){
                        Company c = companyDAO.get(resultSet.getInt(3));
                        Investment investment = new Investment(
                                resultSet.getInt(1),
                                company.getName(),
                                c.getName(),
                                resultSet.getString(4),
                                resultSet.getString(5),
                                resultSet.getDate(6),
                                resultSet.getString(7)
                        );
                        investments.add(investment);
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
        return investments;
    }
    public static List<Investment> getAllIncomingApplications(User user){
        CompanyDAOImpl companyDAO = new CompanyDAOImpl();
        List<Investment> investments = new ArrayList<>();
        List<Company> companies = CompanyDAOImpl.getAllUserCompaniesFromDB(user);
        try{
            Connection connection = DataBaseConnection.getConnection();
            try{
                PreparedStatement statement = connection.prepareStatement("select * from investment where recipient_id = ?");
                for (Company company : companies){
                    statement.setInt(1,company.getId());
                    ResultSet resultSet = statement.executeQuery();
                    while (resultSet.next()){
                        Company c = companyDAO.get(resultSet.getInt(2));
                        Investment investment = new Investment(
                                resultSet.getInt(1),
                                c.getName(),
                                company.getName(),
                                resultSet.getString(4),
                                resultSet.getString(5),
                                resultSet.getDate(6),
                                resultSet.getString(7)
                        );
                        investments.add(investment);
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
        return investments;
    }
    public static List<Investment> getAllInvestments(User user) {
        CompanyDAOImpl companyDAO = new CompanyDAOImpl();
        List<Investment> investments = new ArrayList<>();
        List<Company> companies = CompanyDAOImpl.getAllUserCompaniesFromDB(user);
        try {
            Connection connection = DataBaseConnection.getConnection();
            try {
                PreparedStatement statement = connection.prepareStatement("select * from investment where (investor_id = ? or recipient_id = ?) and status = ?");
                for (Company company : companies) {
                    statement.setInt(1, company.getId());
                    statement.setInt(2, company.getId());
                    statement.setString(3, "Достигнуто");
                    ResultSet resultSet = statement.executeQuery();
                    while (resultSet.next()) {
                        Company c1 = companyDAO.get(resultSet.getInt(2));
                        Company c2 = companyDAO.get(resultSet.getInt(3));
                        Investment investment = new Investment(
                                resultSet.getInt(1),
                                c1.getName(),
                                c2.getName(),
                                resultSet.getString(4),
                                resultSet.getString(5),
                                resultSet.getDate(6),
                                resultSet.getString(7)
                        );
                        investments.add(investment);
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
        return investments;
    }
    public static String getStatusById(int id) {
        String status = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DataBaseConnection.getConnection();
            String sql = "select status from investment where id = ?";
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
    @Override
    public void create(Investment u) {

    }

    @Override
    public Investment get(long id) {
        CompanyDAOImpl companyDAO = new CompanyDAOImpl();
        try{
            PreparedStatement statement = DataBaseConnection.getConnection().prepareStatement("select * from investment where id = ? ");
            statement.setLong(1,id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()){
                Company initiator = companyDAO.get(resultSet.getInt(2));
                Company partner = companyDAO.get(resultSet.getInt(3));
                Investment i = new Investment(
                        resultSet.getInt(1),
                        initiator.getName(),
                        partner.getName(),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getDate(6),
                        resultSet.getString(7)
                );
                return i;
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
    public void update(Investment i) {
        try(Connection connection = DataBaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement
                    ("update investment set status = ? where id = ? ")){
            statement.setInt(2,i.getId());
            PreparedStatement statement1 = connection.prepareStatement("select * from investment where id = ?");
            statement1.setInt(1,i.getId());
            ResultSet resultSet = statement1.executeQuery();
            if (resultSet.next()) {
                if (i.getStatus() == null) {
                    statement.setString(1, resultSet.getString(5));
                } else {
                    statement.setString(1, i.getStatus());
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
    public void delete(Investment u) {

    }

    @Override
    public List<Investment> getAll() {
        return null;
    }
}
