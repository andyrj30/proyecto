/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyecto.model;

import com.proyecto.entities.Distaula;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Andy
 */
@Local
public interface DistaulaFacadeLocal {

    void create(Distaula distaula);

    void edit(Distaula distaula);

    void remove(Distaula distaula);

    Distaula find(Object id);

    List<Distaula> findAll();

    List<Distaula> findRange(int[] range);

    int count();
    
}
