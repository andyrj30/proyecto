/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyecto.controller.util;

import com.proyecto.entities.*;
import com.proyecto.model.*;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;

/**
 *
 * @author Andy
 */
@ManagedBean(name = "eventosController")
@ViewScoped
public class EventosController {

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
    
    
    private Facultad facultad;
    private Carrera carrera;
    private Semestre semestre;
    private Periodo periodo;
    private Paralelo paralelo;

    private List<Facultad> facultades;
    private List<Carrera> carreras;
    private List<Semestre> semestres;
    private List<Periodo> periodos;
    private List<Paralelo> paralelos;
    private List<Docente> docentes;
    
    

    public List<Facultad> getFacultades() {
        if (facultades == null) {
            facultades = facultadFacade.findAll();
        }
        return facultades;
    }

    public List<Carrera> getCarreras() {
        if (carreras == null) {
            carreras = carreraFacade.findAll();
        }
        return carreras;
    }

    public List<Semestre> getSemestres() {
        if (semestres == null) {
            semestres = semestreFacade.findAll();
        }
        return semestres;
    }

    public List<Periodo> getPeriodos() {
        if (periodos == null) {
            periodos = periodoFacade.findAll();
        }
        return periodos;
    }

    public List<Paralelo> getParalelos() {
        if (paralelos == null) {
            paralelos = paraleloFacade.findAll();
        }
        return paralelos;
    }

    public List<Docente> getDocentes() {
        if (docentes == null) {
            docentes = docenteFacade.findAll();
        }
        return docentes;
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

    public void reset() {
        facultad = new Facultad();
        onFacultadChange();
        facultades.clear();
        carreras.clear();
        semestres.clear();
        periodos.clear();
        paralelos.clear();
    }

    public EventosController() {
    }

}
