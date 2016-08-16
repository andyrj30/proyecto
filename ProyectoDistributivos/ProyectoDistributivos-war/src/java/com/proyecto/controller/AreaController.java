package com.proyecto.controller;

import com.proyecto.controller.util.JsfUtil;
import com.proyecto.entities.Area;
import com.proyecto.model.AreaFacade;

import java.io.Serializable;
import java.util.List;
import javax.ejb.EJBException;

@javax.faces.bean.ManagedBean(name = "areaController")
@javax.faces.bean.SessionScoped
public class AreaController extends AbstractController implements Serializable {

    private Area selected;

    public AreaController() {
    }

    private AreaFacade getFacade() {
        return ejbArea;
    }

    public Area getSelected() {
        return selected;
    }

    public void setSelected(Area selected) {
        this.selected = selected;
    }

    public int count() {
        return getFacade().count();
    }

    public Area prepareCreate() {
        selected = new Area();
        return selected;
    }

    public void create() {
        try {
            getFacade().create(selected);
            JsfUtil.addSuccessMessage("Registro agregado correctamente");
            listArea = null;
        } catch (EJBException e) {
            JsfUtil.addErrorMessage(e, defaultMsg);
        }
    }

    public void update() {
        try {
            getFacade().edit(selected);
            JsfUtil.addSuccessMessage("Datos editados");
            listArea = null;
        } catch (EJBException e) {
            JsfUtil.addErrorMessage(e, defaultMsg);
        }
    }

    public void destroy() {
        try {
            getFacade().remove(selected);
            JsfUtil.addSuccessMessage("Registro eliminado correctamente");
            selected = null;
            listArea = null;
        } catch (EJBException e) {
            JsfUtil.addErrorMessage(e, defaultMsg);
        }
    }


    public List<Area> getItems() {
        if (listArea == null) {
            listArea = getFacade().findAll();
        }
        return listArea;
    }

    public Area getArea(Object id) {
        return getFacade().find(id);
    }

    public List<Area> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Area> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }
}
