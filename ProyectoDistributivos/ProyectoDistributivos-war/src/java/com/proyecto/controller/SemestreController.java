package com.proyecto.controller;

import com.proyecto.entities.Semestre;
import com.proyecto.controller.util.JsfUtil;
import com.proyecto.model.SemestreFacade;

import java.io.Serializable;
import java.util.List;
import javax.ejb.EJBException;

@javax.faces.bean.ManagedBean(name = "semestreController")
@javax.faces.bean.SessionScoped
public class SemestreController extends AbstractController implements Serializable {

    private Semestre selected;

    public SemestreController() {
    }

    private SemestreFacade getFacade() {
        return ejbSemestre;
    }

    public Semestre getSelected() {
        return selected;
    }

    public void setSelected(Semestre selected) {
        this.selected = selected;
    }

    public int count() {
        return getFacade().count();
    }

    public Semestre prepareCreate() {
        selected = new Semestre();
        return selected;
    }

    public void create() {
        try {
            getFacade().create(selected);
            JsfUtil.addSuccessMessage("Registro agregado correctamente");
            listSemestre = null;
        } catch (EJBException e) {
            JsfUtil.addErrorMessage(e, defaultMsg);
        }
    }

    public void update() {
        try {
            getFacade().edit(selected);
            JsfUtil.addSuccessMessage("Datos editados");
            listSemestre = null;
        } catch (EJBException e) {
            JsfUtil.addErrorMessage(e, defaultMsg);
        }
    }

    public void destroy() {
        try {
            getFacade().remove(selected);
            JsfUtil.addSuccessMessage("Registro eliminado correctamente");
            selected = null;
            listSemestre = null;
        } catch (EJBException e) {
            JsfUtil.addErrorMessage(e, defaultMsg);
        }
    }


    public List<Semestre> getItems() {
        if (listSemestre == null) {
            listSemestre = getFacade().findAll();
        }
        return listSemestre;
    }

    public Semestre getSemestre(Object id) {
        return getFacade().find(id);
    }

    public List<Semestre> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Semestre> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }
}
