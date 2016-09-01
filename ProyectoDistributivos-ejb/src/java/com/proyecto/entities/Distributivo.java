/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyecto.entities;

import java.io.Serializable;
import java.util.List;
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
    @NamedQuery(name = "Distributivo.findByTotalhorasclase", query = "SELECT d FROM Distributivo d WHERE d.totalhorasclase = :totalhorasclase"),
    @NamedQuery(name = "Distributivo.findByTotalhoraspracticas", query = "SELECT d FROM Distributivo d WHERE d.totalhoraspracticas = :totalhoraspracticas"),
    @NamedQuery(name = "Distributivo.findByTotalaprendizajecolaborativo", query = "SELECT d FROM Distributivo d WHERE d.totalaprendizajecolaborativo = :totalaprendizajecolaborativo"),
    @NamedQuery(name = "Distributivo.findByTotalotras", query = "SELECT d FROM Distributivo d WHERE d.totalotras = :totalotras"),
    @NamedQuery(name = "Distributivo.findByTotalinvestigacion", query = "SELECT d FROM Distributivo d WHERE d.totalinvestigacion = :totalinvestigacion"),
    @NamedQuery(name = "Distributivo.findByTotalvinculacion", query = "SELECT d FROM Distributivo d WHERE d.totalvinculacion = :totalvinculacion"),
    @NamedQuery(name = "Distributivo.findByTotalgestion", query = "SELECT d FROM Distributivo d WHERE d.totalgestion = :totalgestion"),
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
    @Column(name = "totalhorasclase")
    private int totalhorasclase;
    @Basic(optional = false)
    @NotNull
    @Column(name = "totalhoraspracticas")
    private int totalhoraspracticas;
    @Basic(optional = false)
    @NotNull
    @Column(name = "totalaprendizajecolaborativo")
    private int totalaprendizajecolaborativo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "totalotras")
    private int totalotras;
    @Basic(optional = false)
    @NotNull
    @Column(name = "totalinvestigacion")
    private int totalinvestigacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "totalvinculacion")
    private int totalvinculacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "totalgestion")
    private int totalgestion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "totalsemanal")
    private int totalsemanal;
    @JoinColumn(name = "docente", referencedColumnName = "iddocente")
    @ManyToOne(optional = false)
    private Docente docente;
    @JoinColumn(name = "periodo", referencedColumnName = "idperiodo")
    @ManyToOne(optional = false)
    private Periodo periodo;
    @OneToMany(mappedBy = "distributivo")
    private List<Detalledistributivo> detalledistributivoList;

    public Distributivo() {
    }

    public Distributivo(Integer iddistributivo) {
        this.iddistributivo = iddistributivo;
    }

    public Distributivo(Integer iddistributivo, int totalhorasclase, int totalhoraspracticas, int totalaprendizajecolaborativo, int totalotras, int totalinvestigacion, int totalvinculacion, int totalgestion, int totalsemanal) {
        this.iddistributivo = iddistributivo;
        this.totalhorasclase = totalhorasclase;
        this.totalhoraspracticas = totalhoraspracticas;
        this.totalaprendizajecolaborativo = totalaprendizajecolaborativo;
        this.totalotras = totalotras;
        this.totalinvestigacion = totalinvestigacion;
        this.totalvinculacion = totalvinculacion;
        this.totalgestion = totalgestion;
        this.totalsemanal = totalsemanal;
    }

    public Integer getIddistributivo() {
        return iddistributivo;
    }

    public void setIddistributivo(Integer iddistributivo) {
        this.iddistributivo = iddistributivo;
    }

    public int getTotalhorasclase() {
        return totalhorasclase;
    }

    public void setTotalhorasclase(int totalhorasclase) {
        this.totalhorasclase = totalhorasclase;
    }

    public int getTotalhoraspracticas() {
        return totalhoraspracticas;
    }

    public void setTotalhoraspracticas(int totalhoraspracticas) {
        this.totalhoraspracticas = totalhoraspracticas;
    }

    public int getTotalaprendizajecolaborativo() {
        return totalaprendizajecolaborativo;
    }

    public void setTotalaprendizajecolaborativo(int totalaprendizajecolaborativo) {
        this.totalaprendizajecolaborativo = totalaprendizajecolaborativo;
    }

    public int getTotalotras() {
        return totalotras;
    }

    public void setTotalotras(int totalotras) {
        this.totalotras = totalotras;
    }

    public int getTotalinvestigacion() {
        return totalinvestigacion;
    }

    public void setTotalinvestigacion(int totalinvestigacion) {
        this.totalinvestigacion = totalinvestigacion;
    }

    public int getTotalvinculacion() {
        return totalvinculacion;
    }

    public void setTotalvinculacion(int totalvinculacion) {
        this.totalvinculacion = totalvinculacion;
    }

    public int getTotalgestion() {
        return totalgestion;
    }

    public void setTotalgestion(int totalgestion) {
        this.totalgestion = totalgestion;
    }

    public int getTotalsemanal() {
        return totalsemanal;
    }

    public void setTotalsemanal(int totalsemanal) {
        this.totalsemanal = totalsemanal;
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

    public List<Detalledistributivo> getDetalledistributivoList() {
        return detalledistributivoList;
    }

    public void setDetalledistributivoList(List<Detalledistributivo> detalledistributivoList) {
        this.detalledistributivoList = detalledistributivoList;
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
