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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Andy
 */
@Entity
@Table(name = "horariootras")
@NamedQueries({
    @NamedQuery(name = "Horariootras.findAll", query = "SELECT h FROM Horariootras h"),
    @NamedQuery(name = "Horariootras.findByIdhorariootras", query = "SELECT h FROM Horariootras h WHERE h.idhorariootras = :idhorariootras"),
    @NamedQuery(name = "Horariootras.findByHorainicio", query = "SELECT h FROM Horariootras h WHERE h.horainicio = :horainicio"),
    @NamedQuery(name = "Horariootras.findByHorafin", query = "SELECT h FROM Horariootras h WHERE h.horafin = :horafin")})
public class Horariootras implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idhorariootras")
    private Integer idhorariootras;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "horainicio")
    private String horainicio;
    @Size(max = 10)
    @Column(name = "horafin")
    private String horafin;
    @JoinColumn(name = "detalledistributivootras", referencedColumnName = "iddetalledistributivootras")
    @ManyToOne(optional = false)
    private Detalledistributivootras detalledistributivootras;

    public Horariootras() {
    }

    public Horariootras(Integer idhorariootras) {
        this.idhorariootras = idhorariootras;
    }

    public Horariootras(Integer idhorariootras, String horainicio) {
        this.idhorariootras = idhorariootras;
        this.horainicio = horainicio;
    }

    public Integer getIdhorariootras() {
        return idhorariootras;
    }

    public void setIdhorariootras(Integer idhorariootras) {
        this.idhorariootras = idhorariootras;
    }

    public String getHorainicio() {
        return horainicio;
    }

    public void setHorainicio(String horainicio) {
        this.horainicio = horainicio;
    }

    public String getHorafin() {
        return horafin;
    }

    public void setHorafin(String horafin) {
        this.horafin = horafin;
    }

    public Detalledistributivootras getDetalledistributivootras() {
        return detalledistributivootras;
    }

    public void setDetalledistributivootras(Detalledistributivootras detalledistributivootras) {
        this.detalledistributivootras = detalledistributivootras;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idhorariootras != null ? idhorariootras.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Horariootras)) {
            return false;
        }
        Horariootras other = (Horariootras) object;
        if ((this.idhorariootras == null && other.idhorariootras != null) || (this.idhorariootras != null && !this.idhorariootras.equals(other.idhorariootras))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.proyecto.entities.Horariootras[ idhorariootras=" + idhorariootras + " ]";
    }
    
}
