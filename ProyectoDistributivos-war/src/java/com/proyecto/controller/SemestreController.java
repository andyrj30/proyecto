package com.proyecto.controller;

import com.proyecto.entities.Semestre;
import com.proyecto.controller.util.JsfUtil;
import com.proyecto.model.SemestreFacadeLocal;

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

@javax.faces.bean.ManagedBean(name = "semestreController")
@javax.faces.bean.SessionScoped
public class SemestreController implements Serializable {

    @EJB
    protected SemestreFacadeLocal ejbSemestre;

    private Semestre selected;
    protected List<Semestre> listSemestre;
    protected String defaultMsg = "Ha ocurrido un error en la transacción";

    public SemestreController() {
    }

    private SemestreFacadeLocal getFacade() {
        return ejbSemestre;
    }

    public Semestre getSelected() {
        return selected;
    }

    public void setSelected(Semestre selected) {
        this.selected = selected;
    }

    public int count() {
        return getFacade().count();
    }

    public Semestre prepareCreate() {
        selected = new Semestre();
        return selected;
    }

    public void create() {
        try {
            getFacade().create(selected);
            JsfUtil.addSuccessMessage("Registro agregado correctamente");
            listSemestre = null;
        } catch (EJBException e) {
            JsfUtil.addErrorMessage(e, defaultMsg);
        }
    }

    public void update() {
        try {
            getFacade().edit(selected);
            JsfUtil.addSuccessMessage("Datos editados");
            listSemestre = null;
        } catch (EJBException e) {
            JsfUtil.addErrorMessage(e, defaultMsg);
        }
    }

    public void destroy() {
        try {
            getFacade().remove(selected);
            JsfUtil.addSuccessMessage("Registro eliminado correctamente");
            selected = null;
            listSemestre = null;
        } catch (EJBException e) {
            JsfUtil.addErrorMessage(e, defaultMsg);
        }
    }

    public List<Semestre> getItems() {
        listSemestre = getFacade().findAll();
        return listSemestre;
    }

    public Semestre getSemestre(Object id) {
        return getFacade().find(id);
    }

    @FacesConverter(forClass = Semestre.class)
    public static class SemestreControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            SemestreController controller = (SemestreController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "semestreController");
            return controller.getSemestre(getKey(value));
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
            if (object instanceof Semestre) {
                Semestre o = (Semestre) object;
                return getStringKey(o.getIdsemestre());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Semestre.class.getName()});
                return null;
            }
        }

    }

}
