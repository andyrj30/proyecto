package com.proyecto.controller;

import com.proyecto.entities.Carrera;
import com.proyecto.controller.util.JsfUtil;
import com.proyecto.entities.Semestre;
import com.proyecto.model.CarreraFacadeLocal;
import com.proyecto.model.SemestreFacadeLocal;
import com.proyecto.websocket.WSEndpoint;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@javax.faces.bean.ManagedBean(name = "carreraController")
@javax.faces.bean.SessionScoped
public class CarreraController implements Serializable {

    @EJB
    protected CarreraFacadeLocal ejbCarrera;

    @EJB
    protected SemestreFacadeLocal ejbSemestre;

    private Carrera selected;
    private int semestres = 10;
    protected List<Carrera> listCarrera;
    protected String defaultMsg = "Ha ocurrido un error en la transacción";

    public CarreraController() {
    }

    private CarreraFacadeLocal getFacade() {
        return ejbCarrera;
    }

    public int getSemestres() {
        return semestres;
    }

    public void setSemestres(int semestres) {
        this.semestres = semestres;
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
            if (semestres >= 0) {
                Semestre semestre = new Semestre();
                semestre.setCarrera(selected);
                semestre.setSemestre("Primer semestre");
                semestre.setAbreviatura("I");
                ejbSemestre.create(semestre);
            }
            if (semestres >= 1) {
                Semestre semestre = new Semestre();
                semestre.setCarrera(selected);
                semestre.setSemestre("Segundo semestre");
                semestre.setAbreviatura("II");
                ejbSemestre.create(semestre);
            }
            if (semestres >= 3) {
                Semestre semestre = new Semestre();
                semestre.setCarrera(selected);
                semestre.setSemestre("Tercero semestre");
                semestre.setAbreviatura("III");
                ejbSemestre.create(semestre);
            }
            if (semestres >= 4) {
                Semestre semestre = new Semestre();
                semestre.setCarrera(selected);
                semestre.setSemestre("Cuarto semestre");
                semestre.setAbreviatura("IV");
                ejbSemestre.create(semestre);
            }
            if (semestres >= 5) {
                Semestre semestre = new Semestre();
                semestre.setCarrera(selected);
                semestre.setSemestre("Quinto semestre");
                semestre.setAbreviatura("V");
                ejbSemestre.create(semestre);
            }
            if (semestres >= 6) {
                Semestre semestre = new Semestre();
                semestre.setCarrera(selected);
                semestre.setSemestre("Sexto semestre");
                semestre.setAbreviatura("VI");
                ejbSemestre.create(semestre);
            }
            if (semestres >= 7) {
                Semestre semestre = new Semestre();
                semestre.setCarrera(selected);
                semestre.setSemestre("Septimo semestre");
                semestre.setAbreviatura("VII");
                ejbSemestre.create(semestre);
            }
            if (semestres >= 8) {
                Semestre semestre = new Semestre();
                semestre.setCarrera(selected);
                semestre.setSemestre("Octavo semestre");
                semestre.setAbreviatura("VIII");
                ejbSemestre.create(semestre);
            }
            if (semestres >= 9) {
                Semestre semestre = new Semestre();
                semestre.setCarrera(selected);
                semestre.setSemestre("Noveno semestre");
                semestre.setAbreviatura("IX");
                ejbSemestre.create(semestre);
            }
            if (semestres >= 10) {
                Semestre semestre = new Semestre();
                semestre.setCarrera(selected);
                semestre.setSemestre("Decimo semestre");
                semestre.setAbreviatura("X");
                ejbSemestre.create(semestre);
            }
            JsfUtil.addSuccessMessage("Registro agregado correctamente");
            listCarrera = null;
            WSEndpoint.notificar("carrera");
        } catch (EJBException e) {
            JsfUtil.addErrorMessage(e, defaultMsg);
        }
    }

    public void update() {
        try {
            getFacade().edit(selected);
            JsfUtil.addSuccessMessage("Datos editados");
            listCarrera = null;
            WSEndpoint.notificar("carrera");
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
            WSEndpoint.notificar("carrera");
        } catch (EJBException e) {
            JsfUtil.addErrorMessage(e, defaultMsg);
        }
    }

    public List<Carrera> getItems() {
        listCarrera = getFacade().findAll();
        return listCarrera;
    }

    public Carrera getCarrera(Object id) {
        return getFacade().find(id);
    }

    @FacesConverter(forClass = Carrera.class)
    public static class CarreraControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            CarreraController controller = (CarreraController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "carreraController");
            return controller.getCarrera(getKey(value));
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
            if (object instanceof Carrera) {
                Carrera o = (Carrera) object;
                return getStringKey(o.getCodcarrera());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Carrera.class.getName()});
                return null;
            }
        }

    }

}
