/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyecto.model;

import com.proyecto.entities.Docente;
import com.proyecto.entities.Usuario;
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
public class DocenteFacade extends AbstractFacade<Docente> implements DocenteFacadeLocal {

    @PersistenceContext(unitName = "ProyectoDistributivos-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DocenteFacade() {
        super(Docente.class);
    }

    public List<Docente> FindByDocente(String cedula) {
        List<Docente> lista = null;
        try {
            TypedQuery<Docente> findByDocente;
            findByDocente = em.createNamedQuery("Docente.findByCedula", Docente.class);
            findByDocente.setParameter("cedula", cedula);
            lista = findByDocente.getResultList();
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
        return lista;
    }

}
