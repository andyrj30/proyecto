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
@Table(name = "aula")
@NamedQueries({
    @NamedQuery(name = "Aula.findAll", query = "SELECT a FROM Aula a"),
    @NamedQuery(name = "Aula.findByCodaula", query = "SELECT a FROM Aula a WHERE a.codaula = :codaula"),
    @NamedQuery(name = "Aula.findByAula", query = "SELECT a FROM Aula a WHERE a.aula = :aula")})
public class Aula implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "codaula")
    private String codaula;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "aula")
    private String aula;
    @JoinColumn(name = "idedificio", referencedColumnName = "idedificio")
    @ManyToOne(optional = false)
    private Edificio idedificio;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codaula")
    private List<Distributivoaula> distributivoaulaList;

    public Aula() {
    }

    public Aula(String codaula) {
        this.codaula = codaula;
    }

    public Aula(String codaula, String aula) {
        this.codaula = codaula;
        this.aula = aula;
    }

    public String getCodaula() {
        return codaula;
    }

    public void setCodaula(String codaula) {
        this.codaula = codaula;
    }

    public String getAula() {
        return aula;
    }

    public void setAula(String aula) {
        this.aula = aula;
    }

    public Edificio getIdedificio() {
        return idedificio;
    }

    public void setIdedificio(Edificio idedificio) {
        this.idedificio = idedificio;
    }

    public List<Distributivoaula> getDistributivoaulaList() {
        return distributivoaulaList;
    }

    public void setDistributivoaulaList(List<Distributivoaula> distributivoaulaList) {
        this.distributivoaulaList = distributivoaulaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codaula != null ? codaula.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Aula)) {
            return false;
        }
        Aula other = (Aula) object;
        if ((this.codaula == null && other.codaula != null) || (this.codaula != null && !this.codaula.equals(other.codaula))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.proyecto.entities.Aula[ codaula=" + codaula + " ]";
    }
    
}
