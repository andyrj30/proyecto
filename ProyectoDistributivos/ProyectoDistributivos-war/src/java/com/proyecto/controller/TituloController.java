package com.proyecto.controller;

import com.proyecto.entities.Titulo;
import com.proyecto.controller.util.JsfUtil;
import com.proyecto.model.TituloFacade;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJBException;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@javax.faces.bean.ManagedBean(name = "tituloController")
@javax.faces.bean.SessionScoped
public class TituloController extends AbstractController implements Serializable {

    private Titulo selected;

    public TituloController() {
    }

    private TituloFacade getFacade() {
        return ejbTitulo;
    }

    public Titulo getSelected() {
        return selected;
    }

    public void setSelected(Titulo selected) {
        this.selected = selected;
    }

    public int count() {
        return getFacade().count();
    }

    public Titulo prepareCreate() {
        selected = new Titulo();
        return selected;
    }

    public void create() {
        try {
            getFacade().create(selected);
            JsfUtil.addSuccessMessage("Registro agregado correctamente");
            listTitulo = null;
        } catch (EJBException e) {
            JsfUtil.addErrorMessage(e, defaultMsg);
        }
    }

    public void update() {
        try {
            getFacade().edit(selected);
            JsfUtil.addSuccessMessage("Datos editados");
            listTitulo = null;
        } catch (EJBException e) {
            JsfUtil.addErrorMessage(e, defaultMsg);
        }
    }

    public void destroy() {
        try {
            getFacade().remove(selected);
            JsfUtil.addSuccessMessage("Registro eliminado correctamente");
            selected = null;
            listTitulo = null;
        } catch (EJBException e) {
            JsfUtil.addErrorMessage(e, defaultMsg);
        }
    }

    public List<Titulo> getItems() {
        if (listTitulo == null) {
            listTitulo = getFacade().findAll();
        }
        return listTitulo;
    }

    public Titulo getTitulo(Object id) {
        return getFacade().find(id);
    }

    @FacesConverter(forClass = Titulo.class)
    public static class TituloControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            TituloController controller = (TituloController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "tituloController");
            return controller.getTitulo(getKey(value));
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
            if (object instanceof Titulo) {
                Titulo o = (Titulo) object;
                return getStringKey(o.getIdtitulo());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Titulo.class.getName()});
                return null;
            }
        }

    }

}
