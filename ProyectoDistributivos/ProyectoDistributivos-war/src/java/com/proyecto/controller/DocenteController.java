package com.proyecto.controller;

import com.proyecto.entities.Docente;
import com.proyecto.controller.util.JsfUtil;
import com.proyecto.controller.util.JsfUtil.PersistAction;
import com.proyecto.model.DocenteFacade;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@Named("docenteController")
@SessionScoped
public class DocenteController implements Serializable {

    @EJB
    private com.proyecto.model.DocenteFacade ejbFacade;
    private List<Docente> items = null;
    private Docente selected;

    public DocenteController() {
    }

    public Docente getSelected() {
        return selected;
    }

    public int count() {
        return ejbFacade.count();
    }

    public void setSelected(Docente selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private DocenteFacade getFacade() {
        return ejbFacade;
    }

    public Docente prepareCreate() {
        selected = new Docente();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("DocenteCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("DocenteUpdated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("DocenteDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }
    

    public List<Docente> getItems() {
        items = getFacade().findAll();
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != null) {
                    switch (persistAction) {
                        case DELETE:
                            getFacade().remove(selected);
                            break;
                        case CREATE:
                            getFacade().create(selected);
                            break;
                        default:
                            getFacade().edit(selected);
                            break;
                    }
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    public Docente getDocente(java.lang.String id) {
        return getFacade().find(id);
    }

    public List<Docente> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Docente> getItemsAvailableSelectOne() {
        return getFacade().findAll();

    }

    @FacesConverter(forClass = Docente.class)
    public static class DocenteControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            DocenteController controller = (DocenteController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "docenteController");
            return controller.getDocente(getKey(value));
        }

        java.lang.String getKey(String value) {
            java.lang.String key;
            key = value;
            return key;
        }

        String getStringKey(java.lang.String value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Docente) {
                Docente o = (Docente) object;
                return getStringKey(o.getCedula());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Docente.class.getName()});
                return null;
            }
        }

    }

}