package com.proyecto.controller;

import com.proyecto.entities.Carrera;
import com.proyecto.controller.util.JsfUtil;
import com.proyecto.model.CarreraFacade;

import java.io.Serializable;
import java.util.List;
import javax.ejb.EJBException;

@javax.faces.bean.ManagedBean(name = "carreraController")
@javax.faces.bean.SessionScoped
public class CarreraController extends AbstractController implements Serializable {

    private Carrera selected;

    public CarreraController() {
    }

    private CarreraFacade getFacade() {
        return ejbCarrera;
    }

    public Carrera getSelected() {
        return selected;
    }

    public void setSelected(Carrera selected) {
        this.selected = selected;
    }

    public int count() {
        return getFacade().count();
    }

    public Carrera prepareCreate() {
        selected = new Carrera();
        return selected;
    }

    public void create() {
        try {
            getFacade().create(selected);
            JsfUtil.addSuccessMessage("Registro agregado correctamente");
            listCarrera = null;
        } catch (EJBException e) {
            JsfUtil.addErrorMessage(e, defaultMsg);
        }
    }

    public void update() {
        try {
            getFacade().edit(selected);
            JsfUtil.addSuccessMessage("Datos editados");
            listCarrera = null;
        } catch (EJBException e) {
            JsfUtil.addErrorMessage(e, defaultMsg);
        }
    }

    public void destroy() {
        try {
            getFacade().remove(selected);
            JsfUtil.addSuccessMessage("Registro eliminado correctamente");
            selected = null;
            listCarrera = null;
        } catch (EJBException e) {
            JsfUtil.addErrorMessage(e, defaultMsg);
        }
    }

    public List<Carrera> getItems() {
        if (listCarrera == null) {
            listCarrera = getFacade().findAll();
        }
        return listCarrera;
    }

    public Carrera getCarrera(Object id) {
        return getFacade().find(id);
    }
}
