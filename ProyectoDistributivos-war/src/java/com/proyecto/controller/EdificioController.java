package com.proyecto.controller;

import com.proyecto.controller.util.JsfUtil;
import com.proyecto.entities.Edificio;
import com.proyecto.model.EdificioFacadeLocal;
import com.proyecto.websocket.WSEndpoint;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@javax.faces.bean.ManagedBean(name = "edificioController")
@javax.faces.bean.SessionScoped
public class EdificioController implements Serializable {

    @EJB
    protected EdificioFacadeLocal ejbEdificio;

    private Edificio selected;
    protected List<Edificio> listEdificio;
    protected String defaultMsg = "Ha ocurrido un error en la transacción";

    public EdificioController() {
    }

    private EdificioFacadeLocal getFacade() {
        return ejbEdificio;
    }

    public Edificio getSelected() {
        return selected;
    }

    public void setSelected(Edificio selected) {
        this.selected = selected;
    }

    public int count() {
        return getFacade().count();
    }

    public Edificio prepareCreate() {
        selected = new Edificio();
        return selected;
    }

    public void create() {
        try {
            getFacade().create(selected);
            JsfUtil.addSuccessMessage("Registro agregado correctamente");
            listEdificio = null;
            WSEndpoint.notificar("aula");
        } catch (EJBException e) {
            JsfUtil.addErrorMessage(e, defaultMsg);
        }
    }

    public void update() {
        try {
            getFacade().edit(selected);
            JsfUtil.addSuccessMessage("Datos editados");
            listEdificio = null;
            WSEndpoint.notificar("aula");
        } catch (EJBException e) {
            JsfUtil.addErrorMessage(e, defaultMsg);
        }
    }

    public void destroy() {
        try {
            getFacade().remove(selected);
            JsfUtil.addSuccessMessage("Registro eliminado correctamente");
            selected = null;
            listEdificio = null;
        } catch (EJBException e) {
            JsfUtil.addErrorMessage(e, defaultMsg);
        }
    }

    public List<Edificio> getItems() {
        listEdificio = getFacade().findAll();
        return listEdificio;
    }

    public List<String> getPisos() {
        List<String> listPisos = new ArrayList<>();
        listPisos.add("Planta baja");
        listPisos.add("Primer piso");
        listPisos.add("Segundo piso");
        listPisos.add("Tercer piso");
        listPisos.add("Cuarto piso");
        listPisos.add("Quinto piso");
        listPisos.add("Otro");
        return listPisos;
    }

    public Edificio getEdificio(Object id) {
        return getFacade().find(id);
    }

    @FacesConverter(forClass = Edificio.class)
    public static class EdificioControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            EdificioController controller = (EdificioController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "edificioController");
            return controller.getEdificio(getKey(value));
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
            if (object instanceof Edificio) {
                Edificio o = (Edificio) object;
                return getStringKey(o.getIdedificio());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Edificio.class.getName()});
                return null;
            }
        }

    }

}
