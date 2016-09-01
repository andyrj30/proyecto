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
@Table(name = "dedicacion")
@NamedQueries({
    @NamedQuery(name = "Dedicacion.findAll", query = "SELECT d FROM Dedicacion d"),
    @NamedQuery(name = "Dedicacion.findByIddedicacion", query = "SELECT d FROM Dedicacion d WHERE d.iddedicacion = :iddedicacion"),
    @NamedQuery(name = "Dedicacion.findByDetalle", query = "SELECT d FROM Dedicacion d WHERE d.detalle = :detalle"),
    @NamedQuery(name = "Dedicacion.findByAbreviatura", query = "SELECT d FROM Dedicacion d WHERE d.abreviatura = :abreviatura"),
    @NamedQuery(name = "Dedicacion.findByHoras", query = "SELECT d FROM Dedicacion d WHERE d.horas = :horas")})
public class Dedicacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "iddedicacion")
    private Integer iddedicacion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "detalle")
    private String detalle;
    @Size(max = 10)
    @Column(name = "abreviatura")
    private String abreviatura;
    @Basic(optional = false)
    @NotNull
    @Column(name = "horas")
    private int horas;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "dedicacion")
    private List<Contrato> contratoList;

    public Dedicacion() {
    }

    public Dedicacion(Integer iddedicacion) {
        this.iddedicacion = iddedicacion;
    }

    public Dedicacion(Integer iddedicacion, String detalle, int horas) {
        this.iddedicacion = iddedicacion;
        this.detalle = detalle;
        this.horas = horas;
    }

    public Integer getIddedicacion() {
        return iddedicacion;
    }

    public void setIddedicacion(Integer iddedicacion) {
        this.iddedicacion = iddedicacion;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public String getAbreviatura() {
        return abreviatura;
    }

    public void setAbreviatura(String abreviatura) {
        this.abreviatura = abreviatura;
    }

    public int getHoras() {
        return horas;
    }

    public void setHoras(int horas) {
        this.horas = horas;
    }

    public List<Contrato> getContratoList() {
        return contratoList;
    }

    public void setContratoList(List<Contrato> contratoList) {
        this.contratoList = contratoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iddedicacion != null ? iddedicacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Dedicacion)) {
            return false;
        }
        Dedicacion other = (Dedicacion) object;
        if ((this.iddedicacion == null && other.iddedicacion != null) || (this.iddedicacion != null && !this.iddedicacion.equals(other.iddedicacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.proyecto.entities.Dedicacion[ iddedicacion=" + iddedicacion + " ]";
    }
    
}
