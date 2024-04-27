/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package tennis_beans;

import jakarta.annotation.PostConstruct;
import jakarta.inject.Named;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import tennis_dbase.TennisDBase;
import tennis_pojos.Player;

/**
 *
 * @author lisset
 */
@Named(value = "playerBean")
@SessionScoped
public class PlayerBean implements Serializable {

   List<Player> players;
   
   @Inject
   TennisDBase<Player> dmp;
   @PostConstruct
   public void playerConstruct(){
       try{
          players = this.dmp.listAll(); 
       }
       catch(Exception e){
           e.printStackTrace();
       }
   }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public TennisDBase<Player> getDmp() {
        return dmp;
    }

    public void setDmp(TennisDBase<Player> dmp) {
        this.dmp = dmp;
    }
   
    public void create() {

        try{
        System.out.println("NEW PLAYER CREATED");
        players.add(new Player());
        
        String msg = "Created new PLayer. Dont forget to edit and save!";
        addMessage(msg,"no exception", FacesMessage.SEVERITY_INFO);
        }
        catch(Exception e){
            String msg = "Could not create a new entry";
        addMessage(msg,e.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }
    
    public void saveChanges() {
        //>find if supplier has been modified  
        // and update the modified supplier
        if (players == null) {
            return;
        }

        int totalRowsUpdated = 0;
        for (Player p : this.players) {

            System.out.println(p.getPlayer_no());
            if (p.isModify()) {
                int rowsUpdated = 0;
                try {
                    if(p.isKeyModify())
                    {
                        System.out.println("Inserting " + p.toString());
                        rowsUpdated = this.dmp.insert(p);
                    }
                        rowsUpdated = this.dmp.update(p);
                    if (rowsUpdated > 0) {
//                        System.out.println(s);
                        totalRowsUpdated += rowsUpdated;
                        p.setModify(false);
                      
                    }
                } catch ( RuntimeException | SQLException ex) {
                    String msg = ("number of rows updated is 0. ");
                    addMessage(ex.getMessage(), ex.getMessage(), FacesMessage.SEVERITY_ERROR);
                    break;
                }
            }
        }
        String msg = ("number of rows updated: " + totalRowsUpdated);
        addMessage(msg, "no exception", FacesMessage.SEVERITY_INFO);

    }
    public void delete(Player t){
        try 
        {
            if(t.isModify()){
            System.out.println("Deleting: " + t.toString());
            System.out.println("Delete: "+ t.getPlayer_no());
            this.dmp.delete(t);
            players = this.dmp.listAll();     
            }
        } catch (SQLException ex) {
            String msg = ("number of rows deleted is 0. ");
            addMessage(msg, ex.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
        
    }
    public void deletePlayer()
    {
        int count = 0;
        if (players == null)
            return;
        for (Player t : this.players)
        {
            if (t.isModify())
            {
                try
                {
                    count = this.dmp.delete(t);
                }
                catch (SQLException e)
                {
                    addMessage("Cannot delete this player.", e.getMessage(), FacesMessage.SEVERITY_ERROR);
                }
            }
        }
        try
        {
            this.players = dmp.listAll();
        }
        catch (SQLException sql)
        {
            addMessage("Error connecting to database.", sql.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
        if (count == 0)
        {
            String msg = "Number of rows deleted: " + count;
                addMessage(msg, "no exception", FacesMessage.SEVERITY_ERROR);
        }
        else
        {
            String msg = "Number of rows deleted: " + count;
                addMessage(msg, "no exception", FacesMessage.SEVERITY_INFO);
        }
    }
    public void addMessage(String summary, String detail, FacesMessage.Severity severity) {
        FacesMessage msg = new FacesMessage(severity, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
}
