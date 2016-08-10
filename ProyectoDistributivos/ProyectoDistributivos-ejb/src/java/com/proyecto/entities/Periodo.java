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
@Table(name = "periodo")
@NamedQueries({
    @NamedQuery(name = "Periodo.findAll", query = "SELECT p FROM Periodo p"),
    @NamedQuery(name = "Periodo.findByIdperiodo", query = "SELECT p FROM Periodo p WHERE p.idperiodo = :idperiodo"),
    @NamedQuery(name = "Periodo.findByPeridodo", query = "SELECT p FROM Periodo p WHERE p.peridodo = :peridodo")})
public class Periodo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "idperiodo")
    private String idperiodo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "peridodo")
    private String peridodo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idperiodo")
    private List<Distributivoaula> distributivoaulaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idperiodo")
    private List<Paralelo> paraleloList;

    public Periodo() {
    }

    public Periodo(String idperiodo) {
        this.idperiodo = idperiodo;
    }

    public Periodo(String idperiodo, String peridodo) {
        this.idperiodo = idperiodo;
        this.peridodo = peridodo;
    }

    public String getIdperiodo() {
        return idperiodo;
    }

    public void setIdperiodo(String idperiodo) {
        this.idperiodo = idperiodo;
    }

    public String getPeridodo() {
        return peridodo;
    }

    public void setPeridodo(String peridodo) {
        this.peridodo = peridodo;
    }

    public List<Distributivoaula> getDistributivoaulaList() {
        return distributivoaulaList;
    }

    public void setDistributivoaulaList(List<Distributivoaula> distributivoaulaList) {
        this.distributivoaulaList = distributivoaulaList;
    }

    public List<Paralelo> getParaleloList() {
        return paraleloList;
    }

    public void setParaleloList(List<Paralelo> paraleloList) {
        this.paraleloList = paraleloList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idperiodo != null ? idperiodo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Periodo)) {
            return false;
        }
        Periodo other = (Periodo) object;
        if ((this.idperiodo == null && other.idperiodo != null) || (this.idperiodo != null && !this.idperiodo.equals(other.idperiodo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.proyecto.entities.Periodo[ idperiodo=" + idperiodo + " ]";
    }
    
}
