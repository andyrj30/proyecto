package com.proyecto.controller;

import com.proyecto.entities.Distributivoaula;
import com.proyecto.controller.util.JsfUtil;
import com.proyecto.model.DistributivoaulaFacade;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJBException;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@javax.faces.bean.ManagedBean(name = "distributivoaulaController")
@javax.faces.bean.SessionScoped
public class DistributivoaulaController extends AbstractController implements Serializable {

    private Distributivoaula selected;

    public DistributivoaulaController() {
    }

    private DistributivoaulaFacade getFacade() {
        return ejbDistributivoaula;
    }

    public Distributivoaula getSelected() {
        return selected;
    }

    public void setSelected(Distributivoaula selected) {
        this.selected = selected;
    }

    public int count() {
        return getFacade().count();
    }

    public Distributivoaula prepareCreate() {
        selected = new Distributivoaula();
        return selected;
    }

    public void create() {
        try {
            getFacade().create(selected);
            JsfUtil.addSuccessMessage("Registro agregado correctamente");
            listDistributivoaula = null;
        } catch (EJBException e) {
            JsfUtil.addErrorMessage(e, defaultMsg);
        }
    }

    public void update() {
        try {
            getFacade().edit(selected);
            JsfUtil.addSuccessMessage("Datos editados");
            listDistributivoaula = null;
        } catch (EJBException e) {
            JsfUtil.addErrorMessage(e, defaultMsg);
        }
    }

    public void destroy() {
        try {
            getFacade().remove(selected);
            JsfUtil.addSuccessMessage("Registro eliminado correctamente");
            selected = null;
            listDistributivoaula = null;
        } catch (EJBException e) {
            JsfUtil.addErrorMessage(e, defaultMsg);
        }
    }

    public List<Distributivoaula> getItems() {
        if (listDistributivoaula == null) {
            listDistributivoaula = getFacade().findAll();
        }
        return listDistributivoaula;
    }

    public List<String> getEdificios() {
        return listEdificio;
    }

    public Distributivoaula getDistributivoaula(Object id) {
        return getFacade().find(id);
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
