package com.proyecto.controller;

import com.proyecto.entities.Distributivo;
import com.proyecto.controller.util.JsfUtil;
import com.proyecto.model.DistributivoFacadeLocal;

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

@javax.faces.bean.ManagedBean(name = "distributivoController")
@javax.faces.bean.SessionScoped
public class DistributivoController implements Serializable {

    @EJB
    protected DistributivoFacadeLocal ejbDistributivo;

    private Distributivo selected;
    protected List<Distributivo> listDistributivo;
    protected String defaultMsg = "Ha ocurrido un error en la transacción";

    public DistributivoController() {
    }

    private DistributivoFacadeLocal getFacade() {
        return ejbDistributivo;
    }

    public Distributivo getSelected() {
        return selected;
    }

    public void setSelected(Distributivo selected) {
        this.selected = selected;
    }

    public int count() {
        return getFacade().count();
    }

    public Distributivo prepareCreate() {
        selected = new Distributivo();
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

    public List<Distributivo> getItems() {
        listDistributivo = getFacade().findAll();
        return listDistributivo;
    }

    public Distributivo getDistributivo(Object id) {
        return getFacade().find(id);
    }

    @FacesConverter(forClass = Distributivo.class)
    public static class DistributivoControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            DistributivoController controller = (DistributivoController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "distributivoController");
            return controller.getDistributivo(getKey(value));
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
            if (object instanceof Distributivo) {
                Distributivo o = (Distributivo) object;
                return getStringKey(o.getIddistributivo());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Distributivo.class.getName()});
                return null;
            }
        }

    }

}
