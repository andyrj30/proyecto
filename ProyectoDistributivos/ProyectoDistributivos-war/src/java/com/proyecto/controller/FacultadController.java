package com.proyecto.controller;

import com.proyecto.entities.Facultad;
import com.proyecto.controller.util.JsfUtil;
import com.proyecto.model.FacultadFacade;

import java.io.Serializable;
import java.util.List;
import javax.ejb.EJBException;

@javax.faces.bean.ManagedBean(name = "facultadController")
@javax.faces.bean.SessionScoped
public class FacultadController extends AbstractController implements Serializable {

    private Facultad selected;

    public FacultadController() {
    }

    private FacultadFacade getFacade() {
        return ejbFacultad;
    }

    public Facultad getSelected() {
        return selected;
    }

    public void setSelected(Facultad selected) {
        this.selected = selected;
    }

    public int count() {
        return getFacade().count();
    }

    public Facultad prepareCreate() {
        selected = new Facultad();
        return selected;
    }

    public void create() {
        try {
            getFacade().create(selected);
            JsfUtil.addSuccessMessage("Registro agregado correctamente");
            listFacultad = null;
        } catch (EJBException e) {
            JsfUtil.addErrorMessage(e, defaultMsg);
        }
    }

    public void update() {
        try {
            getFacade().edit(selected);
            JsfUtil.addSuccessMessage("Datos editados");
            listFacultad = null;
        } catch (EJBException e) {
            JsfUtil.addErrorMessage(e, defaultMsg);
        }
    }

    public void destroy() {
        try {
            getFacade().remove(selected);
            JsfUtil.addSuccessMessage("Registro eliminado correctamente");
            selected = null;
            listFacultad = null;
        } catch (EJBException e) {
            JsfUtil.addErrorMessage(e, defaultMsg);
        }
    }

    public List<Facultad> getItems() {
        if (listFacultad == null) {
            listFacultad = getFacade().findAll();
        }
        return listFacultad;
    }

    public Facultad getFacultad(Object id) {
        return getFacade().find(id);
    }
}
