package com.proyecto.controller;

import com.proyecto.entities.Distributivoaula;
import com.proyecto.controller.util.JsfUtil;
import com.proyecto.controller.util.JsfUtil.PersistAction;
import com.proyecto.model.DistributivoaulaFacade;

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

@Named("distributivoaulaController")
@SessionScoped
public class DistributivoaulaController implements Serializable {

    @EJB
    private com.proyecto.model.DistributivoaulaFacade ejbFacade;
    private List<Distributivoaula> items = null;
    private Distributivoaula selected;

    public DistributivoaulaController() {
    }

    public Distributivoaula getSelected() {
        return selected;
    }

    public int count() {
        return ejbFacade.count();
    }

    public void setSelected(Distributivoaula selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private DistributivoaulaFacade getFacade() {
        return ejbFacade;
    }

    public Distributivoaula prepareCreate() {
        selected = new Distributivoaula();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("DistributivoaulaCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("DistributivoaulaUpdated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }
    

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("DistributivoaulaDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Distributivoaula> getItems() {
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

    public Distributivoaula getDistributivoaula(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Distributivoaula> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Distributivoaula> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Distributivoaula.class)
    public static class DistributivoaulaControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            DistributivoaulaController controller = (DistributivoaulaController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "distributivoaulaController");
            return controller.getDistributivoaula(getKey(value));
        }

        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Integer value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Distributivoaula) {
                Distributivoaula o = (Distributivoaula) object;
                return getStringKey(o.getIddistributivoaula());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Distributivoaula.class.getName()});
                return null;
            }
        }

    }

}
