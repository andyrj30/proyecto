package com.proyecto.controller;

import com.proyecto.entities.Distributivoaula;
import com.proyecto.controller.util.JsfUtil;
import com.proyecto.model.DistributivoaulaFacade;

import java.io.Serializable;
import java.util.List;
import javax.ejb.EJBException;

@javax.faces.bean.ManagedBean(name = "distributivoaulaController")
@javax.faces.bean.SessionScoped
public class DistributivoaulaController extends AbstractController implements Serializable {

    private Distributivoaula selected;

    public DistributivoaulaController() {
    }

    private DistributivoaulaFacade getFacade() {
        return ejbDistributivoaula;
    }

    public Distributivoaula getSelected() {
        return selected;
    }

    public void setSelected(Distributivoaula selected) {
        this.selected = selected;
    }

    public int count() {
        return getFacade().count();
    }

    public Distributivoaula prepareCreate() {
        selected = new Distributivoaula();
        return selected;
    }

    public void create() {
        try {
            getFacade().create(selected);
            JsfUtil.addSuccessMessage("Registro agregado correctamente");
            listDistributivoaula = null;
        } catch (EJBException e) {
            JsfUtil.addErrorMessage(e, defaultMsg);
        }
    }

    public void update() {
        try {
            getFacade().edit(selected);
            JsfUtil.addSuccessMessage("Datos editados");
            listDistributivoaula = null;
        } catch (EJBException e) {
            JsfUtil.addErrorMessage(e, defaultMsg);
        }
    }

    public void destroy() {
        try {
            getFacade().remove(selected);
            JsfUtil.addSuccessMessage("Registro eliminado correctamente");
            selected = null;
            listDistributivoaula = null;
        } catch (EJBException e) {
            JsfUtil.addErrorMessage(e, defaultMsg);
        }
    }


    public List<Distributivoaula> getItems() {
        if (listDistributivoaula == null) {
            listDistributivoaula = getFacade().findAll();
        }
        return listDistributivoaula;
    }

    public Distributivoaula getDistributivoaula(Object id) {
        return getFacade().find(id);
    }

    public List<Distributivoaula> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Distributivoaula> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }
}
