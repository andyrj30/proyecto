package com.proyecto.controller;

import com.proyecto.entities.Aula;
import com.proyecto.controller.util.JsfUtil;
import com.proyecto.controller.util.JsfUtil.PersistAction;
import com.proyecto.model.AulaFacade;

import java.io.Serializable;
import java.util.ArrayList;
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


@javax.faces.bean.ManagedBean(name = "aulaController")
@javax.faces.bean.SessionScoped
public class AulaController implements Serializable {

    @EJB
    private com.proyecto.model.AulaFacade ejbFacade;
    private List<Aula> items = null;
    private List<String> edificios = null;
    private Aula selected;

    public AulaController() {
        edificios = new ArrayList<>();
        edificios.add("Instituto de Informtica");
        edificios.add("Facultad de Ciencias de la Ingenieria");
    }

    public int count() {
        return ejbFacade.count();
    }

    public List<String> getEdificios() {
        return edificios;
    }

    public void setEdificios(List<String> edificios) {
        this.edificios = edificios;
    }

    public Aula getSelected() {
        return selected;
    }

    public void setSelected(Aula selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private AulaFacade getFacade() {
        return ejbFacade;
    }

    public Aula prepareCreate() {
        selected = new Aula();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("AulaCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("AulaUpdated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("AulaDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Aula> getItems() {
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

    public Aula getAula(java.lang.String id) {
        return getFacade().find(id);
    }

    public List<Aula> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Aula> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Aula.class)
    public static class AulaControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            AulaController controller = (AulaController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "aulaController");
            return controller.getAula(getKey(value));
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
            if (object instanceof Aula) {
                Aula o = (Aula) object;
                return getStringKey(o.getCodaula());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Aula.class.getName()});
                return null;
            }
        }

    }

}
