package com.proyecto.controller;

import com.proyecto.entities.*;
import com.proyecto.model.*;
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

    protected String defaultMsg = "Error de persistencia";
}
