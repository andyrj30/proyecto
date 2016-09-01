package com.proyecto.controller;

import com.proyecto.entities.Otrasactividades;
import com.proyecto.controller.util.JsfUtil;
import com.proyecto.model.OtrasactividadesFacadeLocal;

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

@javax.faces.bean.ManagedBean(name = "otrasactividadesController")
@javax.faces.bean.SessionScoped
public class OtrasactividadesController implements Serializable {

    @EJB
    protected OtrasactividadesFacadeLocal ejbOtrasactividades;

    private Otrasactividades selected;
    protected List<Otrasactividades> listOtrasactividades;
    protected String defaultMsg = "Ha ocurrido un error en la transacción";

    public OtrasactividadesController() {
    }

    private OtrasactividadesFacadeLocal getFacade() {
        return ejbOtrasactividades;
    }

    public Otrasactividades getSelected() {
        return selected;
    }

    public void setSelected(Otrasactividades selected) {
        this.selected = selected;
    }

    public int count() {
        return getFacade().count();
    }

    public Otrasactividades prepareCreate() {
        selected = new Otrasactividades();
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

    public List<Otrasactividades> getItems() {
        listOtrasactividades = getFacade().findAll();
        return listOtrasactividades;
    }

    public Otrasactividades getOtrasactividades(Object id) {
        return getFacade().find(id);
    }

    @FacesConverter(forClass = Otrasactividades.class)
    public static class OtrasactividadesControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            OtrasactividadesController controller = (OtrasactividadesController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "distributivoController");
            return controller.getOtrasactividades(getKey(value));
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
            if (object instanceof Otrasactividades) {
                Otrasactividades o = (Otrasactividades) object;
                return getStringKey(o.getIdotrasactividades());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Otrasactividades.class.getName()});
                return null;
            }
        }

    }

}
