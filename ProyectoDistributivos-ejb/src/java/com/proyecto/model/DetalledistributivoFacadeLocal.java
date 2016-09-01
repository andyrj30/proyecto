/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyecto.model;

import com.proyecto.entities.Detalledistributivo;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Andy
 */
@Local
public interface DetalledistributivoFacadeLocal {

    void create(Detalledistributivo detalledistributivo);

    void edit(Detalledistributivo detalledistributivo);

    void remove(Detalledistributivo detalledistributivo);

    Detalledistributivo find(Object id);

    List<Detalledistributivo> findAll();

    List<Detalledistributivo> findRange(int[] range);

    int count();
    
}
