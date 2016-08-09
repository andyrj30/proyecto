package com.proyecto.controller;

import com.proyecto.entities.Semestre;
import com.proyecto.controller.util.JsfUtil;
import com.proyecto.controller.util.JsfUtil.PersistAction;
import com.proyecto.model.SemestreFacade;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@Named("semestreController")
@SessionScoped
public class SemestreController implements Serializable {

    @EJB
    private com.proyecto.model.SemestreFacade ejbFacade;
    private List<Semestre> items = null;
    private Semestre selected;

    public SemestreController() {
    }

    public Semestre getSelected() {
        return selected;
    }

    public void setSelected(Semestre selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private SemestreFacade getFacade() {
        return ejbFacade;
    }

    public Semestre prepareCreate() {
        selected = new Semestre();
        initializeEmbeddableKey();
        return selected;
    }

    public int count() {
        return ejbFacade.count();
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("SemestreCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("SemestreUpdated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }


    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("SemestreDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Semestre> getItems() {
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
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    public Semestre getSemestre(java.lang.String id) {
        return getFacade().find(id);
    }

    public List<Semestre> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Semestre> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Semestre.class)
    public static class SemestreControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            SemestreController controller = (SemestreController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "semestreController");
            return controller.getSemestre(getKey(value));
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
            if (object instanceof Semestre) {
                Semestre o = (Semestre) object;
                return getStringKey(o.getIdsemestre().toString());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Semestre.class.getName()});
                return null;
            }
        }

    }

}