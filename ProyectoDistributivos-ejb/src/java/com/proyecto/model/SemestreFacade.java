/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyecto.model;

import com.proyecto.entities.Carrera;
import com.proyecto.entities.Semestre;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Andy
 */
@Stateless
public class SemestreFacade extends AbstractFacade<Semestre> implements SemestreFacadeLocal {

    @PersistenceContext(unitName = "ProyectoDistributivos-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SemestreFacade() {
        super(Semestre.class);
    }

    @Override
    public List<Semestre> findByCarrera(Carrera carrera) {
       List<Semestre> lista = null;
        try {
            TypedQuery<Semestre> findByCarrera;
            findByCarrera = em.createNamedQuery("Semestre.findByCarrera", Semestre.class);
            findByCarrera.setParameter("carrera", carrera);
            lista = findByCarrera.getResultList();
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
        return lista;
    }
    
}
