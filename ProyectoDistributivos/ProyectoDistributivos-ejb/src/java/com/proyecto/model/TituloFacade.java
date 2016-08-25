/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyecto.model;

import com.proyecto.entities.Titulo;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Andy
 */
@Stateless
public class TituloFacade extends AbstractFacade<Titulo> implements TituloFacadeLocal {

    @PersistenceContext(unitName = "ProyectoDistributivos-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TituloFacade() {
        super(Titulo.class);
    }
    
}
