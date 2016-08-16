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
@Table(name = "distributivoaula")
@NamedQueries({
    @NamedQuery(name = "Distributivoaula.findAll", query = "SELECT d FROM Distributivoaula d"),
    @NamedQuery(name = "Distributivoaula.findByIddistributivoaula", query = "SELECT d FROM Distributivoaula d WHERE d.iddistributivoaula = :iddistributivoaula")})
public class Distributivoaula implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "iddistributivoaula")
    private Integer iddistributivoaula;
    @JoinColumn(name = "codaula", referencedColumnName = "codaula")
    @ManyToOne(optional = false)
    private Aula codaula;
    @JoinColumn(name = "idperiodo", referencedColumnName = "idperiodo")
    @ManyToOne(optional = false)
    private Periodo idperiodo;

    public Distributivoaula() {
    }

    public Distributivoaula(Integer iddistributivoaula) {
        this.iddistributivoaula = iddistributivoaula;
    }

    public Integer getIddistributivoaula() {
        return iddistributivoaula;
    }

    public void setIddistributivoaula(Integer iddistributivoaula) {
        this.iddistributivoaula = iddistributivoaula;
    }

    public Aula getCodaula() {
        return codaula;
    }

    public void setCodaula(Aula codaula) {
        this.codaula = codaula;
    }

    public Periodo getIdperiodo() {
        return idperiodo;
    }

    public void setIdperiodo(Periodo idperiodo) {
        this.idperiodo = idperiodo;
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
        if (!(object instanceof Distributivoaula)) {
            return false;
        }
        Distributivoaula other = (Distributivoaula) object;
        if ((this.iddistributivoaula == null && other.iddistributivoaula != null) || (this.iddistributivoaula != null && !this.iddistributivoaula.equals(other.iddistributivoaula))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.proyecto.entities.Distributivoaula[ iddistributivoaula=" + iddistributivoaula + " ]";
    }
    
}
