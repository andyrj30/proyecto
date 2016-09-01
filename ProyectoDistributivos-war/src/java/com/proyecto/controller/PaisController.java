package com.proyecto.controller;

import com.proyecto.entities.Pais;
import com.proyecto.controller.util.JsfUtil;
import com.proyecto.model.PaisFacadeLocal;

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

@javax.faces.bean.ManagedBean(name = "paisController")
@javax.faces.bean.SessionScoped
public class PaisController implements Serializable {

    @EJB
    protected PaisFacadeLocal ejbPais;

    private Pais selected;
    protected List<Pais> listPais;
    protected String defaultMsg = "Ha ocurrido un error en la transacción";

    public PaisController() {
    }

    private PaisFacadeLocal getFacade() {
        return ejbPais;
    }

    public Pais getSelected() {
        return selected;
    }

    public void setSelected(Pais selected) {
        this.selected = selected;
    }

    public int count() {
        return getFacade().count();
    }

    public Pais prepareCreate() {
        selected = new Pais();
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

    public List<Pais> getItems() {
        listPais = getFacade().findAll();
        return listPais;
    }

    public Pais getPais(Object id) {
        return getFacade().find(id);
    }

    @FacesConverter(forClass = Pais.class)
    public static class PaisControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            PaisController controller = (PaisController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "distributivoController");
            return controller.getPais(getKey(value));
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
            if (object instanceof Pais) {
                Pais o = (Pais) object;
                return getStringKey(o.getIdpais());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Pais.class.getName()});
                return null;
            }
        }

    }

}
