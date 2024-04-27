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

/**
 *
 * @author lisset
 */
public class DataBaseManipulationCommittee implements TennisDBase<Committee>, Serializable {

    @Override
    public List<Committee> listAll() throws SQLException {
        List<Committee> tableCommittee = new ArrayList<>();
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
            String sqlStr = "SELECT  *  FROM committee";
            //prepare statement
            ps = con.prepareStatement(sqlStr);
            //execute
            rs = ps.executeQuery();
            int row = 0;
            while (rs.next()) {
                int player_no = rs.getInt(1);
                String begin_date = rs.getString(2);
                String end_date = rs.getString(3);
                String position = rs.getString(4);
                
                Committee committee = new Committee(player_no, begin_date, end_date,
                        position);
                
                tableCommittee.add(committee);
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
        return tableCommittee;
    }

    @Override
    public int update(Committee t) throws SQLException {
        String databaseName = "tennis23";
        String userName = "root";
        String password = "root";
        String URL2 = "com.mysql.jdbc.Driver";

//        if(true) throw new SQLException();
        Connection con = new UtilitiesDatabase().connection(
                databaseName, userName, password, URL2);
        int result = 0;
        PreparedStatement updateCommittee = null;
        try {
            if (con == null) {
                throw new RuntimeException("cannot connect to database");
            }
            
            updateCommittee = con.prepareStatement(
                    "UPDATE committee SET player_no=?, begin_date=?, end_date=?, position=? "
                            + "WHERE player_no=? AND begin_date=?");
            updateCommittee.setInt(1, t.getPlayer_no());
            java.sql.Date bd = null;
            try {
                bd = UtilitiesDatabase.stringDateToSqlDate(t.getBegin_date());
            } catch (Exception e) {
                e.getMessage();
            }
            updateCommittee.setDate(2, bd);
            java.sql.Date ed = null;
            try {
                ed = UtilitiesDatabase.stringDateToSqlDate(t.getEnd_date());
            } catch (Exception e) {
                e.getMessage();
            }
            updateCommittee.setDate(3, ed);
            updateCommittee.setString(4, t.getPosition());
            updateCommittee.setInt(5, t.getPlayer_no());
            updateCommittee.setDate(6, bd);
            
            ParameterMetaData pd = updateCommittee.getParameterMetaData();
            result = updateCommittee.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(ex.toString());
            throw ex;
        } finally {
            try {
                new UtilitiesDatabase().closeConnection(con);
                // close the resources 
                if (updateCommittee != null) {
                    updateCommittee.close();
                }
            } catch (SQLException sqle) {
                sqle.printStackTrace();
                throw sqle;
            }
        }
        return result;
    }

    @Override
    public int delete(Committee t) throws SQLException {
        
        String databaseName = "tennis23";
        String userName = "root";
        String password = "root";
        String URL2 = "com.mysql.jdbc.Driver";
        Connection con = new UtilitiesDatabase().connection(
                databaseName, userName, password, URL2);
        int result = 0;
        PreparedStatement delMatch = null;
        try {
            if (con == null) {
                throw new RuntimeException("cannot connect to database");
            }

            delMatch = con.prepareStatement(
                    "DELETE FROM committee WHERE player_no=? AND begin_date=?");
            delMatch.setInt(1, t.getPlayer_no());
            delMatch.setString(2, t.getBegin_date());
            
            ParameterMetaData pmd = delMatch.getParameterMetaData();
            result = delMatch.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(ex.toString());
            throw ex;
        } finally {
            try {
                new UtilitiesDatabase().closeConnection(con);
                // close the resources 
                if (delMatch != null) {
                    delMatch.close();
                }
            } catch (SQLException sqle) {
                sqle.printStackTrace();
                throw sqle;
            }
        }
        return result;
    }

    @Override
    public int insert(Committee t) throws SQLException {
        int result = 0;
        Connection con = UtilitiesDatabase.connection("tennis23", "root", "root", "com.mysql.jdbc.Driver");
        try {
            if (con == null) {
                throw new RuntimeException("I cannot connect to the database.");
            }
        } catch (RuntimeException e) {
            System.err.println(e);
            addMessage("Error", e.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
        PreparedStatement insertCommittee = null;
        try {
            insertCommittee = con.prepareStatement(
                    "INSERT INTO committee (player_no, begin_date, end_date, position) "
                    + "VALUES( ?, ?, ?, ?)");
            insertCommittee.setInt(1, t.getPlayer_no());
            java.sql.Date sqlBeginDate = UtilitiesDatabase.stringDateToSqlDate(t.getBegin_date());
            insertCommittee.setDate(2, sqlBeginDate);
            java.sql.Date sqlEndDate = UtilitiesDatabase.stringDateToSqlDate(t.getEnd_date());
            insertCommittee.setDate(3, sqlEndDate);
            insertCommittee.setString(4, t.getPosition());
            
            int updateCount = insertCommittee.executeUpdate();

            result = updateCount;
        } catch (SQLException ex) {
            System.err.println(ex.toString());
            addMessage("Error", ex.getMessage(), FacesMessage.SEVERITY_ERROR);
        } finally {
            try {
                UtilitiesDatabase.closeConnection(con);
                // close the resources 
                if (insertCommittee != null) {
                    insertCommittee.close();
                }

            } catch (SQLException e) {
                System.err.println(e.toString());
                addMessage(e.toString(), e.getMessage() ,FacesMessage.SEVERITY_ERROR);
            }
        }
        return result;
    }

    public void addMessage(String summary, String detail, FacesMessage.Severity severity) {
        FacesMessage msg = new FacesMessage(severity, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
}
