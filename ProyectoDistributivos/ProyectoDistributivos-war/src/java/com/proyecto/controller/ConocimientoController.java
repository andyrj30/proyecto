package com.proyecto.controller;

import com.proyecto.entities.Conocimiento;
import com.proyecto.controller.util.JsfUtil;
import com.proyecto.model.ConocimientoFacade;

import java.io.Serializable;
import java.util.List;
import javax.ejb.EJBException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "conocimientoController")
@SessionScoped
public class ConocimientoController extends AbstractController implements Serializable {

    private Conocimiento selected;

    public ConocimientoController() {
    }

    private ConocimientoFacade getFacade() {
        return ejbConocimiento;
    }

    public Conocimiento getSelected() {
        return selected;
    }

    public void setSelected(Conocimiento selected) {
        this.selected = selected;
    }

    public int count() {
        return getFacade().count();
    }

    public Conocimiento prepareCreate() {
        selected = new Conocimiento();
        return selected;
    }

    public void create() {
        try {
            getFacade().create(selected);
            JsfUtil.addSuccessMessage("Registro agregado correctamente");
            listConocimiento = null;
        } catch (EJBException e) {
            JsfUtil.addErrorMessage(e, defaultMsg);
        }
    }

    public void update() {
        try {
            getFacade().edit(selected);
            JsfUtil.addSuccessMessage("Datos editados");
            listConocimiento = null;
        } catch (EJBException e) {
            JsfUtil.addErrorMessage(e, defaultMsg);
        }
    }

    public void destroy() {
        try {
            getFacade().remove(selected);
            JsfUtil.addSuccessMessage("Registro eliminado correctamente");
            selected = null;
            listConocimiento = null;
        } catch (EJBException e) {
            JsfUtil.addErrorMessage(e, defaultMsg);
        }
    }


    public List<Conocimiento> getItems() {
        if (listConocimiento == null) {
            listConocimiento = getFacade().findAll();
        }
        return listConocimiento;
    }

    public Conocimiento getConocimiento(Object id) {
        return getFacade().find(id);
    }

    public List<Conocimiento> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Conocimiento> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }
}
