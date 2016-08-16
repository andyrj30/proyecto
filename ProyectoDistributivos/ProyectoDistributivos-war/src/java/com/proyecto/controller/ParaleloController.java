package com.proyecto.controller;

import com.proyecto.entities.Paralelo;
import com.proyecto.controller.util.JsfUtil;
import com.proyecto.model.ParaleloFacade;

import java.io.Serializable;
import java.util.List;
import javax.ejb.EJBException;

@javax.faces.bean.ManagedBean(name = "paraleloController")
@javax.faces.bean.SessionScoped
public class ParaleloController extends AbstractController implements Serializable {

    private Paralelo selected;

    public ParaleloController() {
    }

    private ParaleloFacade getFacade() {
        return ejbParalelo;
    }

    public Paralelo getSelected() {
        return selected;
    }

    public void setSelected(Paralelo selected) {
        this.selected = selected;
    }

    public int count() {
        return getFacade().count();
    }

    public Paralelo prepareCreate() {
        selected = new Paralelo();
        return selected;
    }

    public void create() {
        try {
            getFacade().create(selected);
            JsfUtil.addSuccessMessage("Registro agregado correctamente");
            listParalelo = null;
        } catch (EJBException e) {
            JsfUtil.addErrorMessage(e, defaultMsg);
        }
    }

    public void update() {
        try {
            getFacade().edit(selected);
            JsfUtil.addSuccessMessage("Datos editados");
            listParalelo = null;
        } catch (EJBException e) {
            JsfUtil.addErrorMessage(e, defaultMsg);
        }
    }

    public void destroy() {
        try {
            getFacade().remove(selected);
            JsfUtil.addSuccessMessage("Registro eliminado correctamente");
            selected = null;
            listParalelo = null;
        } catch (EJBException e) {
            JsfUtil.addErrorMessage(e, defaultMsg);
        }
    }


    public List<Paralelo> getItems() {
        if (listParalelo == null) {
            listParalelo = getFacade().findAll();
        }
        return listParalelo;
    }

    public Paralelo getParalelo(Object id) {
        return getFacade().find(id);
    }

    public List<Paralelo> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Paralelo> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }
}
