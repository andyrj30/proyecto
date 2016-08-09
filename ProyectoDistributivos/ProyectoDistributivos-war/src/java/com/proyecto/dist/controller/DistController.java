/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyecto.dist.controller;

import com.proyecto.controller.CarreraController;
import com.proyecto.controller.FacultadController;
import com.proyecto.entities.Carrera;
import com.proyecto.entities.Facultad;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Andy
 */

@ManagedBean(name = "DistController")
@SessionScoped
public class DistController implements Serializable {

    List<Facultad> facultades;
    String facultad;
    List<Carrera> carreras;
    String carrera;
    @ManagedProperty("#{facultadController}")
    FacultadController FC;
    @ManagedProperty("#{carreraController}")
    CarreraController CC;

    public FacultadController getFC() {
        return FC;
    }

    public void setFC(FacultadController FC) {
        this.FC = FC;
    }

    public CarreraController getCC() {
        return CC;
    }

    public void setCC(CarreraController CC) {
        this.CC = CC;
    }
    
    
    
    public List<Facultad> getFacultades() {
        return facultades;
    }

    public void setFacultades(List<Facultad> facultades) {
        this.facultades = facultades;
    }

    public String getFacultad() {
        return facultad;
    }

    public void setFacultad(String facultad) {
        this.facultad = facultad;
    }

    public List<Carrera> getCarreras() {
        return carreras;
    }

    public void setCarreras(List<Carrera> carreras) {
        this.carreras = carreras;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public void onFacultadChange() {

    }

    public void onCarreraChange() {

    }

    public DistController() {
        FC.count();
    }

}
