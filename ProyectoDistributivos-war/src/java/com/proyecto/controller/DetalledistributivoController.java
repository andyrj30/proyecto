package com.proyecto.controller;

import com.proyecto.entities.Detalledistributivo;
import com.proyecto.controller.util.JsfUtil;
import com.proyecto.model.DetalledistributivoFacadeLocal;

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

@javax.faces.bean.ManagedBean(name = "detalledistributivoController")
@javax.faces.bean.SessionScoped
public class DetalledistributivoController implements Serializable {

    @EJB
    protected DetalledistributivoFacadeLocal ejbDetalledistributivo;

    private Detalledistributivo selected;
    protected List<Detalledistributivo> listDetalledistributivo;
    protected String defaultMsg = "Ha ocurrido un error en la transacción";

    public DetalledistributivoController() {
    }

    private DetalledistributivoFacadeLocal getFacade() {
        return ejbDetalledistributivo;
    }

    public Detalledistributivo getSelected() {
        return selected;
    }

    public void setSelected(Detalledistributivo selected) {
        this.selected = selected;
    }

    public int count() {
        return getFacade().count();
    }

    public Detalledistributivo prepareCreate() {
        selected = new Detalledistributivo();
        return selected;
    }

    public void create() {
        try {
            getFacade().create(selected);
            JsfUtil.addSuccessMessage("Registro agregado correctamente");
        } catch (EJBException e) {
            JsfUtil.addErrorMessage(e, defaultMsg);
        }
    }

    public void update() {
        try {
            getFacade().edit(selected);
            JsfUtil.addSuccessMessage("Datos editados");
        } catch (EJBException e) {
            JsfUtil.addErrorMessage(e, defaultMsg);
        }
    }

    public void destroy() {
        try {
            getFacade().remove(selected);
            selected = null;
            JsfUtil.addSuccessMessage("Registro eliminado correctamente");
        } catch (EJBException e) {
            JsfUtil.addErrorMessage(e, defaultMsg);
        }
    }

    public List<Detalledistributivo> getItems() {
        listDetalledistributivo = getFacade().findAll();
        return listDetalledistributivo;
    }

    public Detalledistributivo getDetalledistributivo(Object id) {
        return getFacade().find(id);
    }

    @FacesConverter(forClass = Detalledistributivo.class)
    public static class DetalledistributivoControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            DetalledistributivoController controller = (DetalledistributivoController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "distributivoController");
            return controller.getDetalledistributivo(getKey(value));
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
            if (object instanceof Detalledistributivo) {
                Detalledistributivo o = (Detalledistributivo) object;
                return getStringKey(o.getIddetalledistributivo());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Detalledistributivo.class.getName()});
                return null;
            }
        }

    }

}
