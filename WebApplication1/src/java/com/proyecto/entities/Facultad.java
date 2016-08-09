/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyecto.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
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
    @NamedQuery(name = "Facultad.findByIdfacultad", query = "SELECT f FROM Facultad f WHERE f.idfacultad = :idfacultad"),
    @NamedQuery(name = "Facultad.findByFacultad", query = "SELECT f FROM Facultad f WHERE f.facultad = :facultad")})
public class Facultad implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "idfacultad")
    private String idfacultad;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "facultad")
    private String facultad;
    @OneToMany(mappedBy = "idfacultad")
    private List<Carrera> carreraList;

    public Facultad() {
    }

    public Facultad(String idfacultad) {
        this.idfacultad = idfacultad;
    }

    public Facultad(String idfacultad, String facultad) {
        this.idfacultad = idfacultad;
        this.facultad = facultad;
    }

    public String getIdfacultad() {
        return idfacultad;
    }

    public void setIdfacultad(String idfacultad) {
        this.idfacultad = idfacultad;
    }

    public String getFacultad() {
        return facultad;
    }

    public void setFacultad(String facultad) {
        this.facultad = facultad;
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
        hash += (idfacultad != null ? idfacultad.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Facultad)) {
            return false;
        }
        Facultad other = (Facultad) object;
        if ((this.idfacultad == null && other.idfacultad != null) || (this.idfacultad != null && !this.idfacultad.equals(other.idfacultad))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.proyecto.entities.Facultad[ idfacultad=" + idfacultad + " ]";
    }
    
}
