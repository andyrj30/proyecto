/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyecto.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Andy
 */
@Entity
@Table(name = "detalledistributivo")
@NamedQueries({
    @NamedQuery(name = "Detalledistributivo.findAll", query = "SELECT d FROM Detalledistributivo d"),
    @NamedQuery(name = "Detalledistributivo.findByIddetalledistributivo", query = "SELECT d FROM Detalledistributivo d WHERE d.iddetalledistributivo = :iddetalledistributivo"),
    @NamedQuery(name = "Detalledistributivo.findByHoras", query = "SELECT d FROM Detalledistributivo d WHERE d.horas = :horas")})
public class Detalledistributivo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "iddetalledistributivo")
    private Integer iddetalledistributivo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "horas")
    private int horas;
    @JoinColumn(name = "carrera", referencedColumnName = "codcarrera")
    @ManyToOne
    private Carrera carrera;
    @JoinColumn(name = "distributivo", referencedColumnName = "iddistributivo")
    @ManyToOne
    private Distributivo distributivo;
    @JoinColumn(name = "facultad", referencedColumnName = "codfacultad")
    @ManyToOne(optional = false)
    private Facultad facultad;
    @JoinColumn(name = "materia", referencedColumnName = "codmateria")
    @ManyToOne
    private Materia materia;
    @JoinColumn(name = "otrasactividades", referencedColumnName = "idotrasactividades")
    @ManyToOne
    private Otrasactividades otrasactividades;
    @JoinColumn(name = "paralelo", referencedColumnName = "codparalelo")
    @ManyToOne
    private Paralelo paralelo;
    @JoinColumn(name = "semestre", referencedColumnName = "idsemestre")
    @ManyToOne
    private Semestre semestre;

    public Detalledistributivo() {
    }

    public Detalledistributivo(Integer iddetalledistributivo) {
        this.iddetalledistributivo = iddetalledistributivo;
    }

    public Detalledistributivo(Integer iddetalledistributivo, int horas) {
        this.iddetalledistributivo = iddetalledistributivo;
        this.horas = horas;
    }

    public Integer getIddetalledistributivo() {
        return iddetalledistributivo;
    }

    public void setIddetalledistributivo(Integer iddetalledistributivo) {
        this.iddetalledistributivo = iddetalledistributivo;
    }

    public int getHoras() {
        return horas;
    }

    public void setHoras(int horas) {
        this.horas = horas;
    }

    public Carrera getCarrera() {
        return carrera;
    }

    public void setCarrera(Carrera carrera) {
        this.carrera = carrera;
    }

    public Distributivo getDistributivo() {
        return distributivo;
    }

    public void setDistributivo(Distributivo distributivo) {
        this.distributivo = distributivo;
    }

    public Facultad getFacultad() {
        return facultad;
    }

    public void setFacultad(Facultad facultad) {
        this.facultad = facultad;
    }

    public Materia getMateria() {
        return materia;
    }

    public void setMateria(Materia materia) {
        this.materia = materia;
    }

    public Otrasactividades getOtrasactividades() {
        return otrasactividades;
    }

    public void setOtrasactividades(Otrasactividades otrasactividades) {
        this.otrasactividades = otrasactividades;
    }

    public Paralelo getParalelo() {
        return paralelo;
    }

    public void setParalelo(Paralelo paralelo) {
        this.paralelo = paralelo;
    }

    public Semestre getSemestre() {
        return semestre;
    }

    public void setSemestre(Semestre semestre) {
        this.semestre = semestre;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iddetalledistributivo != null ? iddetalledistributivo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Detalledistributivo)) {
            return false;
        }
        Detalledistributivo other = (Detalledistributivo) object;
        if ((this.iddetalledistributivo == null && other.iddetalledistributivo != null) || (this.iddetalledistributivo != null && !this.iddetalledistributivo.equals(other.iddetalledistributivo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.proyecto.entities.Detalledistributivo[ iddetalledistributivo=" + iddetalledistributivo + " ]";
    }
    
}
