package com.proyecto.controller;

import com.proyecto.entities.Docente;
import com.proyecto.controller.util.JsfUtil;
import com.proyecto.model.DocenteFacade;

import java.io.Serializable;
import java.util.List;
import javax.ejb.EJBException;

@javax.faces.bean.ManagedBean(name = "docenteController")
@javax.faces.bean.SessionScoped
public class DocenteController extends AbstractController implements Serializable {

    private Docente selected;

    public DocenteController() {
    }

    private DocenteFacade getFacade() {
        return ejbDocente;
    }

    public Docente getSelected() {
        return selected;
    }

    public void setSelected(Docente selected) {
        this.selected = selected;
    }

    public int count() {
        return getFacade().count();
    }

    public Docente prepareCreate() {
        selected = new Docente();
        return selected;
    }

    public void create() {
        try {
            getFacade().create(selected);
            JsfUtil.addSuccessMessage("Registro agregado correctamente");
            listDocente = null;
        } catch (EJBException e) {
            JsfUtil.addErrorMessage(e, defaultMsg);
        }
    }

    public void update() {
        try {
            getFacade().edit(selected);
            JsfUtil.addSuccessMessage("Datos editados");
            listDocente = null;
        } catch (EJBException e) {
            JsfUtil.addErrorMessage(e, defaultMsg);
        }
    }

    public void destroy() {
        try {
            getFacade().remove(selected);
            JsfUtil.addSuccessMessage("Registro eliminado correctamente");
            selected = null;
            listDocente = null;
        } catch (EJBException e) {
            JsfUtil.addErrorMessage(e, defaultMsg);
        }
    }


    public List<Docente> getItems() {
        if (listDocente == null) {
            listDocente = getFacade().findAll();
        }
        return listDocente;
    }

    public Docente getDocente(Object id) {
        return getFacade().find(id);
    }

    public List<Docente> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Docente> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }
}
