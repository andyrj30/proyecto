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
@Table(name = "distributivodocente")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Distributivodocente.findAll", query = "SELECT d FROM Distributivodocente d"),
    @NamedQuery(name = "Distributivodocente.findByIddistributivodoc", query = "SELECT d FROM Distributivodocente d WHERE d.iddistributivodoc = :iddistributivodoc")})
public class Distributivodocente implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "iddistributivodoc")
    private Integer iddistributivodoc;
    @JoinColumn(name = "cedula", referencedColumnName = "cedula")
    @ManyToOne(optional = false)
    private Docente cedula;
    @JoinColumn(name = "codmateria", referencedColumnName = "codmateria")
    @ManyToOne(optional = false)
    private Materia codmateria;
    @JoinColumn(name = "idperiodo", referencedColumnName = "idperiodo")
    @ManyToOne(optional = false)
    private Periodo idperiodo;

    public Distributivodocente() {
    }

    public Distributivodocente(Integer iddistributivodoc) {
        this.iddistributivodoc = iddistributivodoc;
    }

    public Integer getIddistributivodoc() {
        return iddistributivodoc;
    }

    public void setIddistributivodoc(Integer iddistributivodoc) {
        this.iddistributivodoc = iddistributivodoc;
    }

    public Docente getCedula() {
        return cedula;
    }

    public void setCedula(Docente cedula) {
        this.cedula = cedula;
    }

    public Materia getCodmateria() {
        return codmateria;
    }

    public void setCodmateria(Materia codmateria) {
        this.codmateria = codmateria;
    }

    public Periodo getIdperiodo() {
        return idperiodo;
    }

    public void setIdperiodo(Periodo idperiodo) {
        this.idperiodo = idperiodo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iddistributivodoc != null ? iddistributivodoc.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Distributivodocente)) {
            return false;
        }
        Distributivodocente other = (Distributivodocente) object;
        if ((this.iddistributivodoc == null && other.iddistributivodoc != null) || (this.iddistributivodoc != null && !this.iddistributivodoc.equals(other.iddistributivodoc))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.proyecto.entities.Distributivodocente[ iddistributivodoc=" + iddistributivodoc + " ]";
    }
    
}
