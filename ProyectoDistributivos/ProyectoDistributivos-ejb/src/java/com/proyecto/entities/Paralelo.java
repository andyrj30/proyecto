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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Andy
 */
@Entity
@Table(name = "paralelo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Paralelo.findAll", query = "SELECT p FROM Paralelo p"),
    @NamedQuery(name = "Paralelo.findByIdparalelo", query = "SELECT p FROM Paralelo p WHERE p.idparalelo = :idparalelo"),
    @NamedQuery(name = "Paralelo.findByParalelo", query = "SELECT p FROM Paralelo p WHERE p.paralelo = :paralelo")})
public class Paralelo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "idparalelo")
    private String idparalelo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "paralelo")
    private String paralelo;
    @JoinColumn(name = "idperiodo", referencedColumnName = "idperiodo")
    @ManyToOne(optional = false)
    private Periodo idperiodo;
    @JoinColumn(name = "idsemestre", referencedColumnName = "idsemestre")
    @ManyToOne(optional = false)
    private Semestre idsemestre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idparalelo")
    private List<Distributivoclase> distributivoclaseList;

    public Paralelo() {
    }

    public Paralelo(String idparalelo) {
        this.idparalelo = idparalelo;
    }

    public Paralelo(String idparalelo, String paralelo) {
        this.idparalelo = idparalelo;
        this.paralelo = paralelo;
    }

    public String getIdparalelo() {
        return idparalelo;
    }

    public void setIdparalelo(String idparalelo) {
        this.idparalelo = idparalelo;
    }

    public String getParalelo() {
        return paralelo;
    }

    public void setParalelo(String paralelo) {
        this.paralelo = paralelo;
    }

    public Periodo getIdperiodo() {
        return idperiodo;
    }

    public void setIdperiodo(Periodo idperiodo) {
        this.idperiodo = idperiodo;
    }

    public Semestre getIdsemestre() {
        return idsemestre;
    }

    public void setIdsemestre(Semestre idsemestre) {
        this.idsemestre = idsemestre;
    }

    @XmlTransient
    public List<Distributivoclase> getDistributivoclaseList() {
        return distributivoclaseList;
    }

    public void setDistributivoclaseList(List<Distributivoclase> distributivoclaseList) {
        this.distributivoclaseList = distributivoclaseList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idparalelo != null ? idparalelo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Paralelo)) {
            return false;
        }
        Paralelo other = (Paralelo) object;
        if ((this.idparalelo == null && other.idparalelo != null) || (this.idparalelo != null && !this.idparalelo.equals(other.idparalelo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.proyecto.entities.Paralelo[ idparalelo=" + idparalelo + " ]";
    }
    
}
