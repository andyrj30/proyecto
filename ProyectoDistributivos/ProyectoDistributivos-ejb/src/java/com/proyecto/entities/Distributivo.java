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
import javax.validation.constraints.NotNull;

/**
 *
 * @author Andy
 */
@Entity
@Table(name = "distributivo")
@NamedQueries({
    @NamedQuery(name = "Distributivo.findAll", query = "SELECT d FROM Distributivo d"),
    @NamedQuery(name = "Distributivo.findByIddistributivo", query = "SELECT d FROM Distributivo d WHERE d.iddistributivo = :iddistributivo"),
    @NamedQuery(name = "Distributivo.findByTotalsemanal", query = "SELECT d FROM Distributivo d WHERE d.totalsemanal = :totalsemanal")})
public class Distributivo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "iddistributivo")
    private Integer iddistributivo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "totalsemanal")
    private int totalsemanal;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "distributivo")
    private List<Detalledistributivootras> detalledistributivootrasList;
    @JoinColumn(name = "docente", referencedColumnName = "iddocente")
    @ManyToOne(optional = false)
    private Docente docente;
    @JoinColumn(name = "periodo", referencedColumnName = "idperiodo")
    @ManyToOne(optional = false)
    private Periodo periodo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "distributivo")
    private List<Detalledistributivoclase> detalledistributivoclaseList;

    public Distributivo() {
    }

    public Distributivo(Integer iddistributivo) {
        this.iddistributivo = iddistributivo;
    }

    public Distributivo(Integer iddistributivo, int totalsemanal) {
        this.iddistributivo = iddistributivo;
        this.totalsemanal = totalsemanal;
    }

    public Integer getIddistributivo() {
        return iddistributivo;
    }

    public void setIddistributivo(Integer iddistributivo) {
        this.iddistributivo = iddistributivo;
    }

    public int getTotalsemanal() {
        return totalsemanal;
    }

    public void setTotalsemanal(int totalsemanal) {
        this.totalsemanal = totalsemanal;
    }

    public List<Detalledistributivootras> getDetalledistributivootrasList() {
        return detalledistributivootrasList;
    }

    public void setDetalledistributivootrasList(List<Detalledistributivootras> detalledistributivootrasList) {
        this.detalledistributivootrasList = detalledistributivootrasList;
    }

    public Docente getDocente() {
        return docente;
    }

    public void setDocente(Docente docente) {
        this.docente = docente;
    }

    public Periodo getPeriodo() {
        return periodo;
    }

    public void setPeriodo(Periodo periodo) {
        this.periodo = periodo;
    }

    public List<Detalledistributivoclase> getDetalledistributivoclaseList() {
        return detalledistributivoclaseList;
    }

    public void setDetalledistributivoclaseList(List<Detalledistributivoclase> detalledistributivoclaseList) {
        this.detalledistributivoclaseList = detalledistributivoclaseList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iddistributivo != null ? iddistributivo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Distributivo)) {
            return false;
        }
        Distributivo other = (Distributivo) object;
        if ((this.iddistributivo == null && other.iddistributivo != null) || (this.iddistributivo != null && !this.iddistributivo.equals(other.iddistributivo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.proyecto.entities.Distributivo[ iddistributivo=" + iddistributivo + " ]";
    }
    
}
