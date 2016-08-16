package com.proyecto.controller;

import com.proyecto.entities.Distributivodocente;
import com.proyecto.controller.util.JsfUtil;
import com.proyecto.entities.Docente;
import com.proyecto.model.DistributivodocenteFacade;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJBException;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@javax.faces.bean.ManagedBean(name = "distributivodocenteController")
@javax.faces.bean.SessionScoped
public class DistributivodocenteController extends AbstractController implements Serializable {

    private Distributivodocente selected;
    private Docente docente;
    private int b = 0;

    public DistributivodocenteController() {
    }

    private DistributivodocenteFacade getFacade() {
        return ejbDistributivodocente;
    }

    public Distributivodocente getSelected() {
        return selected;
    }

    public void setSelected(Distributivodocente selected) {
        this.selected = selected;
    }

    public Docente getDocente() {
        return docente;
    }

    public void setDocente(Docente docente) {
        this.docente = docente;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }

    public void todos() {
        b = 1;
    }

    public int count() {
        return getFacade().count();
    }

    public Distributivodocente prepareCreate() {
        selected = new Distributivodocente();
        return selected;
    }

    public void create() {
        try {
            getFacade().create(selected);
            JsfUtil.addSuccessMessage("Registro agregado correctamente");
            listDistributivodocente = null;
        } catch (EJBException e) {
            JsfUtil.addErrorMessage(e, defaultMsg);
        }
    }

    public void update() {
        try {
            getFacade().edit(selected);
            JsfUtil.addSuccessMessage("Datos editados");
            listDistributivodocente = null;
        } catch (EJBException e) {
            JsfUtil.addErrorMessage(e, defaultMsg);
        }
    }

    public void destroy() {
        try {
            getFacade().remove(selected);
            JsfUtil.addSuccessMessage("Registro eliminado correctamente");
            selected = null;
            listDistributivodocente = null;
        } catch (EJBException e) {
            JsfUtil.addErrorMessage(e, defaultMsg);
        }
    }

    public List<Distributivodocente> getItems() {
        if (listDistributivodocente == null) {
            listDistributivodocente = getFacade().findAll();
        }
        return listDistributivodocente;
    }

    public List<Docente> getDocentes() {
        if (listDocente == null) {
            listDocente = ejbDocente.findAll();
        }
        return listDocente;
    }

    public Distributivodocente getDistributivodocente(Object id) {
        return getFacade().find(id);
    }

    @FacesConverter(forClass = Distributivodocente.class)
    public static class DistributivodocenteControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            DistributivodocenteController controller = (DistributivodocenteController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "distributivodocenteController");
            return controller.getDistributivodocente(getKey(value));
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
            if (object instanceof Distributivodocente) {
                Distributivodocente o = (Distributivodocente) object;
                return getStringKey(o.getIddistributivodoc());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Distributivodocente.class.getName()});
                return null;
            }
        }

    }

}
