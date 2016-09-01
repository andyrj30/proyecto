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
@Table(name = "facultad")
@NamedQueries({
    @NamedQuery(name = "Facultad.findAll", query = "SELECT f FROM Facultad f"),
    @NamedQuery(name = "Facultad.findByCodfacultad", query = "SELECT f FROM Facultad f WHERE f.codfacultad = :codfacultad"),
    @NamedQuery(name = "Facultad.findByFacultad", query = "SELECT f FROM Facultad f WHERE f.facultad = :facultad")})
public class Facultad implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "codfacultad")
    private String codfacultad;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "facultad")
    private String facultad;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "facultad")
    private List<Detalledistributivo> detalledistributivoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "facultad")
    private List<Carrera> carreraList;

    public Facultad() {
    }

    public Facultad(String codfacultad) {
        this.codfacultad = codfacultad;
    }

    public Facultad(String codfacultad, String facultad) {
        this.codfacultad = codfacultad;
        this.facultad = facultad;
    }

    public String getCodfacultad() {
        return codfacultad;
    }

    public void setCodfacultad(String codfacultad) {
        this.codfacultad = codfacultad;
    }

    public String getFacultad() {
        return facultad;
    }

    public void setFacultad(String facultad) {
        this.facultad = facultad;
    }

    public List<Detalledistributivo> getDetalledistributivoList() {
        return detalledistributivoList;
    }

    public void setDetalledistributivoList(List<Detalledistributivo> detalledistributivoList) {
        this.detalledistributivoList = detalledistributivoList;
    }

    public List<Carrera> getCarreraList() {
        return carreraList;
    }

    public void setCarreraList(List<Carrera> carreraList) {
        this.carreraList = carreraList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codfacultad != null ? codfacultad.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Facultad)) {
            return false;
        }
        Facultad other = (Facultad) object;
        if ((this.codfacultad == null && other.codfacultad != null) || (this.codfacultad != null && !this.codfacultad.equals(other.codfacultad))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.proyecto.entities.Facultad[ codfacultad=" + codfacultad + " ]";
    }
    
}
