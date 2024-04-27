/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package validators;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.validator.FacesValidator;
import jakarta.faces.validator.Validator;
import jakarta.faces.validator.ValidatorException;

/**
 *
 * @author lisset
 */
@FacesValidator("userValidate")
public class UsernameValidator implements Validator{

    @Override
    public void validate(FacesContext fc, UIComponent uic, Object t) throws ValidatorException {
        String username = t.toString();
       if(username.length() < 8)
           throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Username minimum length is 8", null));
       if (!username.matches("^[a-zA-Z0-9.&]*$")) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Username is alphanumeric and can only contain '.' and '&' symbols", null));
        }
    }
}
