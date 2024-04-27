package languages;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */

import jakarta.inject.Named;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.component.UIViewRoot;
import jakarta.faces.context.FacesContext;
import java.io.Serializable;
import java.util.Locale;

/**
 *
 * @author Lisset Ripoll
 */
@Named(value="languages")
@SessionScoped
public class Languages implements Serializable{

    /** Creates a new instance of Languages */
    public Languages() {
        
    }
    public void setEnglish() {
        UIViewRoot viewRoot = FacesContext.getCurrentInstance().getViewRoot();
        viewRoot.setLocale(new Locale("en"));
    }

    public void setSpanish() {
        UIViewRoot viewRoot = FacesContext.getCurrentInstance().getViewRoot();
        viewRoot.setLocale(new Locale("es"));
    }
    public void setRussian() {
        UIViewRoot viewRoot = FacesContext.getCurrentInstance().getViewRoot();
        viewRoot.setLocale(new Locale("ru"));
    }
    public void setGreek(){
        UIViewRoot viewRoot = FacesContext.getCurrentInstance().getViewRoot();
        viewRoot.setLocale(new Locale("el"));
    }

}
