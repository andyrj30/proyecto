package com.proyecto.controller;

import com.proyecto.entities.Subarea;
import com.proyecto.controller.util.JsfUtil;
import com.proyecto.model.SubareaFacadeLocal;
import com.proyecto.websocket.WSEndpoint;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJBException;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@javax.faces.bean.ManagedBean(name = "subareaController")
@javax.faces.bean.SessionScoped
public class SubareaController extends AbstractController implements Serializable {

    private Subarea selected;

    public SubareaController() {
    }

    private SubareaFacadeLocal getFacade() {
        return ejbSubarea;
    }

    public Subarea getSelected() {
        return selected;
    }

    public void setSelected(Subarea selected) {
        this.selected = selected;
    }

    public int count() {
        return getFacade().count();
    }

    public Subarea prepareCreate() {
        selected = new Subarea();
        return selected;
    }

    public void create() {
        try {
            getFacade().create(selected);
            JsfUtil.addSuccessMessage("Registro agregado correctamente");
            listSubarea = null;
            WSEndpoint.notificar("area");
        } catch (EJBException e) {
            JsfUtil.addErrorMessage(e, defaultMsg);
        }
    }

    public void update() {
        try {
            getFacade().edit(selected);
            JsfUtil.addSuccessMessage("Datos editados");
            listSubarea = null;
            WSEndpoint.notificar("area");
        } catch (EJBException e) {
            JsfUtil.addErrorMessage(e, defaultMsg);
        }
    }

    public void destroy() {
        try {
            getFacade().remove(selected);
            JsfUtil.addSuccessMessage("Registro eliminado correctamente");
            selected = null;
            listSubarea = null;
            WSEndpoint.notificar("area");
        } catch (EJBException e) {
            JsfUtil.addErrorMessage(e, defaultMsg);
        }
    }

    public List<Subarea> getItems() {
        listSubarea = getFacade().findAll();
        return listSubarea;
    }

    public Subarea getSubarea(Object id) {
        return getFacade().find(id);
    }

    @FacesConverter(forClass = Subarea.class)
    public static class SubareaControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            SubareaController controller = (SubareaController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "subareaController");
            return controller.getSubarea(getKey(value));
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
            if (object instanceof Subarea) {
                Subarea o = (Subarea) object;
                return getStringKey(o.getIdsubarea());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Subarea.class.getName()});
                return null;
            }
        }

    }

}
