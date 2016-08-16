package com.proyecto.controller;

import com.proyecto.controller.util.JsfUtil;
import com.proyecto.entities.Aula;
import com.proyecto.model.AulaFacade;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJBException;

@javax.faces.bean.ManagedBean(name = "aulaController")
@javax.faces.bean.SessionScoped
public class AulaController extends AbstractController implements Serializable {

    private Aula selected;
    private List<String> edificios = null;

    public AulaController() {
        edificios = new ArrayList<>();
        edificios.add("Instituto de Informtica");
        edificios.add("Facultad de Ciencias de la Ingenieria");
    }

    private AulaFacade getFacade() {
        return ejbAula;
    }

    public List<String> getEdificios() {
        return edificios;
    }

    public void setEdificios(List<String> edificios) {
        this.edificios = edificios;
    }

    public Aula getSelected() {
        return selected;
    }

    public void setSelected(Aula selected) {
        this.selected = selected;
    }

    public int count() {
        return getFacade().count();
    }

    public Aula prepareCreate() {
        selected = new Aula();
        return selected;
    }

    public void create() {
        try {
            getFacade().create(selected);
            JsfUtil.addSuccessMessage("Registro agregado correctamente");
            listAula = null;
        } catch (EJBException e) {
            JsfUtil.addErrorMessage(e, defaultMsg);
        }
    }

    public void update() {
        try {
            getFacade().edit(selected);
            JsfUtil.addSuccessMessage("Datos editados");
            listAula = null;
        } catch (EJBException e) {
            JsfUtil.addErrorMessage(e, defaultMsg);
        }
    }

    public void destroy() {
        try {
            getFacade().remove(selected);
            JsfUtil.addSuccessMessage("Registro eliminado correctamente");
            selected = null;
            listAula = null;
        } catch (EJBException e) {
            JsfUtil.addErrorMessage(e, defaultMsg);
        }
    }

    public List<Aula> getItems() {
        if (listAula == null) {
            listAula = getFacade().findAll();
        }
        return listAula;
    }

    public Aula getAula(Object id) {
        return getFacade().find(id);
    }

    public List<Aula> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Aula> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }
}
