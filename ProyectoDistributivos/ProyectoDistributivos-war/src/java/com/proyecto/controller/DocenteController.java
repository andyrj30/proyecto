package com.proyecto.controller;

import com.proyecto.entities.Docente;
import com.proyecto.controller.util.JsfUtil;
import com.proyecto.model.DocenteFacade;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJBException;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@javax.faces.bean.ManagedBean(name = "docenteController")
@javax.faces.bean.SessionScoped
public class DocenteController extends AbstractController implements Serializable {

    private Docente selected;

    public DocenteController() {
    }

    private DocenteFacade getFacade() {
        return ejbDocente;
    }

    public Docente getSelected() {
        return selected;
    }

    public void setSelected(Docente selected) {
        this.selected = selected;
    }

    public int count() {
        return getFacade().count();
    }

    public Docente prepareCreate() {
        selected = new Docente();
        return selected;
    }

    public void create() {
        try {
            getFacade().create(selected);
            JsfUtil.addSuccessMessage("Registro agregado correctamente");
            listDocente = null;
        } catch (EJBException e) {
            JsfUtil.addErrorMessage(e, defaultMsg);
        }
    }

    public void update() {
        try {
            getFacade().edit(selected);
            JsfUtil.addSuccessMessage("Datos editados");
            listDocente = null;
        } catch (EJBException e) {
            JsfUtil.addErrorMessage(e, defaultMsg);
        }
    }

    public void destroy() {
        try {
            getFacade().remove(selected);
            JsfUtil.addSuccessMessage("Registro eliminado correctamente");
            selected = null;
            listDocente = null;
        } catch (EJBException e) {
            JsfUtil.addErrorMessage(e, defaultMsg);
        }
    }

    public List<Docente> getItems() {
        if (listDocente == null) {
            listDocente = getFacade().findAll();
        }
        return listDocente;
    }

    public Docente getDocente(Object id) {
        return getFacade().find(id);
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
