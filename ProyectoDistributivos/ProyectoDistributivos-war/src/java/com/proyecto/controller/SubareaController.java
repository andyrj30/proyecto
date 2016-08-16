package com.proyecto.controller;

import com.proyecto.entities.Subarea;
import com.proyecto.controller.util.JsfUtil;
import com.proyecto.model.SubareaFacade;

import java.io.Serializable;
import java.util.List;
import javax.ejb.EJBException;

@javax.faces.bean.ManagedBean(name = "subareaController")
@javax.faces.bean.SessionScoped
public class SubareaController extends AbstractController implements Serializable {

    private Subarea selected;

    public SubareaController() {
    }

    private SubareaFacade getFacade() {
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
        } catch (EJBException e) {
            JsfUtil.addErrorMessage(e, defaultMsg);
        }
    }

    public void update() {
        try {
            getFacade().edit(selected);
            JsfUtil.addSuccessMessage("Datos editados");
            listSubarea = null;
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
        } catch (EJBException e) {
            JsfUtil.addErrorMessage(e, defaultMsg);
        }
    }


    public List<Subarea> getItems() {
        if (listSubarea == null) {
            listSubarea = getFacade().findAll();
        }
        return listSubarea;
    }

    public Subarea getSubarea(Object id) {
        return getFacade().find(id);
    }

    public List<Subarea> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Subarea> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }
}
