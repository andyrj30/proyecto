package com.proyecto.controller;

import com.proyecto.entities.Periodo;
import com.proyecto.controller.util.JsfUtil;
import com.proyecto.model.PeriodoFacadeLocal;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJBException;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@javax.faces.bean.ManagedBean(name = "periodoController")
@javax.faces.bean.SessionScoped
public class PeriodoController extends AbstractController implements Serializable {

    private Periodo selected;

    public PeriodoController() {
    }

    private PeriodoFacadeLocal getFacade() {
        return ejbPeriodo;
    }

    public Periodo getSelected() {
        return selected;
    }

    public void setSelected(Periodo selected) {
        this.selected = selected;
    }

    public int count() {
        return getFacade().count();
    }

    public Periodo prepareCreate() {
        selected = new Periodo();
        return selected;
    }

    public void create() {
        try {
            selected.setInicio(new Date());
            getFacade().create(selected);
            JsfUtil.addSuccessMessage("Registro agregado correctamente");
            JsfUtil.addSuccessMessage(new Date().toString());
            listPeriodo = null;
        } catch (EJBException e) {
            JsfUtil.addErrorMessage(e, defaultMsg);
        }
    }

    public void update() {
        try {
            getFacade().edit(selected);
            JsfUtil.addSuccessMessage("Datos editados");
            listPeriodo = null;
        } catch (EJBException e) {
            JsfUtil.addErrorMessage(e, defaultMsg);
        }
    }

    public void destroy() {
        try {
            selected.setFin(new Date());
            selected.setEstado("Finalizado");
            getFacade().edit(selected);
            JsfUtil.addSuccessMessage("Periodo terminado");
        } catch (EJBException e) {
            JsfUtil.addErrorMessage(e, defaultMsg);
        }
    }

    public List<Periodo> getItems() {
        listPeriodo = getFacade().findAll();
        return listPeriodo;
    }

    public Periodo getPeriodo(Object id) {
        return getFacade().find(id);
    }

    @FacesConverter(forClass = Periodo.class)
    public static class PeriodoControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            PeriodoController controller = (PeriodoController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "periodoController");
            return controller.getPeriodo(getKey(value));
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
            if (object instanceof Periodo) {
                Periodo o = (Periodo) object;
                return getStringKey(o.getIdperiodo().toString());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Periodo.class.getName()});
                return null;
            }
        }

    }

}
