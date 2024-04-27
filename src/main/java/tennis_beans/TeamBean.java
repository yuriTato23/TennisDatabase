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
import java.util.List;
import tennis_dbase.TennisDBase;
import tennis_pojos.Team;

/**
 *
 * @author lisset
 */
@Named(value = "teamBean")
@SessionScoped
public class TeamBean implements Serializable {

   
   List<Team> teams;
   
   @Inject
   TennisDBase<Team> dmt;
   @PostConstruct
   public void matchConstruct(){
       try{
          teams = this.dmt.listAll(); 
       }
       catch(Exception e){
           e.printStackTrace();
       }
   }

    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }

    public TennisDBase<Team> getDmt() {
        return dmt;
    }

    public void setDmt(TennisDBase<Team> dmt) {
        this.dmt = dmt;
    }
   
    public void create() {

        System.out.println("NEW TEAM CREATED");
        
        Team newT = new Team();
                teams.add(newT);
        System.out.println(newT.toString());
        
        String msg = "Created new Team. Dont forget to edit and save!";
        addMessage(msg,"no exception", FacesMessage.SEVERITY_INFO);
    }
    
    public void saveChanges() {
        //>find if supplier has been modified  
        // and update the modified supplier
        if (teams == null) {
            return;
        }

        int totalRowsUpdated = 0;
        for (Team m : this.teams) {

            System.out.println(m.toString());
            if (m.isModify()) {
                int rowsUpdated = 0;
                try {
                    if(m.isKeyModify())
                    {
                        System.out.println("Inserting " + m.toString());
                        rowsUpdated = this.dmt.insert(m);
                    }
                        rowsUpdated = this.dmt.update(m);
                    if (rowsUpdated > 0) {
//                        System.out.println(s);
                        totalRowsUpdated += rowsUpdated;
                        m.setModify(false);
                        m.setKeyModify(false);
                    }
                } catch (SQLException ex) {
                    String msg = ("number of rows updated is 0. ");
                    addMessage(msg, ex.getMessage(), FacesMessage.SEVERITY_ERROR);
                    break;
                }
            }
        }
        String msg = ("number of rows updated: " + totalRowsUpdated);
        addMessage(msg, "", FacesMessage.SEVERITY_INFO);

    }
    
    public void delete(Team t){
        try 
        {
            System.out.println("Deleting: " + t.toString());
            this.dmt.delete(t);
            teams = this.dmt.listAll();
        } catch (SQLException ex) {
            String msg = ("number of rows deleted is 0. ");
            addMessage(msg, ex.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
        
    }
    
    public void delete()
    {
        int count = 0;
        if (teams == null)
            return;
        for (Team s : this.teams)
        {
            if (s.isModify())
            {
                try
                {
                    count = this.dmt.delete(s);
                }
                catch (SQLException e)
                {
                    addMessage("Cannot delete this team.", e.getMessage(), FacesMessage.SEVERITY_ERROR);
                }
            }
        }
        try
        {
            this.teams = dmt.listAll();
        }
        catch (SQLException sql)
        {
            addMessage("Error connecting to database.", sql.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
        if (count == 0)
        {
            String msg = "Number of rows deleted: " + count;
                addMessage(msg, "", FacesMessage.SEVERITY_ERROR);
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
