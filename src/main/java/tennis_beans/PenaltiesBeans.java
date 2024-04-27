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
import tennis_pojos.Penalty;

/**
 *
 * @author lisset
 */
@Named(value = "penaltiesBean")
@SessionScoped
public class PenaltiesBeans implements Serializable {

   List<Penalty> penalties;
   Date payment_date;
   
   
   @Inject
   TennisDBase<Penalty> dmp;
   @PostConstruct
   public void committeeConstruct(){
       try{
          penalties = this.dmp.listAll(); 
       }
       catch(SQLException e){
           e.printStackTrace();
           addMessage(String.valueOf(e.getErrorCode()), e.getMessage(), FacesMessage.SEVERITY_ERROR);
       }
   }

    public TennisDBase<Penalty> getDmc() {
        return dmp;
    }

    public void setDmc(TennisDBase<Penalty> dmc) {
        this.dmp = dmc;
    }

    public List<Penalty> getPenalties() {
        return penalties;
    }

    public void setPenalties(List<Penalty> penalties) {
        this.penalties = penalties;
    }
    
    
    public void create() {

        try
        {
            System.out.println("NEW PENALTY CREATED");
            penalties.add(new Penalty());

            String msg = "Created new Penalty. Dont forget to edit and save!";
            addMessage(msg,"", FacesMessage.SEVERITY_INFO);
        }
        catch(Exception e){
                addMessage("Error creating new Penalty", e.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }
    
    public void saveChanges() {
        //>find if supplier has been modified  
        // and update the modified supplier
        if (penalties == null) {
            return;
        }

        int totalRowsUpdated = 0;
        for (Penalty t : this.penalties) {

            System.out.println(t.getPlayer_no());
            if (t.isModify()) {
                int rowsUpdated = 0;
                try {
                    if(t.isKeyModify())
                    {
                        System.out.println("Inserting " + t.toString());
                        rowsUpdated = this.dmp.insert(t);
                    }
                        rowsUpdated = this.dmp.update(t);
                    if (rowsUpdated > 0) {
//                        System.out.println(s);
                        totalRowsUpdated += rowsUpdated;
                        t.setModify(false);
                      
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
    
    public void delete(Penalty t){
        try 
        {
            System.out.println("Deleting: " + t.toString());
            this.dmp.delete(t);
            penalties = this.dmp.listAll();
        } catch (SQLException ex) {
            String msg = ("number of rows deleted is 0. ");
            addMessage(msg, ex.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
        
    }
    public void delete()
    {
        int count = 0;
        if (penalties == null)
            return;
        for (Penalty s : this.penalties)
        {
            if (s.isModify())
            {
                try
                {
                    count = this.dmp.delete(s);
                }
                catch (SQLException e)
                {
                    addMessage("Cannot delete this penalty.", e.getMessage(), FacesMessage.SEVERITY_ERROR);
                }
            }
        }
        try
        {
            this.penalties = dmp.listAll();
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
