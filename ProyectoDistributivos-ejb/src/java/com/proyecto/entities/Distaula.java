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

/**
 *
 * @author Andy
 */
@Entity
@Table(name = "distaula")
@NamedQueries({
    @NamedQuery(name = "Distaula.findAll", query = "SELECT d FROM Distaula d"),
    @NamedQuery(name = "Distaula.findByIddistributivoaula", query = "SELECT d FROM Distaula d WHERE d.iddistributivoaula = :iddistributivoaula")})
public class Distaula implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "iddistributivoaula")
    private Integer iddistributivoaula;
    @JoinColumn(name = "aula", referencedColumnName = "codaula")
    @ManyToOne(optional = false)
    private Aula aula;
    @JoinColumn(name = "paralelo", referencedColumnName = "codparalelo")
    @ManyToOne(optional = false)
    private Paralelo paralelo;
    @JoinColumn(name = "periodo", referencedColumnName = "idperiodo")
    @ManyToOne(optional = false)
    private Periodo periodo;

    public Distaula() {
    }

    public Distaula(Integer iddistributivoaula) {
        this.iddistributivoaula = iddistributivoaula;
    }

    public Integer getIddistributivoaula() {
        return iddistributivoaula;
    }

    public void setIddistributivoaula(Integer iddistributivoaula) {
        this.iddistributivoaula = iddistributivoaula;
    }

    public Aula getAula() {
        return aula;
    }

    public void setAula(Aula aula) {
        this.aula = aula;
    }

    public Paralelo getParalelo() {
        return paralelo;
    }

    public void setParalelo(Paralelo paralelo) {
        this.paralelo = paralelo;
    }

    public Periodo getPeriodo() {
        return periodo;
    }

    public void setPeriodo(Periodo periodo) {
        this.periodo = periodo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iddistributivoaula != null ? iddistributivoaula.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Distaula)) {
            return false;
        }
        Distaula other = (Distaula) object;
        if ((this.iddistributivoaula == null && other.iddistributivoaula != null) || (this.iddistributivoaula != null && !this.iddistributivoaula.equals(other.iddistributivoaula))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.proyecto.entities.Distaula[ iddistributivoaula=" + iddistributivoaula + " ]";
    }
    
}
