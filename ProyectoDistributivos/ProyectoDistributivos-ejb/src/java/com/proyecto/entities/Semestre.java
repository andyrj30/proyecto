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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "semestre")
@NamedQueries({
    @NamedQuery(name = "Semestre.findAll", query = "SELECT s FROM Semestre s"),
    @NamedQuery(name = "Semestre.findByIdsemestre", query = "SELECT s FROM Semestre s WHERE s.idsemestre = :idsemestre"),
    @NamedQuery(name = "Semestre.findBySemestre", query = "SELECT s FROM Semestre s WHERE s.semestre = :semestre")})
public class Semestre implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idsemestre")
    private Integer idsemestre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "semestre")
    private String semestre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idsemestre")
    private List<Paralelo> paraleloList;
    @OneToMany(mappedBy = "idsemestre")
    private List<Materia> materiaList;
    @JoinColumn(name = "idcarrera", referencedColumnName = "idcarrera")
    @ManyToOne(optional = false)
    private Carrera idcarrera;

    public Semestre() {
    }

    public Semestre(Integer idsemestre) {
        this.idsemestre = idsemestre;
    }

    public Semestre(Integer idsemestre, String semestre) {
        this.idsemestre = idsemestre;
        this.semestre = semestre;
    }

    public Integer getIdsemestre() {
        return idsemestre;
    }

    public void setIdsemestre(Integer idsemestre) {
        this.idsemestre = idsemestre;
    }

    public String getSemestre() {
        return semestre;
    }

    public void setSemestre(String semestre) {
        this.semestre = semestre;
    }

    public List<Paralelo> getParaleloList() {
        return paraleloList;
    }

    public void setParaleloList(List<Paralelo> paraleloList) {
        this.paraleloList = paraleloList;
    }

    public List<Materia> getMateriaList() {
        return materiaList;
    }

    public void setMateriaList(List<Materia> materiaList) {
        this.materiaList = materiaList;
    }

    public Carrera getIdcarrera() {
        return idcarrera;
    }

    public void setIdcarrera(Carrera idcarrera) {
        this.idcarrera = idcarrera;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idsemestre != null ? idsemestre.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Semestre)) {
            return false;
        }
        Semestre other = (Semestre) object;
        if ((this.idsemestre == null && other.idsemestre != null) || (this.idsemestre != null && !this.idsemestre.equals(other.idsemestre))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.proyecto.entities.Semestre[ idsemestre=" + idsemestre + " ]";
    }
    
}
