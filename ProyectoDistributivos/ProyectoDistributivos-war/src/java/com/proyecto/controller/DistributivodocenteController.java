package com.proyecto.controller;

import com.proyecto.entities.Distributivodocente;
import com.proyecto.controller.util.JsfUtil;
import com.proyecto.model.DistributivodocenteFacade;

import java.io.Serializable;
import java.util.List;
import javax.ejb.EJBException;

@javax.faces.bean.ManagedBean(name = "distributivodocenteController")
@javax.faces.bean.SessionScoped
public class DistributivodocenteController extends AbstractController implements Serializable {

    private Distributivodocente selected;

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

    public Distributivodocente getDistributivodocente(Object id) {
        return getFacade().find(id);
    }

    public List<Distributivodocente> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Distributivodocente> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }
}
