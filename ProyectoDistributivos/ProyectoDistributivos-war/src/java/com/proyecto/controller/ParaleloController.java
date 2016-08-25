package com.proyecto.controller;

import com.proyecto.entities.Paralelo;
import com.proyecto.controller.util.JsfUtil;
import com.proyecto.model.ParaleloFacade;
import com.proyecto.model.ParaleloFacadeLocal;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJBException;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@javax.faces.bean.ManagedBean(name = "paraleloController")
@javax.faces.bean.SessionScoped
public class ParaleloController extends AbstractController implements Serializable {

    private Paralelo selected;

    public ParaleloController() {
    }

    private ParaleloFacadeLocal getFacade() {
        return ejbParalelo;
    }

    public Paralelo getSelected() {
        return selected;
    }

    public void setSelected(Paralelo selected) {
        this.selected = selected;
    }

    public int count() {
        return getFacade().count();
    }

    public Paralelo prepareCreate() {
        selected = new Paralelo();
        return selected;
    }

    public void create() {
        try {
            getFacade().create(selected);
            JsfUtil.addSuccessMessage("Registro agregado correctamente");
            listParalelo = null;
        } catch (EJBException e) {
            JsfUtil.addErrorMessage(e, defaultMsg);
        }
    }

    public void update() {
        try {
            getFacade().edit(selected);
            JsfUtil.addSuccessMessage("Datos editados");
            listParalelo = null;
        } catch (EJBException e) {
            JsfUtil.addErrorMessage(e, defaultMsg);
        }
    }

    public void destroy() {
        try {
            getFacade().remove(selected);
            JsfUtil.addSuccessMessage("Registro eliminado correctamente");
            selected = null;
            listParalelo = null;
        } catch (EJBException e) {
            JsfUtil.addErrorMessage(e, defaultMsg);
        }
    }

    public List<Paralelo> getItems() {
        listParalelo = getFacade().findAll();
        return listParalelo;
    }

    public Paralelo getParalelo(Object id) {
        return getFacade().find(id);
    }

    @FacesConverter(forClass = Paralelo.class)
    public static class ParaleloControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ParaleloController controller = (ParaleloController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "paraleloController");
            return controller.getParalelo(getKey(value));
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
            if (object instanceof Paralelo) {
                Paralelo o = (Paralelo) object;
                return getStringKey(o.getCodparalelo());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Paralelo.class.getName()});
                return null;
            }
        }

    }

}
