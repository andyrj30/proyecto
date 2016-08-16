package com.proyecto.controller;

import com.proyecto.entities.Horario;
import com.proyecto.controller.util.JsfUtil;
import com.proyecto.model.HorarioFacade;

import java.io.Serializable;
import java.util.List;
import javax.ejb.EJBException;

@javax.faces.bean.ManagedBean(name = "horarioController")
@javax.faces.bean.SessionScoped
public class HorarioController extends AbstractController implements Serializable {

    private Horario selected;

    public HorarioController() {
    }

    private HorarioFacade getFacade() {
        return ejbHorario;
    }

    public Horario getSelected() {
        return selected;
    }

    public void setSelected(Horario selected) {
        this.selected = selected;
    }

    public int count() {
        return getFacade().count();
    }

    public Horario prepareCreate() {
        selected = new Horario();
        return selected;
    }

    public void create() {
        try {
            getFacade().create(selected);
            JsfUtil.addSuccessMessage("Registro agregado correctamente");
            listHorario = null;
        } catch (EJBException e) {
            JsfUtil.addErrorMessage(e, defaultMsg);
        }
    }

    public void update() {
        try {
            getFacade().edit(selected);
            JsfUtil.addSuccessMessage("Datos editados");
            listHorario = null;
        } catch (EJBException e) {
            JsfUtil.addErrorMessage(e, defaultMsg);
        }
    }

    public void destroy() {
        try {
            getFacade().remove(selected);
            JsfUtil.addSuccessMessage("Registro eliminado correctamente");
            selected = null;
            listHorario = null;
        } catch (EJBException e) {
            JsfUtil.addErrorMessage(e, defaultMsg);
        }
    }


    public List<Horario> getItems() {
        if (listHorario == null) {
            listHorario = getFacade().findAll();
        }
        return listHorario;
    }

    public Horario getHorario(Object id) {
        return getFacade().find(id);
    }

    public List<Horario> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Horario> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }
}
