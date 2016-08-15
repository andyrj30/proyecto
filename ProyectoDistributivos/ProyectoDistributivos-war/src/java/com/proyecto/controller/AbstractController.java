package com.proyecto.controller;

import com.proyecto.model.*;
import javax.ejb.EJB;

public abstract class AbstractController {

    @EJB
    private AreaFacade ejbArea;

    @EJB
    private AulaFacade ejbAula;

    @EJB
    private CarreraFacade ejbCarrera;

    @EJB
    private ConocimientoFacade ejbConocimiento;

    @EJB
    private ContratoFacade ejbContrato;

    @EJB
    private DistributivoaulaFacade ejbDistributivoaula;

    @EJB
    private DistributivodocenteFacade ejbDistributivodocente;

    @EJB
    private DistributivoclaseFacade ejbDistributivoclase;

    @EJB
    private DocenteFacade ejbDocente;

    @EJB
    private FacultadFacade ejbFacultad;

    @EJB
    private HorarioFacade ejbHorario;

    @EJB
    private MateriaFacade ejbMateria;

    @EJB
    private ParaleloFacade ejbParalelo;

    @EJB
    private PeriodoFacade ejbPeriodo;

    @EJB
    private SemestreFacade ejbSemestre;

    @EJB
    private SubareaFacade ejbSubarea;

    @EJB
    private TituloFacade ejbTitulo;

    @EJB
    private UsuarioFacade ejbUsuario;
    
    
}
