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
public class UsuarioFacade extends AbstractFacade<Usuario> implements UsuarioFacadeLocal {

    @PersistenceContext(unitName = "ProyectoDistributivos-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }

    public List<Usuario> FindByDocente(Docente iddocente) {
        List<Usuario> lista = null;
        try {
            TypedQuery<Usuario> findByDocente;
            findByDocente = em.createNamedQuery("Usuario.findByDocente", Usuario.class);
            findByDocente.setParameter("docente", iddocente);
            lista = findByDocente.getResultList();
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
        return lista;
    }

    public List<Usuario> FindByUsuarioContasena(String user, String pass) {
        List<Usuario> lista = null;
        try {
            TypedQuery<Usuario> findByDocente;
            findByDocente = em.createNamedQuery("Usuario.findByUsuarioContasena", Usuario.class);
            findByDocente.setParameter("usuario", user);
            findByDocente.setParameter("contasena", pass);
            lista = findByDocente.getResultList();
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
        return lista;
    }
}
