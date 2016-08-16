package com.proyecto.controller;

import com.proyecto.entities.Titulo;
import com.proyecto.controller.util.JsfUtil;
import com.proyecto.model.TituloFacade;

import java.io.Serializable;
import java.util.List;
import javax.ejb.EJBException;

@javax.faces.bean.ManagedBean(name = "tituloController")
@javax.faces.bean.SessionScoped
public class TituloController extends AbstractController implements Serializable {

    private Titulo selected;

    public TituloController() {
    }

    private TituloFacade getFacade() {
        return ejbTitulo;
    }

    public Titulo getSelected() {
        return selected;
    }

    public void setSelected(Titulo selected) {
        this.selected = selected;
    }

    public int count() {
        return getFacade().count();
    }

    public Titulo prepareCreate() {
        selected = new Titulo();
        return selected;
    }

    public void create() {
        try {
            getFacade().create(selected);
            JsfUtil.addSuccessMessage("Registro agregado correctamente");
            listTitulo = null;
        } catch (EJBException e) {
            JsfUtil.addErrorMessage(e, defaultMsg);
        }
    }

    public void update() {
        try {
            getFacade().edit(selected);
            JsfUtil.addSuccessMessage("Datos editados");
            listTitulo = null;
        } catch (EJBException e) {
            JsfUtil.addErrorMessage(e, defaultMsg);
        }
    }

    public void destroy() {
        try {
            getFacade().remove(selected);
            JsfUtil.addSuccessMessage("Registro eliminado correctamente");
            selected = null;
            listTitulo = null;
        } catch (EJBException e) {
            JsfUtil.addErrorMessage(e, defaultMsg);
        }
    }


    public List<Titulo> getItems() {
        if (listTitulo == null) {
            listTitulo = getFacade().findAll();
        }
        return listTitulo;
    }

    public Titulo getTitulo(Object id) {
        return getFacade().find(id);
    }

    public List<Titulo> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Titulo> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }
}
