/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyecto.model;

import com.proyecto.entities.Horarioclase;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Andy
 */
@Local
public interface HorarioclaseFacadeLocal {

    void create(Horarioclase horarioclase);

    void edit(Horarioclase horarioclase);

    void remove(Horarioclase horarioclase);

    Horarioclase find(Object id);

    List<Horarioclase> findAll();

    List<Horarioclase> findRange(int[] range);

    int count();
    
}
