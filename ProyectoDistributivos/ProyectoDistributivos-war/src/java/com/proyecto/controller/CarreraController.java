package com.proyecto.controller;

import com.proyecto.entities.Carrera;
import com.proyecto.controller.util.JsfUtil;
import com.proyecto.controller.util.JsfUtil.PersistAction;
import com.proyecto.entities.Semestre;
import com.proyecto.model.CarreraFacade;

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

@Named("carreraController")
@SessionScoped
public class CarreraController implements Serializable {

    @EJB
    private com.proyecto.model.CarreraFacade ejbFacade;
    @EJB
    private com.proyecto.model.SemestreFacade semestreFacade;
    private List<Carrera> items = null;
    private Carrera selected;
    private Semestre semestre;

    public CarreraController() {
    }

    public Carrera getSelected() {
        return selected;
    }

    public int count() {
        return ejbFacade.count();
    }

    public void setSelected(Carrera selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private CarreraFacade getFacade() {
        return ejbFacade;
    }

    public Carrera prepareCreate() {
        selected = new Carrera();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("CarreraCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("CarreraUpdated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("CarreraDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Carrera> getItems() {
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
                            semestre = new Semestre();
                            semestre.setIdsemestre(25);
                            semestre.setIdcarrera(selected);
                            semestre.setSemestre("Primer Semestre");
                            semestreFacade.create(semestre);
                            semestre.setSemestre("Segundo Semestre");
                            semestreFacade.create(semestre);
                            semestre.setSemestre("Tercero Semestre");
                            semestreFacade.create(semestre);
                            semestre.setSemestre("Cuarto Semestre");
                            semestreFacade.create(semestre);
                            semestre.setSemestre("Quinto Semestre");
                            semestreFacade.create(semestre);
                            semestre.setSemestre("Sexto Semestre");
                            semestreFacade.create(semestre);
                            semestre.setSemestre("Septimo Semestre");
                            semestreFacade.create(semestre);
                            semestre.setSemestre("Octavo Semestre");
                            semestreFacade.create(semestre);
                            semestre.setSemestre("Noveno Semestre");
                            semestreFacade.create(semestre);
                            semestre.setSemestre("Decimo Semestre");
                            semestreFacade.create(semestre);
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

    public Carrera getCarrera(java.lang.String id) {
        return getFacade().find(id);
    }

    public List<Carrera> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Carrera> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Carrera.class)
    public static class CarreraControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            CarreraController controller = (CarreraController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "carreraController");
            return controller.getCarrera(getKey(value));
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
            if (object instanceof Carrera) {
                Carrera o = (Carrera) object;
                return getStringKey(o.getIdcarrera());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Carrera.class.getName()});
                return null;
            }
        }

    }

}