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
@Table(name = "detalledistributivootras")
@NamedQueries({
    @NamedQuery(name = "Detalledistributivootras.findAll", query = "SELECT d FROM Detalledistributivootras d"),
    @NamedQuery(name = "Detalledistributivootras.findByIddetalledistributivootras", query = "SELECT d FROM Detalledistributivootras d WHERE d.iddetalledistributivootras = :iddetalledistributivootras")})
public class Detalledistributivootras implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "iddetalledistributivootras")
    private Integer iddetalledistributivootras;
    @JoinColumn(name = "carrera", referencedColumnName = "codcarrera")
    @ManyToOne(optional = false)
    private Carrera carrera;
    @JoinColumn(name = "distributivo", referencedColumnName = "iddistributivo")
    @ManyToOne(optional = false)
    private Distributivo distributivo;
    @JoinColumn(name = "otrasactividades", referencedColumnName = "idotrasactividades")
    @ManyToOne(optional = false)
    private Otrasactividades otrasactividades;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "detalledistributivootras")
    private List<Horariootras> horariootrasList;

    public Detalledistributivootras() {
    }

    public Detalledistributivootras(Integer iddetalledistributivootras) {
        this.iddetalledistributivootras = iddetalledistributivootras;
    }

    public Integer getIddetalledistributivootras() {
        return iddetalledistributivootras;
    }

    public void setIddetalledistributivootras(Integer iddetalledistributivootras) {
        this.iddetalledistributivootras = iddetalledistributivootras;
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

    public Otrasactividades getOtrasactividades() {
        return otrasactividades;
    }

    public void setOtrasactividades(Otrasactividades otrasactividades) {
        this.otrasactividades = otrasactividades;
    }

    public List<Horariootras> getHorariootrasList() {
        return horariootrasList;
    }

    public void setHorariootrasList(List<Horariootras> horariootrasList) {
        this.horariootrasList = horariootrasList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iddetalledistributivootras != null ? iddetalledistributivootras.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Detalledistributivootras)) {
            return false;
        }
        Detalledistributivootras other = (Detalledistributivootras) object;
        if ((this.iddetalledistributivootras == null && other.iddetalledistributivootras != null) || (this.iddetalledistributivootras != null && !this.iddetalledistributivootras.equals(other.iddetalledistributivootras))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.proyecto.entities.Detalledistributivootras[ iddetalledistributivootras=" + iddetalledistributivootras + " ]";
    }
    
}
