package com.proyecto.controller;

import com.proyecto.entities.Materia;
import com.proyecto.controller.util.JsfUtil;
import com.proyecto.entities.Semestre;
import com.proyecto.entities.Subarea;
import com.proyecto.model.MateriaFacade;
import com.proyecto.model.MateriaFacadeLocal;
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

@javax.faces.bean.ManagedBean(name = "materiaController")
@javax.faces.bean.SessionScoped
public class MateriaController extends AbstractController implements Serializable {

    private Materia selected;
    private Semestre semestre;
    private Subarea subarea;

    public MateriaController() {
    }

    public Semestre getSemestre() {
        return semestre;
    }

    public void setSemestre(Semestre semestre) {
        this.semestre = semestre;
    }

    public Subarea getSubarea() {
        return subarea;
    }

    public void setSubarea(Subarea subarea) {
        this.subarea = subarea;
    }

    private MateriaFacadeLocal getFacade() {
        return ejbMateria;
    }

    public Materia getSelected() {
        return selected;
    }

    public void setSelected(Materia selected) {
        this.selected = selected;
    }

    public int count() {
        return getFacade().count();
    }

    public Materia prepareCreate() {
        selected = new Materia();
        return selected;
    }

    public void create() {
        try {
            selected.setSemestre(semestre);
            selected.setSubarea(subarea);
            if (selected.getCodmateria().substring(0,3).equals("CPI")) {
                selected.setTipo("pi");
            } else {
                selected.setTipo("materia");
            }
            getFacade().create(selected);
            JsfUtil.addSuccessMessage("Registro agregado correctamente");
            WSEndpoint.notificar("materia");
            listMateria = null;
        } catch (EJBException e) {
            JsfUtil.addErrorMessage(e, defaultMsg);
        }
    }

    public void update() {
        try {
            getFacade().edit(selected);
            JsfUtil.addSuccessMessage("Datos editados");
            listMateria = null;
            WSEndpoint.notificar("materia");
        } catch (EJBException e) {
            JsfUtil.addErrorMessage(e, defaultMsg);
        }
    }

    public void destroy() {
        try {
            getFacade().remove(selected);
            JsfUtil.addSuccessMessage("Registro eliminado correctamente");
            selected = null;
            listMateria = null;
            WSEndpoint.notificar("materia");
        } catch (EJBException e) {
            JsfUtil.addErrorMessage(e, defaultMsg);
        }
    }

    public List<Materia> getItems() {
        listMateria = getFacade().findAll();
        return listMateria;
    }

    public Materia getMateria(Object id) {
        return getFacade().find(id);
    }

    @FacesConverter(forClass = Materia.class)
    public static class MateriaControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            MateriaController controller = (MateriaController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "materiaController");
            return controller.getMateria(getKey(value));
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
            if (object instanceof Materia) {
                Materia o = (Materia) object;
                return getStringKey(o.getCodmateria());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Materia.class.getName()});
                return null;
            }
        }

    }

}
