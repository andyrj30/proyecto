package com.proyecto.controller;

import com.proyecto.entities.*;
import com.proyecto.model.*;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;

public abstract class AbstractController {

    @EJB
    protected AreaFacade ejbArea;

    @EJB
    protected AulaFacade ejbAula;

    @EJB
    protected CarreraFacade ejbCarrera;

    @EJB
    protected ConocimientoFacade ejbConocimiento;

    @EJB
    protected ContratoFacade ejbContrato;

    @EJB
    protected DistributivoaulaFacade ejbDistributivoaula;

    @EJB
    protected DistributivodocenteFacade ejbDistributivodocente;

    @EJB
    protected DistributivoclaseFacade ejbDistributivoclase;

    @EJB
    protected DocenteFacade ejbDocente;

    @EJB
    protected EdificioFacade ejbEdificio;

    @EJB
    protected FacultadFacade ejbFacultad;

    @EJB
    protected HorarioFacade ejbHorario;

    @EJB
    protected MateriaFacade ejbMateria;

    @EJB
    protected ParaleloFacade ejbParalelo;

    @EJB
    protected PeriodoFacade ejbPeriodo;

    @EJB
    protected SemestreFacade ejbSemestre;

    @EJB
    protected SubareaFacade ejbSubarea;

    @EJB
    protected TituloFacade ejbTitulo;

    @EJB
    protected UsuarioFacade ejbUsuario;

    protected List<Area> listArea;
    protected List<Aula> listAula;
    protected List<Carrera> listCarrera;
    protected List<Conocimiento> listConocimiento;
    protected List<Contrato> listContrato;
    protected List<Distributivoaula> listDistributivoaula;
    protected List<Distributivoclase> listDistributivoclase;
    protected List<Distributivodocente> listDistributivodocente;
    protected List<Docente> listDocente;
    protected List<Facultad> listFacultad;
    protected List<Horario> listHorario;
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
            if (next.getIdfacultad().getIdfacultad() == null ? facultad.getIdfacultad() == null : next.getIdfacultad().getIdfacultad().equals(facultad.getIdfacultad())) {
                listCarrera.add(next);
            }
        }
    }

    public void FiltrarSemestresPor(Carrera carrera) {
        listSemestre.clear();
        for (Semestre next : ejbSemestre.findAll()) {
            if (next.getIdcarrera().getIdcarrera() == null ? carrera.getIdcarrera() == null : next.getIdcarrera().getIdcarrera().equals(carrera.getIdcarrera())) {
                listSemestre.add(next);
            }
        }
    }

    public void FiltrarParalelosPor(Semestre semestre) {
        listParalelo.clear();
        for (Paralelo next : ejbParalelo.findAll()) {
            if (next.getIdsemestre().getIdsemestre() == null ? semestre.getIdsemestre() == null : next.getIdsemestre().getIdsemestre().equals(semestre.getIdsemestre())) {
                listParalelo.add(next);
            }
        }
    }

    public void FiltrarParalelosPor(Periodo periodo) {
        listParalelo.clear();
        for (Paralelo next : ejbParalelo.findAll()) {
            if (next.getIdperiodo().getIdperiodo() == null ? periodo.getIdperiodo() == null : next.getIdperiodo().getIdperiodo().equals(periodo.getIdperiodo())) {
                listParalelo.add(next);
            }
        }
    }

    public void FiltrarParalelosPor(Periodo periodo, Semestre semestre) {
        listParalelo.clear();
        for (Paralelo next : ejbParalelo.findAll()) {
            if (next.getIdperiodo().getIdperiodo() == null ? periodo.getIdperiodo() == null : next.getIdperiodo().getIdperiodo().equals(periodo.getIdperiodo())
                    && next.getIdsemestre().getIdsemestre() == null ? semestre.getIdsemestre() == null : next.getIdsemestre().getIdsemestre().equals(semestre.getIdsemestre())) {
                listParalelo.add(next);
            }
        }
    }

    public void FiltrarDistClasePor(Paralelo paralelo) {
        listDistributivoclase.clear();
        for (Distributivoclase next : listDistributivoclase) {
            if (next.getIdparalelo().getIdparalelo() == null ? paralelo.getIdparalelo() == null : next.getIdparalelo().getIdparalelo().equals(paralelo.getIdparalelo())) {
                listDistributivoclase.add(next);
            }
        }
    }

    public void reset() {
        listArea = null;
        listAula = null;
        listCarrera = null;
        listConocimiento = null;
        listContrato = null;
        listDistributivoaula = null;
        listDistributivoclase = null;
        listDistributivodocente = null;
        listDocente = null;
        listFacultad = null;
        listHorario = null;
        listMateria = null;
        listParalelo = null;
        listPeriodo = null;
        listSemestre = null;
        listSubarea = null;
        listTitulo = null;
        listUsuario = null;
    }

}
