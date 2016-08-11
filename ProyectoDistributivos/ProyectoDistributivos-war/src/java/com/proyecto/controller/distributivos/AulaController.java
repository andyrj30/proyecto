package com.proyecto.controller.distributivos;

import com.proyecto.entities.Periodo;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "auladistController")
@SessionScoped
public class AulaController {

    private List<String> edificios;
    private String edificio;
    private Periodo periodo;

    public Periodo getPeriodo() {
        return periodo;
    }

    public void setPeriodo(Periodo periodo) {
        this.periodo = periodo;
    }

    public String getEdificio() {
        return edificio;
    }

    public void setEdificio(String edificio) {
        this.edificio = edificio;
    }


    public List<String> getedificios() {
        edificios = new ArrayList<>();
        edificios.add("Instituto de informatica");
        edificios.add("Facultad  de Ciencias de la Ingeniria");
        return edificios;
    }

    public AulaController() {
    }

}
