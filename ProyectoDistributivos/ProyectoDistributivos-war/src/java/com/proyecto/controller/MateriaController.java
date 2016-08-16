package com.proyecto.controller;

import com.proyecto.entities.Materia;
import com.proyecto.controller.util.JsfUtil;
import com.proyecto.model.MateriaFacade;

import java.io.Serializable;
import java.util.List;
import javax.ejb.EJBException;

@javax.faces.bean.ManagedBean(name = "materiaController")
@javax.faces.bean.SessionScoped
public class MateriaController extends AbstractController implements Serializable {

    private Materia selected;

    public MateriaController() {
    }

    private MateriaFacade getFacade() {
        return ejbMateria;
    }

    public Materia getSelected() {
        return selected;
    }

    public void setSelected(Materia selected) {
        this.selected = selected;
    }

    public int count() {
        return getFacade().count();
    }

    public Materia prepareCreate() {
        selected = new Materia();
        return selected;
    }

    public void create() {
        try {
            getFacade().create(selected);
            JsfUtil.addSuccessMessage("Registro agregado correctamente");
            listMateria = null;
        } catch (EJBException e) {
            JsfUtil.addErrorMessage(e, defaultMsg);
        }
    }

    public void update() {
        try {
            getFacade().edit(selected);
            JsfUtil.addSuccessMessage("Datos editados");
            listMateria = null;
        } catch (EJBException e) {
            JsfUtil.addErrorMessage(e, defaultMsg);
        }
    }

    public void destroy() {
        try {
            getFacade().remove(selected);
            JsfUtil.addSuccessMessage("Registro eliminado correctamente");
            selected = null;
            listMateria = null;
        } catch (EJBException e) {
            JsfUtil.addErrorMessage(e, defaultMsg);
        }
    }

    public List<Materia> getItems() {
        if (listMateria == null) {
            listMateria = getFacade().findAll();
        }
        return listMateria;
    }

    public Materia getMateria(Object id) {
        return getFacade().find(id);
    }
}
