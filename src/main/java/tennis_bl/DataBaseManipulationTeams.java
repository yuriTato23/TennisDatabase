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
import tennis_pojos.Team;

/**
 *
 * @author lisset
 */
public class DataBaseManipulationTeams implements TennisDBase<Team>, Serializable {

    @Override
    public List<Team> listAll() throws SQLException {
        List<Team> tableTeams = new ArrayList<>();
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
            String sqlStr = "SELECT  *  FROM teams";
            //prepare statement
            ps = con.prepareStatement(sqlStr);
            //execute
            rs = ps.executeQuery();
            int row = 0;
            while (rs.next()) {
                int team_no = rs.getInt(1);
                int player_no = rs.getInt(2);
                String division = rs.getString(3);

                Team teams = new Team(team_no, player_no, division);
                
                tableTeams.add(teams);
                row++;
            }
        } catch (Exception ex) {
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
        return tableTeams;
    }

    @Override
    public int update(Team t) throws SQLException {
        String databaseName = "tennis23";
        String userName = "root";
        String password = "root";
        String URL2 = "com.mysql.jdbc.Driver";

//        if(true) throw new SQLException();
        Connection con = new UtilitiesDatabase().connection(
                databaseName, userName, password, URL2);
        int result = 0;
        PreparedStatement updateTeams = null;
        try {
            if (con == null) {
                throw new RuntimeException("cannot connect to database");
            }

            updateTeams = con.prepareStatement(
                    "UPDATE teams SET team_no=?, player_no=?, division=? "
                            + "WHERE team_no=?");
            updateTeams.setInt(1, t.getTeam_no());
            updateTeams.setInt(2, t.getPlayer_no());
            updateTeams.setString(3, t.getDivision());
            updateTeams.setInt(4, t.getTeam_no());
            
            ParameterMetaData pd = updateTeams.getParameterMetaData();
            result = updateTeams.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(ex.toString());
            throw ex;
        } finally {
            try {
                new UtilitiesDatabase().closeConnection(con);
                // close the resources 
                if (updateTeams != null) {
                    updateTeams.close();
                }
            } catch (SQLException sqle) {
                sqle.printStackTrace();
                throw sqle;
            }
        }
        return result;
    }

    @Override
    public int delete(Team t) throws SQLException {
        
        String databaseName = "tennis23";
        String userName = "root";
        String password = "root";
        String URL2 = "com.mysql.jdbc.Driver";
        Connection con = new UtilitiesDatabase().connection(
                databaseName, userName, password, URL2);
        int result = 0;
        PreparedStatement delTeam = null;
        try {
            if (con == null) {
                throw new RuntimeException("cannot connect to database");
            }

            delTeam = con.prepareStatement(
                    "DELETE FROM teams WHERE team_no=?");
            delTeam.setInt(1, t.getTeam_no());

            ParameterMetaData pmd = delTeam.getParameterMetaData();
            result = delTeam.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(ex.toString());
            addMessage("Error updating", ex.getMessage(), FacesMessage.SEVERITY_ERROR);
            throw ex;
        } finally {
            try {
                new UtilitiesDatabase().closeConnection(con);
                // close the resources 
                if (delTeam != null) {
                    delTeam.close();
                }
            } catch (SQLException sqle) {
                sqle.printStackTrace();
                addMessage("Cannot close Database", sqle.getMessage(), FacesMessage.SEVERITY_ERROR);
                throw sqle;
            }
        }
        return result;
    }

    @Override
    public int insert(Team t) throws SQLException {
        int result = 0;
        Connection con = UtilitiesDatabase.connection("tennis23", "root", "root", "com.mysql.jdbc.Driver");
        try {
            if (con == null) {
                throw new RuntimeException("I cannot connect to the database.");
            }
        } catch (RuntimeException e) {
            System.err.println(e);
            addMessage("Cannot connect to Database", e.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
        PreparedStatement insertTeam = null;
        try {
            insertTeam = con.prepareStatement(
                    "INSERT INTO teams (team_no, player_no, division) "
                    + "VALUES( ?, ?, ?)");
            insertTeam.setInt(1, t.getTeam_no());
            insertTeam.setInt(2, t.getPlayer_no());
            insertTeam.setString(3, t.getDivision());
            
            int updateCount = insertTeam.executeUpdate();

            result = updateCount;
        } catch (SQLException ex) {
            System.err.println(ex.toString());
            addMessage("Team number must be Unique. Player Number must exist in Player Table!",
                    "", FacesMessage.SEVERITY_ERROR);
        } finally {
            try {
                UtilitiesDatabase.closeConnection(con);
                // close the resources 
                if (insertTeam != null) {
                    insertTeam.close();
                }

            } catch (SQLException e) {
                System.err.println(e.toString());
                addMessage(String.valueOf(e.getErrorCode()), e.toString(), FacesMessage.SEVERITY_ERROR);
            }
        }
        return result;
    }
    
    public void addMessage(String summary, String detail, FacesMessage.Severity severity) {
        FacesMessage msg = new FacesMessage(severity, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
}
