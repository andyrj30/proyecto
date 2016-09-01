/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyecto.model;

import com.proyecto.entities.Paralelo;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Andy
 */
@Local
public interface ParaleloFacadeLocal {

    void create(Paralelo paralelo);

    void edit(Paralelo paralelo);

    void remove(Paralelo paralelo);

    Paralelo find(Object id);

    List<Paralelo> findAll();

    List<Paralelo> findRange(int[] range);

    int count();
    
}
