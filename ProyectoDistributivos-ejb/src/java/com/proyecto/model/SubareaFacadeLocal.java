/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyecto.model;

import com.proyecto.entities.Subarea;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Andy
 */
@Local
public interface SubareaFacadeLocal {

    void create(Subarea subarea);

    void edit(Subarea subarea);

    void remove(Subarea subarea);

    Subarea find(Object id);

    List<Subarea> findAll();

    List<Subarea> findRange(int[] range);

    int count();
    
}
