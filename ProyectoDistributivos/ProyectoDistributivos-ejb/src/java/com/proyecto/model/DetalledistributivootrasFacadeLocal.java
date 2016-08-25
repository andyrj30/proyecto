/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyecto.model;

import com.proyecto.entities.Detalledistributivootras;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Andy
 */
@Local
public interface DetalledistributivootrasFacadeLocal {

    void create(Detalledistributivootras detalledistributivootras);

    void edit(Detalledistributivootras detalledistributivootras);

    void remove(Detalledistributivootras detalledistributivootras);

    Detalledistributivootras find(Object id);

    List<Detalledistributivootras> findAll();

    List<Detalledistributivootras> findRange(int[] range);

    int count();
    
}
