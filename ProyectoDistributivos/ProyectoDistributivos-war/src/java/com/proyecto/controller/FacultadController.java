package com.proyecto.controller;

import com.proyecto.entities.Facultad;
import com.proyecto.controller.util.JsfUtil;
import com.proyecto.model.FacultadFacade;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJBException;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@javax.faces.bean.ManagedBean(name = "facultadController")
@javax.faces.bean.SessionScoped
public class FacultadController extends AbstractController implements Serializable {

    private Facultad selected;

    public FacultadController() {
    }

    private FacultadFacade getFacade() {
        return ejbFacultad;
    }

    public Facultad getSelected() {
        return selected;
    }

    public void setSelected(Facultad selected) {
        this.selected = selected;
    }

    public int count() {
        return getFacade().count();
    }

    public Facultad prepareCreate() {
        selected = new Facultad();
        return selected;
    }

    public void create() {
        try {
            getFacade().create(selected);
            JsfUtil.addSuccessMessage("Registro agregado correctamente.");
            listFacultad = null;
        } catch (EJBException e) {
            JsfUtil.addErrorMessage(e, defaultMsg);
        }
    }

    public void update() {
        try {
            getFacade().edit(selected);
            JsfUtil.addSuccessMessage("Datos editados.");
            listFacultad = null;
        } catch (EJBException e) {
            JsfUtil.addErrorMessage(e, defaultMsg);
        }
    }

    public void destroy() {
        try {
            getFacade().remove(selected);
            JsfUtil.addSuccessMessage("Registro eliminado correctamente.");
            selected = null;
            listFacultad = null;
        } catch (EJBException e) {
            JsfUtil.addErrorMessage(e, defaultMsg);
        }
    }

    public List<Facultad> getItems() {
        if (listFacultad == null) {
            listFacultad = getFacade().findAll();
        }
        return listFacultad;
    }

    public Facultad getFacultad(Object id) {
        return getFacade().find(id);
    }

    @FacesConverter(forClass = Facultad.class)
    public static class FacultadControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            FacultadController controller = (FacultadController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "facultadController");
            return controller.getFacultad(getKey(value));
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
            if (object instanceof Facultad) {
                Facultad o = (Facultad) object;
                return getStringKey(o.getIdfacultad());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Facultad.class.getName()});
                return null;
            }
        }

    }

}
