/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyecto.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Andy
 */
@Entity
@Table(name = "carrera")
@NamedQueries({
    @NamedQuery(name = "Carrera.findAll", query = "SELECT c FROM Carrera c"),
    @NamedQuery(name = "Carrera.findByCodcarrera", query = "SELECT c FROM Carrera c WHERE c.codcarrera = :codcarrera"),
    @NamedQuery(name = "Carrera.findByCarrera", query = "SELECT c FROM Carrera c WHERE c.carrera = :carrera")})
public class Carrera implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "codcarrera")
    private String codcarrera;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "carrera")
    private String carrera;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "carrera")
    private List<Detalledistributivo> detalledistributivoList;
    @JoinColumn(name = "facultad", referencedColumnName = "codfacultad")
    @ManyToOne(optional = false)
    private Facultad facultad;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "carrera")
    private List<Semestre> semestreList;

    public Carrera() {
    }

    public Carrera(String codcarrera) {
        this.codcarrera = codcarrera;
    }

    public Carrera(String codcarrera, String carrera) {
        this.codcarrera = codcarrera;
        this.carrera = carrera;
    }

    public String getCodcarrera() {
        return codcarrera;
    }

    public void setCodcarrera(String codcarrera) {
        this.codcarrera = codcarrera;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public List<Detalledistributivo> getDetalledistributivoList() {
        return detalledistributivoList;
    }

    public void setDetalledistributivoList(List<Detalledistributivo> detalledistributivoList) {
        this.detalledistributivoList = detalledistributivoList;
    }

    public Facultad getFacultad() {
        return facultad;
    }

    public void setFacultad(Facultad facultad) {
        this.facultad = facultad;
    }

    public List<Semestre> getSemestreList() {
        return semestreList;
    }

    public void setSemestreList(List<Semestre> semestreList) {
        this.semestreList = semestreList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codcarrera != null ? codcarrera.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Carrera)) {
            return false;
        }
        Carrera other = (Carrera) object;
        if ((this.codcarrera == null && other.codcarrera != null) || (this.codcarrera != null && !this.codcarrera.equals(other.codcarrera))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.proyecto.entities.Carrera[ codcarrera=" + codcarrera + " ]";
    }
    
}
