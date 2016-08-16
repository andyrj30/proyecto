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
import javax.validation.constraints.Size;

/**
 *
 * @author Andy
 */
@Entity
@Table(name = "contrato")
@NamedQueries({
    @NamedQuery(name = "Contrato.findAll", query = "SELECT c FROM Contrato c"),
    @NamedQuery(name = "Contrato.findByIdcontrato", query = "SELECT c FROM Contrato c WHERE c.idcontrato = :idcontrato"),
    @NamedQuery(name = "Contrato.findByFuncion", query = "SELECT c FROM Contrato c WHERE c.funcion = :funcion"),
    @NamedQuery(name = "Contrato.findByDedicacion", query = "SELECT c FROM Contrato c WHERE c.dedicacion = :dedicacion"),
    @NamedQuery(name = "Contrato.findByTotalhoras", query = "SELECT c FROM Contrato c WHERE c.totalhoras = :totalhoras")})
public class Contrato implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idcontrato")
    private Integer idcontrato;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "funcion")
    private String funcion;
    @Size(max = 50)
    @Column(name = "dedicacion")
    private String dedicacion;
    @Column(name = "totalhoras")
    private Integer totalhoras;
    @JoinColumn(name = "cedula", referencedColumnName = "cedula")
    @ManyToOne(optional = false)
    private Docente cedula;

    public Contrato() {
    }

    public Contrato(Integer idcontrato) {
        this.idcontrato = idcontrato;
    }

    public Contrato(Integer idcontrato, String funcion) {
        this.idcontrato = idcontrato;
        this.funcion = funcion;
    }

    public Integer getIdcontrato() {
        return idcontrato;
    }

    public void setIdcontrato(Integer idcontrato) {
        this.idcontrato = idcontrato;
    }

    public String getFuncion() {
        return funcion;
    }

    public void setFuncion(String funcion) {
        this.funcion = funcion;
    }

    public String getDedicacion() {
        return dedicacion;
    }

    public void setDedicacion(String dedicacion) {
        this.dedicacion = dedicacion;
    }

    public Integer getTotalhoras() {
        return totalhoras;
    }

    public void setTotalhoras(Integer totalhoras) {
        this.totalhoras = totalhoras;
    }

    public Docente getCedula() {
        return cedula;
    }

    public void setCedula(Docente cedula) {
        this.cedula = cedula;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcontrato != null ? idcontrato.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Contrato)) {
            return false;
        }
        Contrato other = (Contrato) object;
        if ((this.idcontrato == null && other.idcontrato != null) || (this.idcontrato != null && !this.idcontrato.equals(other.idcontrato))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.proyecto.entities.Contrato[ idcontrato=" + idcontrato + " ]";
    }
    
}
