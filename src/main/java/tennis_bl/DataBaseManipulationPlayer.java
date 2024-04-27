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
import tennis_pojos.Player;
import tennis_dbase.TennisDBase;

/**
 *
 * @author lisset
 */
public class DataBaseManipulationPlayer implements TennisDBase<Player>, Serializable {

    @Override
    public List<Player> listAll() throws SQLException {
        List<Player> tablePlayers = new ArrayList<>();
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
            String sqlStr = "SELECT  *  FROM players";
            //prepare statement
            ps = con.prepareStatement(sqlStr);
            //execute
            rs = ps.executeQuery();
            int row = 0;
            while (rs.next()) {
                int pNo = rs.getInt(1);
                String pName = rs.getString(2);
                String init = rs.getString(3);
                String bdate = rs.getDate(4) + " ";
                String sex = rs.getString(5);
                int joined = rs.getInt(6);
                String street = rs.getString(7);
                String house_no = rs.getString(8);
                String postcode = rs.getString(9);
                String town = rs.getString(10);
                String phone_no = rs.getString(11);
                String league_no = rs.getString(12);

                Player player = new Player(pNo, pName, init,
                        bdate, sex, joined, street, house_no, postcode,
                        town, phone_no, league_no);

                tablePlayers.add(player);
                row++;
            }
        } catch (Exception ex) {
            
            addMessage("Error", ex.getMessage(), FacesMessage.SEVERITY_ERROR);
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
        return tablePlayers;
    }

    @Override
    public int update(Player t) throws SQLException {
        String databaseName = "tennis23";
        String userName = "root";
        String password = "root";
        String URL2 = "com.mysql.jdbc.Driver";

//        if(true) throw new SQLException();
        Connection con = new UtilitiesDatabase().connection(
                databaseName, userName, password, URL2);
        int result = 0;
        PreparedStatement updatePlayer = null;
        try {
            if (con == null) {
                throw new RuntimeException("cannot connect to database");
            }

            updatePlayer = con.prepareStatement(
                    "UPDATE players SET player_no=?, name=?, initials=?, birth_date=?, sex=?, "
                    + "joined=?, street=?, house_no=?, postcode=?, town=?, phone_no=?, league_no=? "
                    + "WHERE player_no=?");
            updatePlayer.setInt(1, t.getPlayer_no());
            updatePlayer.setString(2, t.getName());
            updatePlayer.setString(3, t.getInitials());
            java.sql.Date bd = null;
            try {
                bd = UtilitiesDatabase.stringDateToSqlDate(t.getBirth_date());
            } catch (Exception e) {
                addMessage("Error", e.getMessage(), FacesMessage.SEVERITY_ERROR);
            }
            updatePlayer.setDate(4, bd);
            updatePlayer.setString(5, t.getSex());
            updatePlayer.setInt(6, t.getJoined());
            updatePlayer.setString(7, t.getStreet());
            updatePlayer.setString(8, t.getHouse_no());
            updatePlayer.setString(9, t.getPostcode());
            updatePlayer.setString(10, t.getTown());
            updatePlayer.setString(11, t.getPhone_no());
            updatePlayer.setString(12, t.getLeague_no());
            updatePlayer.setInt(13, t.getPlayer_no());

            ParameterMetaData pd = updatePlayer.getParameterMetaData();
            result = updatePlayer.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(ex.toString());
            addMessage("Error updating", ex.getMessage(), FacesMessage.SEVERITY_ERROR);
            throw ex;
        } finally {
            try {
                new UtilitiesDatabase().closeConnection(con);
                // close the resources 
                if (updatePlayer != null) {
                    updatePlayer.close();
                }
            } catch (SQLException sqle) {
                sqle.printStackTrace();
                addMessage("Error closing Database", sqle.getMessage(), FacesMessage.SEVERITY_ERROR);
                throw sqle;
            }
        }
        return result;
    }

    @Override
    public int delete(Player t) throws SQLException {

        String databaseName = "tennis23";
        String userName = "root";
        String password = "root";
        String URL2 = "com.mysql.jdbc.Driver";
        Connection con = new UtilitiesDatabase().connection(
                databaseName, userName, password, URL2);
        int result = 0;
        PreparedStatement delPlayer = null;
        try {
            if (con == null) {
                throw new RuntimeException("cannot connect to database");
            }

            delPlayer = con.prepareStatement(
                    "DELETE FROM players WHERE player_no=?");
            delPlayer.setInt(1, t.getPlayer_no());
            System.out.println("Delete: " + t.getPlayer_no());

            ParameterMetaData pmd = delPlayer.getParameterMetaData();
            result = delPlayer.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(ex.toString());
            addMessage("Error updating", ex.getMessage(), FacesMessage.SEVERITY_ERROR);
            throw ex;
        } finally {
            try {
                new UtilitiesDatabase().closeConnection(con);
                // close the resources 
                if (delPlayer != null) {
                    delPlayer.close();
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
    public int insert(Player t) throws SQLException {
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
        PreparedStatement updateSupplier = null;
        try {
            updateSupplier = con.prepareStatement(
                    "INSERT INTO players (player_no, name, initials, birth_date, sex, "
                    + " joined, street, house_no, postcode, town, phone_no, league_no) "
                    + "VALUES ( ?, ?, ?, ? ,?, ?, ?, ?, ?, ?, ?, ?)");
            updateSupplier.setInt(1, t.getPlayer_no());
            updateSupplier.setString(2, t.getName());
            updateSupplier.setString(3, t.getInitials());
            java.sql.Date sqlDate = UtilitiesDatabase.stringDateToSqlDate(t.getBirth_date());
            updateSupplier.setDate(4, sqlDate);
            updateSupplier.setString(5, t.getSex());
            updateSupplier.setInt(6, t.getJoined());
            updateSupplier.setString(7, t.getStreet());
            updateSupplier.setString(8, t.getHouse_no());
            updateSupplier.setString(9, t.getPostcode());
            updateSupplier.setString(10, t.getTown());
            updateSupplier.setString(11, t.getPhone_no());
            updateSupplier.setString(12, t.getLeague_no());

            int updateCount = updateSupplier.executeUpdate();

            result = updateCount;
        } catch (Exception ex) {
            System.err.println(ex.toString());
            addMessage("Player Number must be Unique!",
                    ex.getMessage(), FacesMessage.SEVERITY_ERROR);
        } finally {
            try {
                UtilitiesDatabase.closeConnection(con);
                // close the resources 
                if (updateSupplier != null) {
                    updateSupplier.close();
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
