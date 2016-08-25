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
@Table(name = "horarioclase")
@NamedQueries({
    @NamedQuery(name = "Horarioclase.findAll", query = "SELECT h FROM Horarioclase h"),
    @NamedQuery(name = "Horarioclase.findByIdhorario", query = "SELECT h FROM Horarioclase h WHERE h.idhorario = :idhorario"),
    @NamedQuery(name = "Horarioclase.findByHorainicio", query = "SELECT h FROM Horarioclase h WHERE h.horainicio = :horainicio"),
    @NamedQuery(name = "Horarioclase.findByHorafin", query = "SELECT h FROM Horarioclase h WHERE h.horafin = :horafin")})
public class Horarioclase implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idhorario")
    private Integer idhorario;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "horainicio")
    private String horainicio;
    @Size(max = 10)
    @Column(name = "horafin")
    private String horafin;
    @JoinColumn(name = "iddetalledistributivoclase", referencedColumnName = "iddetalledistributivoclase")
    @ManyToOne(optional = false)
    private Detalledistributivoclase iddetalledistributivoclase;

    public Horarioclase() {
    }

    public Horarioclase(Integer idhorario) {
        this.idhorario = idhorario;
    }

    public Horarioclase(Integer idhorario, String horainicio) {
        this.idhorario = idhorario;
        this.horainicio = horainicio;
    }

    public Integer getIdhorario() {
        return idhorario;
    }

    public void setIdhorario(Integer idhorario) {
        this.idhorario = idhorario;
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

    public Detalledistributivoclase getIddetalledistributivoclase() {
        return iddetalledistributivoclase;
    }

    public void setIddetalledistributivoclase(Detalledistributivoclase iddetalledistributivoclase) {
        this.iddetalledistributivoclase = iddetalledistributivoclase;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idhorario != null ? idhorario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Horarioclase)) {
            return false;
        }
        Horarioclase other = (Horarioclase) object;
        if ((this.idhorario == null && other.idhorario != null) || (this.idhorario != null && !this.idhorario.equals(other.idhorario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.proyecto.entities.Horarioclase[ idhorario=" + idhorario + " ]";
    }
    
}
