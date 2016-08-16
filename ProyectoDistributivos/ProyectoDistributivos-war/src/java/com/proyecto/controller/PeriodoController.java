package com.proyecto.controller;

import com.proyecto.entities.Periodo;
import com.proyecto.controller.util.JsfUtil;
import com.proyecto.model.PeriodoFacade;

import java.io.Serializable;
import java.util.List;
import javax.ejb.EJBException;

@javax.faces.bean.ManagedBean(name = "periodoController")
@javax.faces.bean.SessionScoped
public class PeriodoController extends AbstractController implements Serializable {

    private Periodo selected;

    public PeriodoController() {
    }

    private PeriodoFacade getFacade() {
        return ejbPeriodo;
    }

    public Periodo getSelected() {
        return selected;
    }

    public void setSelected(Periodo selected) {
        this.selected = selected;
    }

    public int count() {
        return getFacade().count();
    }

    public Periodo prepareCreate() {
        selected = new Periodo();
        return selected;
    }

    public void create() {
        try {
            getFacade().create(selected);
            JsfUtil.addSuccessMessage("Registro agregado correctamente");
            listPeriodo = null;
        } catch (EJBException e) {
            JsfUtil.addErrorMessage(e, defaultMsg);
        }
    }

    public void update() {
        try {
            getFacade().edit(selected);
            JsfUtil.addSuccessMessage("Datos editados");
            listPeriodo = null;
        } catch (EJBException e) {
            JsfUtil.addErrorMessage(e, defaultMsg);
        }
    }

    public void destroy() {
        try {
            getFacade().remove(selected);
            JsfUtil.addSuccessMessage("Registro eliminado correctamente");
            selected = null;
            listPeriodo = null;
        } catch (EJBException e) {
            JsfUtil.addErrorMessage(e, defaultMsg);
        }
    }

    public List<Periodo> getItems() {
        if (listPeriodo == null) {
            listPeriodo = getFacade().findAll();
        }
        return listPeriodo;
    }

    public Periodo getPeriodo(Object id) {
        return getFacade().find(id);
    }
}
