package com.proyecto.controller;

import com.proyecto.entities.Distributivoclase;
import com.proyecto.controller.util.JsfUtil;
import com.proyecto.model.DistributivoclaseFacade;

import java.io.Serializable;
import java.util.List;
import javax.ejb.EJBException;

@javax.faces.bean.ManagedBean(name = "distributivoclaseController")
@javax.faces.bean.SessionScoped
public class DistributivoclaseController extends AbstractController implements Serializable {

    private Distributivoclase selected;

    public DistributivoclaseController() {
    }

    private DistributivoclaseFacade getFacade() {
        return ejbDistributivoclase;
    }

    public Distributivoclase getSelected() {
        return selected;
    }

    public void setSelected(Distributivoclase selected) {
        this.selected = selected;
    }

    public int count() {
        return getFacade().count();
    }

    public Distributivoclase prepareCreate() {
        selected = new Distributivoclase();
        return selected;
    }

    public void create() {
        try {
            getFacade().create(selected);
            JsfUtil.addSuccessMessage("Registro agregado correctamente");
            listDistributivoclase = null;
        } catch (EJBException e) {
            JsfUtil.addErrorMessage(e, defaultMsg);
        }
    }

    public void update() {
        try {
            getFacade().edit(selected);
            JsfUtil.addSuccessMessage("Datos editados");
            listDistributivoclase = null;
        } catch (EJBException e) {
            JsfUtil.addErrorMessage(e, defaultMsg);
        }
    }

    public void destroy() {
        try {
            getFacade().remove(selected);
            JsfUtil.addSuccessMessage("Registro eliminado correctamente");
            selected = null;
            listDistributivoclase = null;
        } catch (EJBException e) {
            JsfUtil.addErrorMessage(e, defaultMsg);
        }
    }


    public List<Distributivoclase> getItems() {
        if (listDistributivoclase == null) {
            listDistributivoclase = getFacade().findAll();
        }
        return listDistributivoclase;
    }

    public Distributivoclase getDistributivoclase(Object id) {
        return getFacade().find(id);
    }

    public List<Distributivoclase> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Distributivoclase> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }
}
