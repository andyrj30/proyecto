/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyecto.model;

import com.proyecto.entities.Distributivo;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Andy
 */
@Local
public interface DistributivoFacadeLocal {

    void create(Distributivo distributivo);

    void edit(Distributivo distributivo);

    void remove(Distributivo distributivo);

    Distributivo find(Object id);

    List<Distributivo> findAll();

    List<Distributivo> findRange(int[] range);

    int count();
    
}
