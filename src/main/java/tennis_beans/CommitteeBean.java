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
import tennis_pojos.Committee;

/**
 *
 * @author lisset
 */
@Named(value = "committeeBean")
@SessionScoped
public class CommitteeBean implements Serializable {

   List<Committee> committee;
   
   @Inject
   TennisDBase<Committee> dmc;
   @PostConstruct
   public void committeeConstruct(){
       try{
          committee = this.dmc.listAll(); 
       }
       catch(RuntimeException | SQLException e){
           addMessage(e.getLocalizedMessage(),"", FacesMessage.SEVERITY_FATAL);
           e.printStackTrace();
       }
   }

    public List<Committee> getCommittee() {
        return committee;
    }

    public void setCommittee(List<Committee> committee) {
        this.committee = committee;
    }

    public TennisDBase<Committee> getDmc() {
        return dmc;
    }

    public void setDmc(TennisDBase<Committee> dmc) {
        this.dmc = dmc;
    }
    
    public void create() {

      try{
        System.out.println("Ne Commitee Created");
        committee.add(new Committee());
        
        String msg = "Created new Committee. Dont forget to edit and save!";
        addMessage(msg,"no exception", FacesMessage.SEVERITY_INFO);
        }
        catch(Exception e){
            String msg = "Could not create a new entry";
        addMessage(msg,e.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }
    
    public void saveChanges() {
        if (committee == null) {
            return;
        }

        int totalRowsUpdated = 0;
        for (Committee c : this.committee) {

            System.out.println(c.getPlayer_no());
            if (c.isModify()) {
                int rowsUpdated = 0;
                try {
                    if(c.isKeyModify())
                    {
                        System.out.println("Inserting " + c.toString());
                        rowsUpdated = this.dmc.insert(c);
                    }
                        rowsUpdated = this.dmc.update(c);
                    if (rowsUpdated > 0) {
//                        System.out.println(s);
                        totalRowsUpdated += rowsUpdated;
                        c.setModify(false);
                        c.setKeyModify(false);
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
    
    public void delete(Committee t){
        try 
        {
            System.out.println("Deleting: " + t.toString());
            this.dmc.delete(t);
            committee = this.dmc.listAll();
        } catch (SQLException ex) {
            String msg = ("number of rows deleted is 0. ");
            addMessage(msg, ex.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
        
    }
    public void delete()
    {
        
        int count = 0;
        if (committee == null)
            return;
        try{
        for (Committee s : this.committee)
        {
            if (s.isModify())
            {
                try
                {
                    count = this.dmc.delete(s);
                }
                catch (SQLException e)
                {
                    addMessage("Cannot delete this supplier.", e.getMessage(), FacesMessage.SEVERITY_ERROR);
                }
            }
        }
        try
        {
            this.committee = dmc.listAll();
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
        catch(Exception e)
        {
                String msg = "Number of rows deleted: " + count;
                addMessage(msg, e.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }
    public void addMessage(String summary, String detail, FacesMessage.Severity severity) {
        FacesMessage msg = new FacesMessage(severity, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
}
