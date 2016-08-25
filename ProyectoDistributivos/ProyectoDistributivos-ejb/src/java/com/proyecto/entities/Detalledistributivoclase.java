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

/**
 *
 * @author Andy
 */
@Entity
@Table(name = "detalledistributivoclase")
@NamedQueries({
    @NamedQuery(name = "Detalledistributivoclase.findAll", query = "SELECT d FROM Detalledistributivoclase d"),
    @NamedQuery(name = "Detalledistributivoclase.findByIddetalledistributivoclase", query = "SELECT d FROM Detalledistributivoclase d WHERE d.iddetalledistributivoclase = :iddetalledistributivoclase")})
public class Detalledistributivoclase implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "iddetalledistributivoclase")
    private Integer iddetalledistributivoclase;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "iddetalledistributivoclase")
    private List<Horarioclase> horarioclaseList;
    @JoinColumn(name = "distributivoaula", referencedColumnName = "iddistributivoaula")
    @ManyToOne(optional = false)
    private Distaula distributivoaula;
    @JoinColumn(name = "distributivo", referencedColumnName = "iddistributivo")
    @ManyToOne(optional = false)
    private Distributivo distributivo;
    @JoinColumn(name = "materia", referencedColumnName = "codmateria")
    @ManyToOne(optional = false)
    private Materia materia;

    public Detalledistributivoclase() {
    }

    public Detalledistributivoclase(Integer iddetalledistributivoclase) {
        this.iddetalledistributivoclase = iddetalledistributivoclase;
    }

    public Integer getIddetalledistributivoclase() {
        return iddetalledistributivoclase;
    }

    public void setIddetalledistributivoclase(Integer iddetalledistributivoclase) {
        this.iddetalledistributivoclase = iddetalledistributivoclase;
    }

    public List<Horarioclase> getHorarioclaseList() {
        return horarioclaseList;
    }

    public void setHorarioclaseList(List<Horarioclase> horarioclaseList) {
        this.horarioclaseList = horarioclaseList;
    }

    public Distaula getDistributivoaula() {
        return distributivoaula;
    }

    public void setDistributivoaula(Distaula distributivoaula) {
        this.distributivoaula = distributivoaula;
    }

    public Distributivo getDistributivo() {
        return distributivo;
    }

    public void setDistributivo(Distributivo distributivo) {
        this.distributivo = distributivo;
    }

    public Materia getMateria() {
        return materia;
    }

    public void setMateria(Materia materia) {
        this.materia = materia;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iddetalledistributivoclase != null ? iddetalledistributivoclase.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Detalledistributivoclase)) {
            return false;
        }
        Detalledistributivoclase other = (Detalledistributivoclase) object;
        if ((this.iddetalledistributivoclase == null && other.iddetalledistributivoclase != null) || (this.iddetalledistributivoclase != null && !this.iddetalledistributivoclase.equals(other.iddetalledistributivoclase))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.proyecto.entities.Detalledistributivoclase[ iddetalledistributivoclase=" + iddetalledistributivoclase + " ]";
    }
    
}
