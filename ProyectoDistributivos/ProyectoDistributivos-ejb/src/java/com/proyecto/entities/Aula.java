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
    @NamedQuery(name = "Aula.findByPiso", query = "SELECT a FROM Aula a WHERE a.piso = :piso"),
    @NamedQuery(name = "Aula.findByAula", query = "SELECT a FROM Aula a WHERE a.aula = :aula"),
    @NamedQuery(name = "Aula.findByNumeroestudiantesmax", query = "SELECT a FROM Aula a WHERE a.numeroestudiantesmax = :numeroestudiantesmax")})
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
    @Size(min = 1, max = 200)
    @Column(name = "piso")
    private String piso;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "aula")
    private String aula;
    @Column(name = "numeroestudiantesmax")
    private Integer numeroestudiantesmax;
    @JoinColumn(name = "edificio", referencedColumnName = "idedificio")
    @ManyToOne
    private Edificio edificio;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "aula")
    private List<Distaula> distaulaList;

    public Aula() {
    }

    public Aula(String codaula) {
        this.codaula = codaula;
    }

    public Aula(String codaula, String piso, String aula) {
        this.codaula = codaula;
        this.piso = piso;
        this.aula = aula;
    }

    public String getCodaula() {
        return codaula;
    }

    public void setCodaula(String codaula) {
        this.codaula = codaula;
    }

    public String getPiso() {
        return piso;
    }

    public void setPiso(String piso) {
        this.piso = piso;
    }

    public String getAula() {
        return aula;
    }

    public void setAula(String aula) {
        this.aula = aula;
    }

    public Integer getNumeroestudiantesmax() {
        return numeroestudiantesmax;
    }

    public void setNumeroestudiantesmax(Integer numeroestudiantesmax) {
        this.numeroestudiantesmax = numeroestudiantesmax;
    }

    public Edificio getEdificio() {
        return edificio;
    }

    public void setEdificio(Edificio edificio) {
        this.edificio = edificio;
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
