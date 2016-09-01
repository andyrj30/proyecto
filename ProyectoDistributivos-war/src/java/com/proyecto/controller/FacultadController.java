package com.proyecto.controller;

import com.proyecto.entities.Facultad;
import com.proyecto.controller.util.JsfUtil;
import com.proyecto.model.FacultadFacade;
import com.proyecto.model.FacultadFacadeLocal;
import com.proyecto.websocket.WSEndpoint;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@javax.faces.bean.ManagedBean(name = "facultadController")
@javax.faces.bean.SessionScoped
public class FacultadController implements Serializable {

    @EJB
    protected FacultadFacadeLocal ejbFacultad;

    private Facultad selected;
    protected List<Facultad> listFacultad;
    protected String defaultMsg = "Ha ocurrido un error en la transacción";

    public FacultadController() {
    }

    private FacultadFacadeLocal getFacade() {
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
            WSEndpoint.notificar("facultad");
        } catch (EJBException e) {
            JsfUtil.addErrorMessage(e, defaultMsg);
        }
    }

    public void update() {
        try {
            getFacade().edit(selected);
            JsfUtil.addSuccessMessage("Datos editados.");
            listFacultad = null;
            WSEndpoint.notificar("facultad");
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
            WSEndpoint.notificar("facultad");
        } catch (EJBException e) {
            JsfUtil.addErrorMessage(e, defaultMsg);
        }
    }

    public List<Facultad> getItems() {
        listFacultad = getFacade().findAll();
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
                return getStringKey(o.getCodfacultad());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Facultad.class.getName()});
                return null;
            }
        }

    }

}
