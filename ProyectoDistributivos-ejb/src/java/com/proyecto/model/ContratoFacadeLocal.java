/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyecto.model;

import com.proyecto.entities.Contrato;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Andy
 */
@Local
public interface ContratoFacadeLocal {

    void create(Contrato contrato);

    void edit(Contrato contrato);

    void remove(Contrato contrato);

    Contrato find(Object id);

    List<Contrato> findAll();

    List<Contrato> findRange(int[] range);

    int count();
    
}
