/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyecto.model;

import com.proyecto.entities.Otrasactividades;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Andy
 */
@Local
public interface OtrasactividadesFacadeLocal {

    void create(Otrasactividades otrasactividades);

    void edit(Otrasactividades otrasactividades);

    void remove(Otrasactividades otrasactividades);

    Otrasactividades find(Object id);

    List<Otrasactividades> findAll();

    List<Otrasactividades> findRange(int[] range);

    int count();
    
}
