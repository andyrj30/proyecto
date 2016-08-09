package com.proyecto.validation;
 
import com.proyecto.controller.util.JsfUtil;
import java.util.Map;
import java.util.regex.Pattern;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import org.primefaces.validate.ClientValidator;
 
/**
 * Custom JSF Validator for Email input
 */
@FacesValidator("custom.letrasValidator")
public class LetrasValidator implements Validator, ClientValidator {
 
    private Pattern pattern;
  
    private static final String LETRAS_PATTERN = "^[A-Za-z]";
  
    public LetrasValidator() {
        pattern = Pattern.compile(LETRAS_PATTERN);
    }
 
    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        if(value == null) {
            return;
        }
         
        if(!pattern.matcher(value.toString()).matches()) {
            JsfUtil.addErrorMessage("ingrese solo letras");
        }
    }
 
    @Override
    public Map<String, Object> getMetadata() {
        return null;
    }
 
    @Override
    public String getValidatorId() {
        return "custom.letrasValidator";
    }
     
}