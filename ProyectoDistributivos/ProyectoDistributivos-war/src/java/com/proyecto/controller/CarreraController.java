package com.proyecto.controller;

import com.proyecto.entities.Carrera;
import com.proyecto.controller.util.JsfUtil;
import com.proyecto.model.CarreraFacade;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJBException;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@javax.faces.bean.ManagedBean(name = "carreraController")
@javax.faces.bean.SessionScoped
public class CarreraController extends AbstractController implements Serializable {

    private Carrera selected;

    public CarreraController() {
    }

    private CarreraFacade getFacade() {
        return ejbCarrera;
    }

    public Carrera getSelected() {
        return selected;
    }

    public void setSelected(Carrera selected) {
        this.selected = selected;
    }

    public int count() {
        return getFacade().count();
    }

    public Carrera prepareCreate() {
        selected = new Carrera();
        return selected;
    }

    public void create() {
        try {
            getFacade().create(selected);
            JsfUtil.addSuccessMessage("Registro agregado correctamente");
            listCarrera = null;
        } catch (EJBException e) {
            JsfUtil.addErrorMessage(e, defaultMsg);
        }
    }

    public void update() {
        try {
            getFacade().edit(selected);
            JsfUtil.addSuccessMessage("Datos editados");
            listCarrera = null;
        } catch (EJBException e) {
            JsfUtil.addErrorMessage(e, defaultMsg);
        }
    }

    public void destroy() {
        try {
            getFacade().remove(selected);
            JsfUtil.addSuccessMessage("Registro eliminado correctamente");
            selected = null;
            listCarrera = null;
        } catch (EJBException e) {
            JsfUtil.addErrorMessage(e, defaultMsg);
        }
    }

    public List<Carrera> getItems() {
        if (listCarrera == null) {
            listCarrera = getFacade().findAll();
        }
        return listCarrera;
    }

    public Carrera getCarrera(Object id) {
        return getFacade().find(id);
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
