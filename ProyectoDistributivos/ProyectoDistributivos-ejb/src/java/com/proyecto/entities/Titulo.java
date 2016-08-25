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
import javax.validation.constraints.Size;

/**
 *
 * @author Andy
 */
@Entity
@Table(name = "titulo")
@NamedQueries({
    @NamedQuery(name = "Titulo.findAll", query = "SELECT t FROM Titulo t"),
    @NamedQuery(name = "Titulo.findByIdtitulo", query = "SELECT t FROM Titulo t WHERE t.idtitulo = :idtitulo"),
    @NamedQuery(name = "Titulo.findByDetalle", query = "SELECT t FROM Titulo t WHERE t.detalle = :detalle"),
    @NamedQuery(name = "Titulo.findByAbreviatura", query = "SELECT t FROM Titulo t WHERE t.abreviatura = :abreviatura")})
public class Titulo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idtitulo")
    private Integer idtitulo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "detalle")
    private String detalle;
    @Size(max = 50)
    @Column(name = "abreviatura")
    private String abreviatura;
    @JoinColumn(name = "nivel", referencedColumnName = "idnivel")
    @ManyToOne(optional = false)
    private Nivel nivel;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "titulo")
    private List<Estudio> estudioList;

    public Titulo() {
    }

    public Titulo(Integer idtitulo) {
        this.idtitulo = idtitulo;
    }

    public Titulo(Integer idtitulo, String detalle) {
        this.idtitulo = idtitulo;
        this.detalle = detalle;
    }

    public Integer getIdtitulo() {
        return idtitulo;
    }

    public void setIdtitulo(Integer idtitulo) {
        this.idtitulo = idtitulo;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public String getAbreviatura() {
        return abreviatura;
    }

    public void setAbreviatura(String abreviatura) {
        this.abreviatura = abreviatura;
    }

    public Nivel getNivel() {
        return nivel;
    }

    public void setNivel(Nivel nivel) {
        this.nivel = nivel;
    }

    public List<Estudio> getEstudioList() {
        return estudioList;
    }

    public void setEstudioList(List<Estudio> estudioList) {
        this.estudioList = estudioList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idtitulo != null ? idtitulo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Titulo)) {
            return false;
        }
        Titulo other = (Titulo) object;
        if ((this.idtitulo == null && other.idtitulo != null) || (this.idtitulo != null && !this.idtitulo.equals(other.idtitulo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.proyecto.entities.Titulo[ idtitulo=" + idtitulo + " ]";
    }
    
}
