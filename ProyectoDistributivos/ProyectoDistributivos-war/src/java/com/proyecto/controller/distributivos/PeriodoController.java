/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyecto.controller.distributivos;

import com.proyecto.controller.util.JsfUtil;
import com.proyecto.entities.Carrera;
import com.proyecto.entities.Docente;
import com.proyecto.entities.Periodo;
import com.proyecto.model.CarreraFacade;
import com.proyecto.model.DocenteFacade;
import com.proyecto.model.PeriodoFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Andy
 */
@Named(value = "periododistController")
@SessionScoped
public class PeriodoController implements Serializable {

    @EJB
    private DocenteFacade docenteFacade;

    @EJB
    private CarreraFacade carreraFacade;

    @EJB
    private com.proyecto.model.PeriodoFacade ejbFacade;

    private List<Periodo> items = null;
    private List<Carrera> carreras = null;
    private List<Docente> docentes = null;
    private Periodo selected;

    public PeriodoController() {
    }

    public Periodo getSelected() {
        return selected;
    }

    public void setSelected(Periodo selected) {
        this.selected = selected;
    }

    public int count() {
        return ejbFacade.count();
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private PeriodoFacade getFacade() {
        return ejbFacade;
    }

    public Periodo prepareCreate() {
        selected = new Periodo();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        selected.setInicio(new Date());
        getFacade().create(selected);
        for (int i = 0; i < 10; i++) {

        }
        JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("PeriodoCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void destroy() {
        selected.setFin(new Date());
        JsfUtil.addSuccessMessage( "Periodo terminado");
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void buscar(){
        
    }
    
    public List<Periodo> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }
    
    public List<Docente> getdocentes() {
        if (docentes == null) {
            docentes = docenteFacade.findAll();
        }
        return docentes;
    }
    public List<Carrera> getcarreras() {
        if (carreras == null) {
            carreras = carreraFacade.findAll();
        }
        return carreras;
    }

    public Periodo getPeriodo(java.lang.String id) {
        return getFacade().find(id);
    }

    public List<Periodo> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Periodo> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Periodo.class)
    public static class PeriodoControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            PeriodoController controller = (PeriodoController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "periodoController");
            return controller.getPeriodo(getKey(value));
        }

        java.lang.String getKey(String value) {
            java.lang.String key;
            key = value;
            return key;
        }

        String getStringKey(java.lang.String value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Periodo) {
                Periodo o = (Periodo) object;
                return getStringKey(o.getIdperiodo());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Periodo.class.getName()});
                return null;
            }
        }

    }

}
