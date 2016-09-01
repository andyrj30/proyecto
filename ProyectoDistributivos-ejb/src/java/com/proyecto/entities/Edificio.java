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
@Table(name = "edificio")
@NamedQueries({
    @NamedQuery(name = "Edificio.findAll", query = "SELECT e FROM Edificio e"),
    @NamedQuery(name = "Edificio.findByIdedificio", query = "SELECT e FROM Edificio e WHERE e.idedificio = :idedificio"),
    @NamedQuery(name = "Edificio.findByEdificio", query = "SELECT e FROM Edificio e WHERE e.edificio = :edificio")})
public class Edificio implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idedificio")
    private Integer idedificio;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "edificio")
    private String edificio;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "edificio")
    private List<Aula> aulaList;

    public Edificio() {
    }

    public Edificio(Integer idedificio) {
        this.idedificio = idedificio;
    }

    public Edificio(Integer idedificio, String edificio) {
        this.idedificio = idedificio;
        this.edificio = edificio;
    }

    public Integer getIdedificio() {
        return idedificio;
    }

    public void setIdedificio(Integer idedificio) {
        this.idedificio = idedificio;
    }

    public String getEdificio() {
        return edificio;
    }

    public void setEdificio(String edificio) {
        this.edificio = edificio;
    }

    public List<Aula> getAulaList() {
        return aulaList;
    }

    public void setAulaList(List<Aula> aulaList) {
        this.aulaList = aulaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idedificio != null ? idedificio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Edificio)) {
            return false;
        }
        Edificio other = (Edificio) object;
        if ((this.idedificio == null && other.idedificio != null) || (this.idedificio != null && !this.idedificio.equals(other.idedificio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.proyecto.entities.Edificio[ idedificio=" + idedificio + " ]";
    }
    
}
