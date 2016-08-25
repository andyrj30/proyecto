/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyecto.model;

import com.proyecto.entities.Semestre;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Andy
 */
@Local
public interface SemestreFacadeLocal {

    void create(Semestre semestre);

    void edit(Semestre semestre);

    void remove(Semestre semestre);

    Semestre find(Object id);

    List<Semestre> findAll();

    List<Semestre> findRange(int[] range);

    int count();
    
}
