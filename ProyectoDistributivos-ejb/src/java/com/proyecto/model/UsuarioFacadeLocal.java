/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyecto.model;

import com.proyecto.entities.Docente;
import com.proyecto.entities.Usuario;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Andy
 */
@Local
public interface UsuarioFacadeLocal {

    void create(Usuario usuario);

    void edit(Usuario usuario);

    void remove(Usuario usuario);

    Usuario find(Object id);

    List<Usuario> findAll();

    List<Usuario> findRange(int[] range);

    int count();
    
    public List<Usuario> FindByDocente(Docente iddocente);

    public List<Usuario> FindByUsuarioContasena(String user, String pass);
    
}
