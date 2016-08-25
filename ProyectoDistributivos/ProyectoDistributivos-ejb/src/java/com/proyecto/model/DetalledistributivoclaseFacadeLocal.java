/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyecto.model;

import com.proyecto.entities.Detalledistributivoclase;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Andy
 */
@Local
public interface DetalledistributivoclaseFacadeLocal {

    void create(Detalledistributivoclase detalledistributivoclase);

    void edit(Detalledistributivoclase detalledistributivoclase);

    void remove(Detalledistributivoclase detalledistributivoclase);

    Detalledistributivoclase find(Object id);

    List<Detalledistributivoclase> findAll();

    List<Detalledistributivoclase> findRange(int[] range);

    int count();
    
}
