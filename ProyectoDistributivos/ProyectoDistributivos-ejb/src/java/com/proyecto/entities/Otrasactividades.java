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
@Table(name = "otrasactividades")
@NamedQueries({
    @NamedQuery(name = "Otrasactividades.findAll", query = "SELECT o FROM Otrasactividades o"),
    @NamedQuery(name = "Otrasactividades.findByIdotrasactividades", query = "SELECT o FROM Otrasactividades o WHERE o.idotrasactividades = :idotrasactividades"),
    @NamedQuery(name = "Otrasactividades.findByTipo", query = "SELECT o FROM Otrasactividades o WHERE o.tipo = :tipo"),
    @NamedQuery(name = "Otrasactividades.findByDetalle", query = "SELECT o FROM Otrasactividades o WHERE o.detalle = :detalle"),
    @NamedQuery(name = "Otrasactividades.findByHoras", query = "SELECT o FROM Otrasactividades o WHERE o.horas = :horas")})
public class Otrasactividades implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idotrasactividades")
    private Integer idotrasactividades;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "tipo")
    private String tipo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 300)
    @Column(name = "detalle")
    private String detalle;
    @Basic(optional = false)
    @NotNull
    @Column(name = "horas")
    private int horas;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "otrasactividades")
    private List<Detalledistributivootras> detalledistributivootrasList;

    public Otrasactividades() {
    }

    public Otrasactividades(Integer idotrasactividades) {
        this.idotrasactividades = idotrasactividades;
    }

    public Otrasactividades(Integer idotrasactividades, String tipo, String detalle, int horas) {
        this.idotrasactividades = idotrasactividades;
        this.tipo = tipo;
        this.detalle = detalle;
        this.horas = horas;
    }

    public Integer getIdotrasactividades() {
        return idotrasactividades;
    }

    public void setIdotrasactividades(Integer idotrasactividades) {
        this.idotrasactividades = idotrasactividades;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public int getHoras() {
        return horas;
    }

    public void setHoras(int horas) {
        this.horas = horas;
    }

    public List<Detalledistributivootras> getDetalledistributivootrasList() {
        return detalledistributivootrasList;
    }

    public void setDetalledistributivootrasList(List<Detalledistributivootras> detalledistributivootrasList) {
        this.detalledistributivootrasList = detalledistributivootrasList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idotrasactividades != null ? idotrasactividades.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Otrasactividades)) {
            return false;
        }
        Otrasactividades other = (Otrasactividades) object;
        if ((this.idotrasactividades == null && other.idotrasactividades != null) || (this.idotrasactividades != null && !this.idotrasactividades.equals(other.idotrasactividades))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.proyecto.entities.Otrasactividades[ idotrasactividades=" + idotrasactividades + " ]";
    }
    
}
