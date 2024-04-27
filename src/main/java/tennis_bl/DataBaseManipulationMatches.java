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
import tennis_pojos.Match;

/**
 *
 * @author lisset
 */
public class DataBaseManipulationMatches implements TennisDBase<Match>, Serializable {

    @Override
    public List<Match> listAll() throws SQLException {
        List<Match> tableMatches = new ArrayList<>();
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
            String sqlStr = "SELECT  *  FROM matches";
            //prepare statement
            ps = con.prepareStatement(sqlStr);
            //execute
            rs = ps.executeQuery();
            int row = 0;
            while (rs.next()) {
                int match_no = rs.getInt(1);
                int team_no = rs.getInt(2);
                int player_no = rs.getInt(3);
                int won = rs.getInt(4);
                int lost = rs.getInt(5);

                Match matches = new Match(match_no, team_no, player_no,
                        won, lost);
                
                tableMatches.add(matches);
                row++;
            }
        } catch (Exception ex) {
            addMessage("Error", ex.getMessage(), FacesMessage.SEVERITY_ERROR);
            ex.printStackTrace();
        } finally {
            try {
                new UtilitiesDatabase().closeConnection(con);
                // close the resources 
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException sqle) {
                addMessage("Error", sqle.getMessage(), FacesMessage.SEVERITY_ERROR);
                System.out.println(sqle);
                sqle.printStackTrace();
                throw sqle;

            }
        }
        return tableMatches;
    }

    @Override
    public int update(Match t) throws SQLException {
        String databaseName = "tennis23";
        String userName = "root";
        String password = "root";
        String URL2 = "com.mysql.jdbc.Driver";

//        if(true) throw new SQLException();
        Connection con = new UtilitiesDatabase().connection(
                databaseName, userName, password, URL2);
        int result = 0;
        PreparedStatement updateMatches = null;
        try {
            if (con == null) {
                throw new RuntimeException("cannot connect to database");
            }

            updateMatches = con.prepareStatement(
                    "UPDATE matches SET match_no=?, team_no=?, player_no=?, won=?, lost=? "
                            + "WHERE match_no=?");
            updateMatches.setInt(1, t.getMatch_no());
            updateMatches.setInt(2, t.getTeam_no());
            updateMatches.setInt(3, t.getPlayer_no());
            updateMatches.setInt(4, t.getWon());
            updateMatches.setInt(5, t.getLost());
            updateMatches.setInt(6, t.getMatch_no());
            
            ParameterMetaData pd = updateMatches.getParameterMetaData();
            result = updateMatches.executeUpdate();
        } catch (SQLException ex) {
            addMessage("Error Updating", ex.getMessage(), FacesMessage.SEVERITY_ERROR);
            System.err.println(ex.toString());
            throw ex;
        } finally {
            try {
                new UtilitiesDatabase().closeConnection(con);
                // close the resources 
                if (updateMatches != null) {
                    updateMatches.close();
                }
            } catch (SQLException sqle) {
                sqle.printStackTrace();
                addMessage("Error", sqle.getMessage(), FacesMessage.SEVERITY_ERROR);
                throw sqle;
            }
        }
        return result;
    }

    @Override
    public int delete(Match t) throws SQLException {
        
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
                    "DELETE FROM matches WHERE match_no=?");
            delMatch.setInt(1, t.getMatch_no());

            ParameterMetaData pmd = delMatch.getParameterMetaData();
            result = delMatch.executeUpdate();
        } catch (SQLException ex) {
            addMessage("Error Deleting", ex.getMessage(), FacesMessage.SEVERITY_ERROR);
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
                addMessage("Error", sqle.getMessage(), FacesMessage.SEVERITY_ERROR);
                sqle.printStackTrace();
                throw sqle;
            }
        }
        return result;
    }

    @Override
    public int insert(Match t) throws SQLException {
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
        PreparedStatement insertMatches = null;
        try {
            insertMatches = con.prepareStatement(
                    "INSERT INTO matches (match_no, team_no, player_no, won, lost) "
                    + "VALUES( ?, ?, ?, ?, ?)");
            insertMatches.setInt(1, t.getMatch_no());
            insertMatches.setInt(2, t.getTeam_no());
            insertMatches.setInt(3, t.getPlayer_no());
            insertMatches.setInt(4, t.getWon());
            insertMatches.setInt(5, t.getLost());
            
            int updateCount = insertMatches.executeUpdate();

            result = updateCount;
        } catch (Exception ex) {
            System.err.println(ex.toString());
            addMessage("Match_no cannot be duplicate. Player_no must exist in Player table. "
                    + "Team_no must exist in Teams table", "", FacesMessage.SEVERITY_ERROR);
        } finally {
            try {
                UtilitiesDatabase.closeConnection(con);
                // close the resources 
                if (insertMatches != null) {
                    insertMatches.close();
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
