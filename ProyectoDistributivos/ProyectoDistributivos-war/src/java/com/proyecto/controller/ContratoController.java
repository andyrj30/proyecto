package com.proyecto.controller;

import com.proyecto.entities.Contrato;
import com.proyecto.controller.util.JsfUtil;
import com.proyecto.model.ContratoFacade;

import java.io.Serializable;
import java.util.List;
import javax.ejb.EJBException;

@javax.faces.bean.ManagedBean(name = "contratoController")
@javax.faces.bean.SessionScoped
public class ContratoController extends AbstractController implements Serializable {

    private Contrato selected;

    public ContratoController() {
    }

    private ContratoFacade getFacade() {
        return ejbContrato;
    }

    public Contrato getSelected() {
        return selected;
    }

    public void setSelected(Contrato selected) {
        this.selected = selected;
    }

    public int count() {
        return getFacade().count();
    }

    public Contrato prepareCreate() {
        selected = new Contrato();
        return selected;
    }

    public void create() {
        try {
            getFacade().create(selected);
            JsfUtil.addSuccessMessage("Registro agregado correctamente");
            listContrato = null;
        } catch (EJBException e) {
            JsfUtil.addErrorMessage(e, defaultMsg);
        }
    }

    public void update() {
        try {
            getFacade().edit(selected);
            JsfUtil.addSuccessMessage("Datos editados");
            listContrato = null;
        } catch (EJBException e) {
            JsfUtil.addErrorMessage(e, defaultMsg);
        }
    }

    public void destroy() {
        try {
            getFacade().remove(selected);
            JsfUtil.addSuccessMessage("Registro eliminado correctamente");
            selected = null;
            listContrato = null;
        } catch (EJBException e) {
            JsfUtil.addErrorMessage(e, defaultMsg);
        }
    }

    public List<Contrato> getItems() {
        if (listContrato == null) {
            listContrato = getFacade().findAll();
        }
        return listContrato;
    }

    public Contrato getContrato(Object id) {
        return getFacade().find(id);
    }
}
