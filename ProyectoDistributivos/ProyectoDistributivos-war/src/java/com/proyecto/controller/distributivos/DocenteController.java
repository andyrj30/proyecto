package com.proyecto.controller.distributivos;

import com.proyecto.entities.*;
import com.proyecto.model.CarreraFacade;
import com.proyecto.model.DistributivodocenteFacade;
import com.proyecto.model.DocenteFacade;
import com.proyecto.model.FacultadFacade;
import com.proyecto.model.ParaleloFacade;
import com.proyecto.model.PeriodoFacade;
import com.proyecto.model.SemestreFacade;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "docentedistController")
@SessionScoped
public class DocenteController {

    @EJB
    private DistributivodocenteFacade distFacade;

    @EJB
    private ParaleloFacade paraleloFacade;

    @EJB
    private PeriodoFacade periodoFacade;

    @EJB
    private SemestreFacade semestreFacade;

    @EJB
    private CarreraFacade carreraFacade;

    @EJB
    private FacultadFacade facultadFacade;

    @EJB
    private DocenteFacade docenteFacade;

    private List<Facultad> facultades;
    private List<Carrera> carreras;
    private List<Semestre> semestres;
    private List<Periodo> periodos;
    private List<Paralelo> paralelos;
    private List<Distributivodocente> dist;

    private Facultad facultad;
    private Carrera carrera;
    private Semestre semestre;
    private Periodo periodo;
    private Paralelo paralelo;

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

    public List<Facultad> getfacultades() {
        facultades = facultadFacade.findAll();
        return facultades;
    }

    public List<Carrera> getcarreras() {
        if (carreras == null) {
            carreras = carreraFacade.findAll();
        }
        return carreras;
    }

    public List<Semestre> getsemestres() {
        if (semestres == null) {
            semestres = semestreFacade.findAll();
        }
        return semestres;
    }

    public List<Periodo> getperiodos() {
        if (periodos == null) {
            periodos = periodoFacade.findAll();
        }
        return periodos;
    }

    public List<Paralelo> getparalelos() {
        if (paralelos == null) {
            paralelos = paraleloFacade.findAll();
        }
        return paralelos;
    }

    public List<Distributivodocente> getdist() {
        if (dist == null) {
            dist = new ArrayList<>();
        }
        return dist;
    }

    public void onFacultadChange() {
        carreras.clear();
        for (Carrera next : carreraFacade.findAll()) {
            if (next.getIdfacultad().getIdfacultad() == null ? facultad.getIdfacultad() == null : next.getIdfacultad().getIdfacultad().equals(facultad.getIdfacultad())) {
                carreras.add(next);
            }
        }
    }

    public void onCarreraChange() {
        semestres.clear();
        for (Semestre next : semestreFacade.findAll()) {
            if (next.getIdcarrera().getIdcarrera() == null ? carrera.getIdcarrera() == null : next.getIdcarrera().getIdcarrera().equals(carrera.getIdcarrera())) {
                semestres.add(next);
            }
        }
    }

    public void onSemestreChange() {
        paralelos.clear();
        periodos = periodoFacade.findAll();
        periodo = new Periodo();
    }

    public void onPeriodoChange() {
        paralelos.clear();
        paralelo = new Paralelo();
        for (Paralelo next : paraleloFacade.findAll()) {
            if (next.getIdperiodo().getIdperiodo() == null ? periodo.getIdperiodo() == null : next.getIdperiodo().getIdperiodo().equals(periodo.getIdperiodo())
                    && (next.getIdsemestre().getIdsemestre() == null ? semestre.getIdsemestre() == null : next.getIdsemestre().getIdsemestre().equals(semestre.getIdsemestre()))) {
                paralelos.add(next);
            }
        }
    }

    public void buscar() {
        dist.clear();
        for (Distributivodocente next : distFacade.findAll()) {
            if (next.getCodmateria().getIdsemestre().getIdsemestre() == null ? semestre.getIdsemestre() == null : next.getCodmateria().getIdsemestre().getIdsemestre().equals(semestre.getIdsemestre())) {
                if (next.getIdparalelo().getIdperiodo().getIdperiodo() == null ? periodo.getIdperiodo() == null : next.getIdparalelo().getIdperiodo().getIdperiodo().equals(periodo.getIdperiodo())) {
                    if (next.getIdparalelo().getIdparalelo() == null ? paralelo.getIdparalelo() == null : next.getIdparalelo().getIdparalelo().equals(paralelo.getIdparalelo())) {
                        dist.add(next);
                    }
                }
            }
        }
    }

    public void reset() {
        facultad = new Facultad();
        onFacultadChange();
        facultades.clear();
        carreras.clear();
        semestres.clear();
        periodos.clear();
        paralelos.clear();
    }

    public DocenteController() {
    }

}
