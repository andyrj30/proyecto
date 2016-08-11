package com.proyecto.controller.distributivos;

import com.proyecto.entities.*;
import com.proyecto.model.DocenteFacade;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "docentedistController")
@SessionScoped
public class DocenteController {

    @EJB
    private com.proyecto.model.DocenteFacade ejbFacade;
    private List<Docente> items = null;
    private Docente selected;

    public DocenteController() {
    }

    public Docente getSelected() {
        return selected;
    }

    public int count() {
        return ejbFacade.count();
    }

    public void setSelected(Docente selected) {
        this.selected = selected;
    }

    private DocenteFacade getFacade() {
        return ejbFacade;
    }

    public Docente prepareCreate() {
        selected = new Docente();
        return selected;
    }

    public Docente getDocente(java.lang.String id) {
        return getFacade().find(id);
    }
}
