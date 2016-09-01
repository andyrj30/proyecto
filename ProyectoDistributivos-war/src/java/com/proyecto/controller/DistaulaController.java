package com.proyecto.controller;

import com.proyecto.controller.util.JsfUtil;
import com.proyecto.entities.Distaula;
import com.proyecto.entities.Edificio;
import com.proyecto.entities.Periodo;
import com.proyecto.model.DistaulaFacadeLocal;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.bean.ManagedBean;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Andy
 */
@ManagedBean(name = "distaulaController")
@SessionScoped
public class DistaulaController implements Serializable {

    @EJB
    protected DistaulaFacadeLocal ejbDistaula;

    private Distaula selected;
    private Edificio edificio;
    private Periodo periodo;
    protected List<Distaula> listDistaula;
    protected String defaultMsg = "Ha ocurrido un error en la transacción";
    
    public DistaulaController() {
    }

    private DistaulaFacadeLocal getFacade() {
        return ejbDistaula;
    }

    public Distaula getSelected() {
        return selected;
    }

    public void setSelected(Distaula selected) {
        this.selected = selected;
    }

    public Edificio getEdificio() {
        return edificio;
    }

    public void setEdificio(Edificio edificio) {
        this.edificio = edificio;
    }

    public Periodo getPeriodo() {
        return periodo;
    }

    public void setPeriodo(Periodo periodo) {
        this.periodo = periodo;
    }

    public int count() {
        return getFacade().count();
    }

    public Distaula prepareCreate() {
        selected = new Distaula();
        return selected;
    }

    public void create() {
        try {
            getFacade().create(selected);
            JsfUtil.addSuccessMessage("Registro agregado correctamente");
            listDistaula = null;
        } catch (EJBException e) {
            JsfUtil.addErrorMessage(e, defaultMsg);
        }
    }

    public void update() {
        try {
            getFacade().edit(selected);
            JsfUtil.addSuccessMessage("Datos editados");
            listDistaula = null;
        } catch (EJBException e) {
            JsfUtil.addErrorMessage(e, defaultMsg);
        }
    }

    public void destroy() {
        try {
            getFacade().remove(selected);
            JsfUtil.addSuccessMessage("Registro eliminado correctamente");
            selected = null;
            listDistaula = null;
        } catch (EJBException e) {
            JsfUtil.addErrorMessage(e, defaultMsg);
        }
    }

    public List<Distaula> getItems() {
        listDistaula = getFacade().findAll();
        return listDistaula;
    }

    public Distaula getDistaula(Object id) {
        return getFacade().find(id);
    }

    @FacesConverter(forClass = Distaula.class)
    public static class DistaulaControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            DistaulaController controller = (DistaulaController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "distaulaController");
            return controller.getDistaula(getKey(value));
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
            if (object instanceof Distaula) {
                Distaula o = (Distaula) object;
                return getStringKey(o.getIddistributivoaula());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Distaula.class.getName()});
                return null;
            }
        }

    }

}
