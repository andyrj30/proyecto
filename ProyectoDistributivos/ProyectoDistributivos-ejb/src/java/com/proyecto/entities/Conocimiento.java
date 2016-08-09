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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Andy
 */
@Entity
@Table(name = "conocimiento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Conocimiento.findAll", query = "SELECT c FROM Conocimiento c"),
    @NamedQuery(name = "Conocimiento.findByIdconocimiento", query = "SELECT c FROM Conocimiento c WHERE c.idconocimiento = :idconocimiento")})
public class Conocimiento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idconocimiento")
    private Integer idconocimiento;
    @JoinColumn(name = "cedula", referencedColumnName = "cedula")
    @ManyToOne(optional = false)
    private Docente cedula;
    @JoinColumn(name = "idsubarea", referencedColumnName = "idsubarea")
    @ManyToOne(optional = false)
    private Subarea idsubarea;

    public Conocimiento() {
    }

    public Conocimiento(Integer idconocimiento) {
        this.idconocimiento = idconocimiento;
    }

    public Integer getIdconocimiento() {
        return idconocimiento;
    }

    public void setIdconocimiento(Integer idconocimiento) {
        this.idconocimiento = idconocimiento;
    }

    public Docente getCedula() {
        return cedula;
    }

    public void setCedula(Docente cedula) {
        this.cedula = cedula;
    }

    public Subarea getIdsubarea() {
        return idsubarea;
    }

    public void setIdsubarea(Subarea idsubarea) {
        this.idsubarea = idsubarea;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idconocimiento != null ? idconocimiento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Conocimiento)) {
            return false;
        }
        Conocimiento other = (Conocimiento) object;
        if ((this.idconocimiento == null && other.idconocimiento != null) || (this.idconocimiento != null && !this.idconocimiento.equals(other.idconocimiento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.proyecto.entities.Conocimiento[ idconocimiento=" + idconocimiento + " ]";
    }
    
}
