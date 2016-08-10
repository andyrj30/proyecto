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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Andy
 */
@Entity
@Table(name = "distributivoclase")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Distributivoclase.findAll", query = "SELECT d FROM Distributivoclase d"),
    @NamedQuery(name = "Distributivoclase.findByIddistributivoclase", query = "SELECT d FROM Distributivoclase d WHERE d.iddistributivoclase = :iddistributivoclase")})
public class Distributivoclase implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "iddistributivoclase")
    private Integer iddistributivoclase;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "iddistributivoclase")
    private List<Horario> horarioList;
    @JoinColumn(name = "codmateria", referencedColumnName = "codmateria")
    @ManyToOne(optional = false)
    private Materia codmateria;
    @JoinColumn(name = "idparalelo", referencedColumnName = "idparalelo")
    @ManyToOne(optional = false)
    private Paralelo idparalelo;

    public Distributivoclase() {
    }

    public Distributivoclase(Integer iddistributivoclase) {
        this.iddistributivoclase = iddistributivoclase;
    }

    public Integer getIddistributivoclase() {
        return iddistributivoclase;
    }

    public void setIddistributivoclase(Integer iddistributivoclase) {
        this.iddistributivoclase = iddistributivoclase;
    }

    @XmlTransient
    public List<Horario> getHorarioList() {
        return horarioList;
    }

    public void setHorarioList(List<Horario> horarioList) {
        this.horarioList = horarioList;
    }

    public Materia getCodmateria() {
        return codmateria;
    }

    public void setCodmateria(Materia codmateria) {
        this.codmateria = codmateria;
    }

    public Paralelo getIdparalelo() {
        return idparalelo;
    }

    public void setIdparalelo(Paralelo idparalelo) {
        this.idparalelo = idparalelo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iddistributivoclase != null ? iddistributivoclase.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Distributivoclase)) {
            return false;
        }
        Distributivoclase other = (Distributivoclase) object;
        if ((this.iddistributivoclase == null && other.iddistributivoclase != null) || (this.iddistributivoclase != null && !this.iddistributivoclase.equals(other.iddistributivoclase))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.proyecto.entities.Distributivoclase[ iddistributivoclase=" + iddistributivoclase + " ]";
    }
    
}
