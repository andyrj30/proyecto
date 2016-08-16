package com.proyecto.controller;

import com.proyecto.entities.Distributivoclase;
import com.proyecto.controller.util.JsfUtil;
import com.proyecto.entities.Carrera;
import com.proyecto.entities.Distributivodocente;
import com.proyecto.entities.Facultad;
import com.proyecto.entities.Paralelo;
import com.proyecto.entities.Periodo;
import com.proyecto.entities.Semestre;
import com.proyecto.model.DistributivoclaseFacade;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJBException;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@javax.faces.bean.ManagedBean(name = "distributivoclaseController")
@javax.faces.bean.SessionScoped
public class DistributivoclaseController extends AbstractController implements Serializable {

    private Distributivoclase selected;
    private Facultad facultad;
    private Carrera carrera;
    private Semestre semestre;
    private Periodo periodo;
    private Paralelo paralelo;

    public DistributivoclaseController() {
    }

    private DistributivoclaseFacade getFacade() {
        return ejbDistributivoclase;
    }

    public Distributivoclase getSelected() {
        return selected;
    }

    public void setSelected(Distributivoclase selected) {
        this.selected = selected;
    }

    public Facultad getFacultad() {
        return facultad;
    }

    public void setFacultad(Facultad facultad) {
        this.facultad = facultad;
    }

    public Carrera getCarrera() {
        return carrera;
    }

    public void setCarrera(Carrera carrera) {
        this.carrera = carrera;
    }

    public Semestre getSemestre() {
        return semestre;
    }

    public void setSemestre(Semestre semestre) {
        this.semestre = semestre;
    }

    public Periodo getPeriodo() {
        return periodo;
    }

    public void setPeriodo(Periodo periodo) {
        this.periodo = periodo;
    }

    public Paralelo getParalelo() {
        return paralelo;
    }

    public void setParalelo(Paralelo paralelo) {
        this.paralelo = paralelo;
    }

    public int count() {
        return getFacade().count();
    }

    public Distributivoclase prepareCreate() {
        selected = new Distributivoclase();
        return selected;
    }

    public void create() {
        try {
            getFacade().create(selected);
            JsfUtil.addSuccessMessage("Registro agregado correctamente");
            listDistributivoclase = null;
        } catch (EJBException e) {
            JsfUtil.addErrorMessage(e, defaultMsg);
        }
    }

    public void update() {
        try {
            getFacade().edit(selected);
            JsfUtil.addSuccessMessage("Datos editados");
            listDistributivoclase = null;
        } catch (EJBException e) {
            JsfUtil.addErrorMessage(e, defaultMsg);
        }
    }

    public void destroy() {
        try {
            getFacade().remove(selected);
            JsfUtil.addSuccessMessage("Registro eliminado correctamente");
            selected = null;
            listDistributivoclase = null;
        } catch (EJBException e) {
            JsfUtil.addErrorMessage(e, defaultMsg);
        }
    }

    public List<Distributivoclase> getItems() {
        if (listDistributivoclase == null) {
            listDistributivoclase = getFacade().findAll();
        }
        return listDistributivoclase;
    }

    public List<Carrera> getListCarrera() {
        if (listCarrera==null) {
            listCarrera = ejbCarrera.findAll();
        }
        return listCarrera;
    }

    public List<Distributivodocente> getListDistributivodocente() {
        if (listDistributivodocente==null) {
            listDistributivodocente = ejbDistributivodocente.findAll();
        }
        return listDistributivodocente;
    }

    public List<Facultad> getListFacultad() {
        if (listFacultad==null) {
            listFacultad = ejbFacultad.findAll();
        }
        return listFacultad;
    }

    public List<Paralelo> getListParalelo() {
        if (listParalelo==null) {
            listParalelo = new ArrayList<>();
        }
        return listParalelo;
    }

    public List<Periodo> getListPeriodo() {
        if (listPeriodo==null) {
            listPeriodo = ejbPeriodo.findAll();
        }
        return listPeriodo;
    }

    public List<Semestre> getListSemestre() {
        if (listSemestre==null) {
            listSemestre = ejbSemestre.findAll();
        }
        return listSemestre;
    }    

    public Distributivoclase getDistributivoclase(Object id) {
        return getFacade().find(id);
    }
    
    
    public void onFacultadChange() {
        filtrarCarrerasPor(facultad);
    }

    public void onCarreraChange() {
        FiltrarSemestresPor(carrera);
    }

    public void onSemestreChange() {
        FiltrarParalelosPor(semestre);
    }

    public void onPeriodoChange() {
        FiltrarParalelosPor(periodo);
    }

     public void onParaleloChange() {
         FiltrarDistClasePor(paralelo);
     }

    @FacesConverter(forClass = Distributivoclase.class)
    public static class DistributivoclaseControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            DistributivoclaseController controller = (DistributivoclaseController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "distributivoclaseController");
            return controller.getDistributivoclase(getKey(value));
        }

        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Integer value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Distributivoclase) {
                Distributivoclase o = (Distributivoclase) object;
                return getStringKey(o.getIddistributivoclase());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Distributivoclase.class.getName()});
                return null;
            }
        }

    }

}
