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
@Table(name = "paralelo")
@NamedQueries({
    @NamedQuery(name = "Paralelo.findAll", query = "SELECT p FROM Paralelo p"),
    @NamedQuery(name = "Paralelo.findByCodparalelo", query = "SELECT p FROM Paralelo p WHERE p.codparalelo = :codparalelo"),
    @NamedQuery(name = "Paralelo.findByParalelo", query = "SELECT p FROM Paralelo p WHERE p.paralelo = :paralelo")})
public class Paralelo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "codparalelo")
    private String codparalelo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "paralelo")
    private String paralelo;
    @JoinColumn(name = "periodo", referencedColumnName = "idperiodo")
    @ManyToOne(optional = false)
    private Periodo periodo;
    @JoinColumn(name = "seccion", referencedColumnName = "idseccion")
    @ManyToOne
    private Seccion seccion;
    @JoinColumn(name = "semestre", referencedColumnName = "idsemestre")
    @ManyToOne(optional = false)
    private Semestre semestre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "paralelo")
    private List<Distaula> distaulaList;

    public Paralelo() {
    }

    public Paralelo(String codparalelo) {
        this.codparalelo = codparalelo;
    }

    public Paralelo(String codparalelo, String paralelo) {
        this.codparalelo = codparalelo;
        this.paralelo = paralelo;
    }

    public String getCodparalelo() {
        return codparalelo;
    }

    public void setCodparalelo(String codparalelo) {
        this.codparalelo = codparalelo;
    }

    public String getParalelo() {
        return paralelo;
    }

    public void setParalelo(String paralelo) {
        this.paralelo = paralelo;
    }

    public Periodo getPeriodo() {
        return periodo;
    }

    public void setPeriodo(Periodo periodo) {
        this.periodo = periodo;
    }

    public Seccion getSeccion() {
        return seccion;
    }

    public void setSeccion(Seccion seccion) {
        this.seccion = seccion;
    }

    public Semestre getSemestre() {
        return semestre;
    }

    public void setSemestre(Semestre semestre) {
        this.semestre = semestre;
    }

    public List<Distaula> getDistaulaList() {
        return distaulaList;
    }

    public void setDistaulaList(List<Distaula> distaulaList) {
        this.distaulaList = distaulaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codparalelo != null ? codparalelo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Paralelo)) {
            return false;
        }
        Paralelo other = (Paralelo) object;
        if ((this.codparalelo == null && other.codparalelo != null) || (this.codparalelo != null && !this.codparalelo.equals(other.codparalelo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.proyecto.entities.Paralelo[ codparalelo=" + codparalelo + " ]";
    }
    
}
