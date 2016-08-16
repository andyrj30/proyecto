package com.proyecto.controller;

import com.proyecto.controller.util.JsfUtil;
import com.proyecto.entities.Aula;
import com.proyecto.model.AulaFacade;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJBException;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@javax.faces.bean.ManagedBean(name = "aulaController")
@javax.faces.bean.SessionScoped
public class AulaController extends AbstractController implements Serializable {

    private Aula selected;
    private List<String> edificios = null;

    public AulaController() {
        edificios = new ArrayList<>();
        edificios.add("Instituto de Informtica");
        edificios.add("Facultad de Ciencias de la Ingenieria");
    }

    private AulaFacade getFacade() {
        return ejbAula;
    }

    public List<String> getEdificios() {
        return edificios;
    }

    public void setEdificios(List<String> edificios) {
        this.edificios = edificios;
    }

    public Aula getSelected() {
        return selected;
    }

    public void setSelected(Aula selected) {
        this.selected = selected;
    }

    public int count() {
        return getFacade().count();
    }

    public Aula prepareCreate() {
        selected = new Aula();
        return selected;
    }

    public void create() {
        try {
            getFacade().create(selected);
            JsfUtil.addSuccessMessage("Registro agregado correctamente");
            listAula = null;
        } catch (EJBException e) {
            JsfUtil.addErrorMessage(e, defaultMsg);
        }
    }

    public void update() {
        try {
            getFacade().edit(selected);
            JsfUtil.addSuccessMessage("Datos editados");
            listAula = null;
        } catch (EJBException e) {
            JsfUtil.addErrorMessage(e, defaultMsg);
        }
    }

    public void destroy() {
        try {
            getFacade().remove(selected);
            JsfUtil.addSuccessMessage("Registro eliminado correctamente");
            selected = null;
            listAula = null;
        } catch (EJBException e) {
            JsfUtil.addErrorMessage(e, defaultMsg);
        }
    }

    public List<Aula> getItems() {
        if (listAula == null) {
            listAula = getFacade().findAll();
        }
        return listAula;
    }

    public Aula getAula(Object id) {
        return getFacade().find(id);
    }

    @FacesConverter(forClass = Aula.class)
    public static class AulaControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            AulaController controller = (AulaController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "aulaController");
            return controller.getAula(getKey(value));
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
            if (object instanceof Aula) {
                Aula o = (Aula) object;
                return getStringKey(o.getCodaula());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Aula.class.getName()});
                return null;
            }
        }

    }

}
