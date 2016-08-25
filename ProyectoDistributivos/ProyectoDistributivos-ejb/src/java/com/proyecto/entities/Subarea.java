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
@Table(name = "subarea")
@NamedQueries({
    @NamedQuery(name = "Subarea.findAll", query = "SELECT s FROM Subarea s"),
    @NamedQuery(name = "Subarea.findByIdsubarea", query = "SELECT s FROM Subarea s WHERE s.idsubarea = :idsubarea"),
    @NamedQuery(name = "Subarea.findBySubarea", query = "SELECT s FROM Subarea s WHERE s.subarea = :subarea")})
public class Subarea implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idsubarea")
    private Integer idsubarea;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "subarea")
    private String subarea;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "subarea")
    private List<Conocimiento> conocimientoList;
    @JoinColumn(name = "area", referencedColumnName = "idarea")
    @ManyToOne(optional = false)
    private Area area;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "subarea")
    private List<Materia> materiaList;

    public Subarea() {
    }

    public Subarea(Integer idsubarea) {
        this.idsubarea = idsubarea;
    }

    public Subarea(Integer idsubarea, String subarea) {
        this.idsubarea = idsubarea;
        this.subarea = subarea;
    }

    public Integer getIdsubarea() {
        return idsubarea;
    }

    public void setIdsubarea(Integer idsubarea) {
        this.idsubarea = idsubarea;
    }

    public String getSubarea() {
        return subarea;
    }

    public void setSubarea(String subarea) {
        this.subarea = subarea;
    }

    public List<Conocimiento> getConocimientoList() {
        return conocimientoList;
    }

    public void setConocimientoList(List<Conocimiento> conocimientoList) {
        this.conocimientoList = conocimientoList;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public List<Materia> getMateriaList() {
        return materiaList;
    }

    public void setMateriaList(List<Materia> materiaList) {
        this.materiaList = materiaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idsubarea != null ? idsubarea.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Subarea)) {
            return false;
        }
        Subarea other = (Subarea) object;
        if ((this.idsubarea == null && other.idsubarea != null) || (this.idsubarea != null && !this.idsubarea.equals(other.idsubarea))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.proyecto.entities.Subarea[ idsubarea=" + idsubarea + " ]";
    }
    
}
