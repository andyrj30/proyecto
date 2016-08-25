package com.proyecto.controller;

import com.proyecto.entities.*;
import com.proyecto.model.*;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;

public abstract class AbstractController {

    @EJB
    protected AreaFacadeLocal ejbArea;

    @EJB
    protected AulaFacadeLocal ejbAula;

    @EJB
    protected CarreraFacadeLocal ejbCarrera;

    @EJB
    protected ConocimientoFacadeLocal ejbConocimiento;

    @EJB
    protected ContratoFacadeLocal ejbContrato;

    @EJB
    protected DistaulaFacadeLocal ejbDistaula;

    @EJB
    protected DocenteFacadeLocal ejbDocente;

    @EJB
    protected EdificioFacadeLocal ejbEdificio;

    @EJB
    protected FacultadFacadeLocal ejbFacultad;

    @EJB
    protected MateriaFacadeLocal ejbMateria;

    @EJB
    protected ParaleloFacadeLocal ejbParalelo;

    @EJB
    protected PeriodoFacadeLocal ejbPeriodo;

    @EJB
    protected SemestreFacadeLocal ejbSemestre;

    @EJB
    protected SubareaFacadeLocal ejbSubarea;

    @EJB
    protected TituloFacadeLocal ejbTitulo;

    @EJB
    protected UsuarioFacadeLocal ejbUsuario;

    protected List<Area> listArea;
    protected List<Aula> listAula;
    protected List<Carrera> listCarrera;
    protected List<Conocimiento> listConocimiento;
    protected List<Contrato> listContrato;
    protected List<Distaula> listDistaula;
    protected List<Docente> listDocente;
    protected List<Facultad> listFacultad;
    protected List<Materia> listMateria;
    protected List<Paralelo> listParalelo;
    protected List<Periodo> listPeriodo;
    protected List<Semestre> listSemestre;
    protected List<Subarea> listSubarea;
    protected List<Titulo> listTitulo;
    protected List<Usuario> listUsuario;
    protected List<Edificio> listEdificio;

    protected String defaultMsg = "Ha ocurrido un error en la transacción";

    public void filtrarCarrerasPor(Facultad facultad) {
        listCarrera.clear();
        for (Carrera next : ejbCarrera.findAll()) {
            if (next.getFacultad().getCodfacultad()== null ? facultad.getCodfacultad()== null : next.getFacultad().getCodfacultad().equals(facultad.getCodfacultad())) {
                listCarrera.add(next);
            }
        }
    }

    public void FiltrarSemestresPor(Carrera carrera) {
        listSemestre.clear();
        for (Semestre next : ejbSemestre.findAll()) {
            if (next.getCarrera().getCodcarrera()== null ? carrera.getCodcarrera()== null : next.getCarrera().getCodcarrera().equals(carrera.getCodcarrera())) {
                listSemestre.add(next);
            }
        }
    }
//
//    public void FiltrarParalelosPor(Semestre semestre) {
//        listParalelo.clear();
//        for (Paralelo next : ejbParalelo.findAll()) {
//            if (next.getIdsemestre().getIdsemestre() == null ? semestre.getIdsemestre() == null : next.getIdsemestre().getIdsemestre().equals(semestre.getIdsemestre())) {
//                listParalelo.add(next);
//            }
//        }
//    }
//
//    public void FiltrarParalelosPor(Periodo periodo) {
//        listParalelo.clear();
//        for (Paralelo next : ejbParalelo.findAll()) {
//            if (next.getIdperiodo().getIdperiodo() == null ? periodo.getIdperiodo() == null : next.getIdperiodo().getIdperiodo().equals(periodo.getIdperiodo())) {
//                listParalelo.add(next);
//            }
//        }
//    }
//
//    public void FiltrarParalelosPor(Periodo periodo, Semestre semestre) {
//        listParalelo.clear();
//        for (Paralelo next : ejbParalelo.findAll()) {
//            if (next.getIdperiodo().getIdperiodo() == null ? periodo.getIdperiodo() == null : next.getIdperiodo().getIdperiodo().equals(periodo.getIdperiodo())
//                    && next.getIdsemestre().getIdsemestre() == null ? semestre.getIdsemestre() == null : next.getIdsemestre().getIdsemestre().equals(semestre.getIdsemestre())) {
//                listParalelo.add(next);
//            }
//        }
//    }
//
//    public void FiltrarDistClasePor(Paralelo paralelo) {
//        listDistributivoclase.clear();
//        for (Distributivoclase next : listDistributivoclase) {
//            if (next.getIdparalelo().getIdparalelo() == null ? paralelo.getIdparalelo() == null : next.getIdparalelo().getIdparalelo().equals(paralelo.getIdparalelo())) {
//                listDistributivoclase.add(next);
//            }
//        }
//    }
//
//    public void reset() {
//        listArea = null;
//        listAula = null;
//        listCarrera = null;
//        listConocimiento = null;
//        listContrato = null;
//        listDistributivoaula = null;
//        listDistributivoclase = null;
//        listDistributivodocente = null;
//        listDocente = null;
//        listFacultad = null;
//        listHorario = null;
//        listMateria = null;
//        listParalelo = null;
//        listPeriodo = null;
//        listSemestre = null;
//        listSubarea = null;
//        listTitulo = null;
//        listUsuario = null;
//    }

}
