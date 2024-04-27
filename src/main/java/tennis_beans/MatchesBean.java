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
import tennis_pojos.Match;

/**
 *
 * @author lisset
 */
@Named(value = "matchesBean")
@SessionScoped
public class MatchesBean implements Serializable {

   List<Match> matches;
   Date birth_date;
   
   @Inject
   TennisDBase<Match> dmm;
   @PostConstruct
   public void matchConstruct(){
       try{
          matches = this.dmm.listAll(); 
       }
       catch(Exception e){
           e.printStackTrace();
       }
   }

    public List<Match> getMatches() {
        return matches;
    }

    public void setMatches(List<Match> matches) {
        this.matches = matches;
    }

    public TennisDBase<Match> getDmm() {
        return dmm;
    }

    public void setDmp(TennisDBase<Match> dmm) {
        this.dmm = dmm;
    }
   
    public void create() {

        System.out.println("NEW MATCH CREATED");
        matches.add(new Match());
        
        String msg = "Created new Match. Dont forget to edit and save!";
        addMessage(msg,"no exception", FacesMessage.SEVERITY_INFO);
    }
    
    public void saveChanges() {
        //>find if supplier has been modified  
        // and update the modified supplier
        if (matches == null) {
            return;
        }

        int totalRowsUpdated = 0;
        for (Match m : this.matches) {

            System.out.println(m.toString());
            if (m.isModify()) {
                int rowsUpdated = 0;
                try {
                    if(m.isKeyModify())
                    {
                        System.out.println("Inserting " + m.toString());
                        rowsUpdated = this.dmm.insert(m);
                    }
                        rowsUpdated = this.dmm.update(m);
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
    public void delete(Match m){
        try 
        {
            System.out.println("Deleting: " + m.toString());
            this.dmm.delete(m);
            matches = this.dmm.listAll();
        } catch (SQLException ex) {
            String msg = ("number of rows deleted is 0. ");
            addMessage(msg, ex.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
        
    }
    public void delete()
    {
        int count = 0;
        if (matches == null)
            return;
        for (Match s : this.matches)
        {
            if (s.isModify())
            {
                try
                {
                    count = this.dmm.delete(s);
                }
                catch (SQLException e)
                {
                    addMessage("Cannot delete this match.", e.getMessage(), FacesMessage.SEVERITY_ERROR);
                }
            }
        }
        try
        {
            this.matches = dmm.listAll();
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
