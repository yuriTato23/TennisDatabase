/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tennis_bl;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import tennis_dbase.UtilitiesDatabase;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import tennis_dbase.TennisDBase;
import tennis_pojos.Committee;
import tennis_pojos.Penalty;

/**
 *
 * @author lisset
 */
public class DataBaseManipulationPenalties implements TennisDBase<Penalty>, Serializable {

    @Override
    public List<Penalty> listAll() throws SQLException {
        List<Penalty> tablePenalties = new ArrayList<>();
        String databaseName = "tennis23";
        String userName = "root";
        String password = "root";
        String URL2 = "com.mysql.jdbc.Driver";
        Connection con = new UtilitiesDatabase().connection(
                databaseName, userName, password, URL2);
        PreparedStatement ps = null;
        try {
            if (con == null) {
                throw new RuntimeException("cannot connect to database");
            }
            String table = "";
            ResultSet rs = null;
            String sqlStr = "SELECT  *  FROM penalties";
            //prepare statement
            ps = con.prepareStatement(sqlStr);
            //execute
            rs = ps.executeQuery();
            int row = 0;
            while (rs.next()) {
                int pay_no = rs.getInt(1);
                int player_no = rs.getInt(2);
                String pay_date = rs.getString(3);
                double amount = rs.getDouble(4);
                
                Penalty penalties = new Penalty(pay_no, player_no, pay_date,
                        amount);
                
                tablePenalties.add(penalties);
                row++;
            }
        } catch (RuntimeException | SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                new UtilitiesDatabase().closeConnection(con);
                // close the resources 
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException sqle) {
                System.out.println(sqle);
                sqle.printStackTrace();
                throw sqle;

            }
        }
        return tablePenalties;
    }

    @Override
    public int update(Penalty t) throws SQLException {
        String databaseName = "tennis23";
        String userName = "root";
        String password = "root";
        String URL2 = "com.mysql.jdbc.Driver";

//        if(true) throw new SQLException();
        Connection con = new UtilitiesDatabase().connection(
                databaseName, userName, password, URL2);
        int result = 0;
        PreparedStatement updatePenalties = null;
        try {
            if (con == null) {
                throw new RuntimeException("cannot connect to database");
            }

            updatePenalties = con.prepareStatement(
                    "UPDATE penalties SET payment_no=?, player_no=?, payment_date=?, amount=? "
                            + "WHERE payment_no=?");
            updatePenalties.setInt(1, t.getPayment_no());
            updatePenalties.setInt(2, t.getPlayer_no());
            java.sql.Date pd = null;
            try {
                pd = UtilitiesDatabase.stringDateToSqlDate(t.getPayment_date());
            } catch (Exception e) {
                addMessage("Date Error", e.getMessage(), FacesMessage.SEVERITY_ERROR);
            }
            updatePenalties.setDate(3, pd);
            updatePenalties.setDouble(4, t.getAmount());
            updatePenalties.setInt(5, t.getPayment_no());
            
            ParameterMetaData pmd = updatePenalties.getParameterMetaData();
            result = updatePenalties.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(ex.toString());
            throw ex;
        } finally {
            try {
                new UtilitiesDatabase().closeConnection(con);
                // close the resources 
                if (updatePenalties != null) {
                    updatePenalties.close();
                }
            } catch (SQLException sqle) {
                sqle.printStackTrace();
                throw sqle;
            }
        }
        return result;
    }

    @Override
    public int delete(Penalty t) throws SQLException {
        
        String databaseName = "tennis23";
        String userName = "root";
        String password = "root";
        String URL2 = "com.mysql.jdbc.Driver";
        Connection con = new UtilitiesDatabase().connection(
                databaseName, userName, password, URL2);
        int result = 0;
        PreparedStatement delPenalty = null;
        try {
            if (con == null) {
                throw new RuntimeException("cannot connect to database");
            }

            delPenalty = con.prepareStatement(
                    "DELETE FROM penalties WHERE payment_no=?");
            delPenalty.setInt(1, t.getPayment_no());

//            ParameterMetaData pmd = delPenalty.getParameterMetaData();
            result = delPenalty.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(ex.toString());
            throw ex;
        } finally {
            try {
                new UtilitiesDatabase().closeConnection(con);
                // close the resources 
                if (delPenalty != null) {
                    delPenalty.close();
                }
            } catch (SQLException sqle) {
                addMessage("Error", sqle.getMessage() ,FacesMessage.SEVERITY_ERROR);
                sqle.printStackTrace();
                throw sqle;
                
            }
        }
        return result;
    }

    @Override
    public int insert(Penalty t) throws SQLException {
        int result = 0;
        Connection con = UtilitiesDatabase.connection("tennis23", "root", "root", "com.mysql.jdbc.Driver");
        try {
            if (con == null) {
                throw new RuntimeException("I cannot connect to the database.");
            }
        } 
        catch (RuntimeException e) 
        {
            System.err.println(e);
            addMessage("Cannot connect to Database", e.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
        PreparedStatement insertCommittee = null;
        try {
            insertCommittee = con.prepareStatement(
                    "INSERT INTO penalties (payment_no, player_no, payment_date, amount) "
                    + "VALUES( ?, ?, ?, ?)");
            insertCommittee.setInt(1, t.getPayment_no());
            insertCommittee.setInt(2, t.getPlayer_no());
            java.sql.Date sqlBeginDate = UtilitiesDatabase.stringDateToSqlDate(t.getPayment_date());
            insertCommittee.setDate(3, sqlBeginDate);
            insertCommittee.setDouble(4, t.getAmount());
            
            int updateCount = insertCommittee.executeUpdate();
            
            result = updateCount;
        } catch (SQLException ex) {
            addMessage("Payment Number must be Unique! Player Number must exist in Player table" ,
                        ex.getMessage() ,FacesMessage.SEVERITY_ERROR);
            System.err.println(ex.toString());
        } finally {
            try {
                UtilitiesDatabase.closeConnection(con);
                // close the resources 
                if (insertCommittee != null) {
                    insertCommittee.close();
                }

            } catch (SQLException e) {
                System.err.println(e.toString());
                            addMessage(String.valueOf(e.getErrorCode()),e.toString(),FacesMessage.SEVERITY_ERROR);

            }
        }
        return result;
    }

    public void addMessage(String summary, String detail, FacesMessage.Severity severity) {
        FacesMessage msg = new FacesMessage(severity, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
}
