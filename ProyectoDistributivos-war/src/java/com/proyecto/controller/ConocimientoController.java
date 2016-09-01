package com.proyecto.controller;

import com.proyecto.entities.Conocimiento;
import com.proyecto.controller.util.JsfUtil;
import com.proyecto.model.ConocimientoFacadeLocal;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@ManagedBean(name = "conocimientoController")
@SessionScoped
public class ConocimientoController implements Serializable {
    
    @EJB
    protected ConocimientoFacadeLocal ejbConocimiento;

    private Conocimiento selected;
    protected List<Conocimiento> listConocimiento;
    protected String defaultMsg = "Ha ocurrido un error en la transacción";

    public ConocimientoController() {
    }

    private ConocimientoFacadeLocal getFacade() {
        return ejbConocimiento;
    }

    public Conocimiento getSelected() {
        return selected;
    }

    public void setSelected(Conocimiento selected) {
        this.selected = selected;
    }

    public int count() {
        return getFacade().count();
    }

    public Conocimiento prepareCreate() {
        selected = new Conocimiento();
        return selected;
    }

    public void create() {
        try {
            getFacade().create(selected);
            JsfUtil.addSuccessMessage("Registro agregado correctamente");
            listConocimiento = null;
        } catch (EJBException e) {
            JsfUtil.addErrorMessage(e, defaultMsg);
        }
    }

    public void update() {
        try {
            getFacade().edit(selected);
            JsfUtil.addSuccessMessage("Datos editados");
            listConocimiento = null;
        } catch (EJBException e) {
            JsfUtil.addErrorMessage(e, defaultMsg);
        }
    }

    public void destroy() {
        try {
            getFacade().remove(selected);
            JsfUtil.addSuccessMessage("Registro eliminado correctamente");
            selected = null;
            listConocimiento = null;
        } catch (EJBException e) {
            JsfUtil.addErrorMessage(e, defaultMsg);
        }
    }

    public List<Conocimiento> getItems() {
        listConocimiento = getFacade().findAll();
        return listConocimiento;
    }

    public Conocimiento getConocimiento(Object id) {
        return getFacade().find(id);
    }

    @FacesConverter(forClass = Conocimiento.class)
    public static class ConocimientoControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ConocimientoController controller = (ConocimientoController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "conocimientoController");
            return controller.getConocimiento(getKey(value));
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
            if (object instanceof Conocimiento) {
                Conocimiento o = (Conocimiento) object;
                return getStringKey(o.getIdconocimiento());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Conocimiento.class.getName()});
                return null;
            }
        }

    }

}
