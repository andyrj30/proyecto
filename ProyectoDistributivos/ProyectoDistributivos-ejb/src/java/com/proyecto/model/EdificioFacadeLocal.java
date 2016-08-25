/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyecto.model;

import com.proyecto.entities.Edificio;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Andy
 */
@Local
public interface EdificioFacadeLocal {

    void create(Edificio edificio);

    void edit(Edificio edificio);

    void remove(Edificio edificio);

    Edificio find(Object id);

    List<Edificio> findAll();

    List<Edificio> findRange(int[] range);

    int count();
    
}
