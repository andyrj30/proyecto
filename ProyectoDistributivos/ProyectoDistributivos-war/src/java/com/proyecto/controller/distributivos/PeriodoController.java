/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyecto.controller.distributivos;

import com.proyecto.entities.Periodo;
import com.proyecto.model.PeriodoFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;

/**
 *
 * @author Andy
 */
@Named(value = "periododistController")
@SessionScoped
public class PeriodoController implements Serializable {

    @EJB
    private PeriodoFacade periodoFacade;    
    
    public PeriodoController() {
    }
    
    public List<Periodo> findAll(){
        return periodoFacade.findAll();
    }
}
