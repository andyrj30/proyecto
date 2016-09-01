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
@Table(name = "materia")
@NamedQueries({
    @NamedQuery(name = "Materia.findAll", query = "SELECT m FROM Materia m"),
    @NamedQuery(name = "Materia.findByCodmateria", query = "SELECT m FROM Materia m WHERE m.codmateria = :codmateria"),
    @NamedQuery(name = "Materia.findByMateria", query = "SELECT m FROM Materia m WHERE m.materia = :materia"),
    @NamedQuery(name = "Materia.findByTipo", query = "SELECT m FROM Materia m WHERE m.tipo = :tipo"),
    @NamedQuery(name = "Materia.findByHorasclase", query = "SELECT m FROM Materia m WHERE m.horasclase = :horasclase"),
    @NamedQuery(name = "Materia.findByHoraspracticas", query = "SELECT m FROM Materia m WHERE m.horaspracticas = :horaspracticas"),
    @NamedQuery(name = "Materia.findByAprendizajecolaborativo", query = "SELECT m FROM Materia m WHERE m.aprendizajecolaborativo = :aprendizajecolaborativo"),
    @NamedQuery(name = "Materia.findByCreditos", query = "SELECT m FROM Materia m WHERE m.creditos = :creditos")})
public class Materia implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "codmateria")
    private String codmateria;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "materia")
    private String materia;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "tipo")
    private String tipo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "horasclase")
    private int horasclase;
    @Basic(optional = false)
    @NotNull
    @Column(name = "horaspracticas")
    private int horaspracticas;
    @Basic(optional = false)
    @NotNull
    @Column(name = "aprendizajecolaborativo")
    private int aprendizajecolaborativo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "creditos")
    private int creditos;
    @OneToMany(mappedBy = "materia")
    private List<Detalledistributivo> detalledistributivoList;
    @JoinColumn(name = "semestre", referencedColumnName = "idsemestre")
    @ManyToOne(optional = false)
    private Semestre semestre;
    @JoinColumn(name = "subarea", referencedColumnName = "idsubarea")
    @ManyToOne(optional = false)
    private Subarea subarea;

    public Materia() {
    }

    public Materia(String codmateria) {
        this.codmateria = codmateria;
    }

    public Materia(String codmateria, String materia, String tipo, int horasclase, int horaspracticas, int aprendizajecolaborativo, int creditos) {
        this.codmateria = codmateria;
        this.materia = materia;
        this.tipo = tipo;
        this.horasclase = horasclase;
        this.horaspracticas = horaspracticas;
        this.aprendizajecolaborativo = aprendizajecolaborativo;
        this.creditos = creditos;
    }

    public String getCodmateria() {
        return codmateria;
    }

    public void setCodmateria(String codmateria) {
        this.codmateria = codmateria;
    }

    public String getMateria() {
        return materia;
    }

    public void setMateria(String materia) {
        this.materia = materia;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getHorasclase() {
        return horasclase;
    }

    public void setHorasclase(int horasclase) {
        this.horasclase = horasclase;
    }

    public int getHoraspracticas() {
        return horaspracticas;
    }

    public void setHoraspracticas(int horaspracticas) {
        this.horaspracticas = horaspracticas;
    }

    public int getAprendizajecolaborativo() {
        return aprendizajecolaborativo;
    }

    public void setAprendizajecolaborativo(int aprendizajecolaborativo) {
        this.aprendizajecolaborativo = aprendizajecolaborativo;
    }

    public int getCreditos() {
        return creditos;
    }

    public void setCreditos(int creditos) {
        this.creditos = creditos;
    }

    public List<Detalledistributivo> getDetalledistributivoList() {
        return detalledistributivoList;
    }

    public void setDetalledistributivoList(List<Detalledistributivo> detalledistributivoList) {
        this.detalledistributivoList = detalledistributivoList;
    }

    public Semestre getSemestre() {
        return semestre;
    }

    public void setSemestre(Semestre semestre) {
        this.semestre = semestre;
    }

    public Subarea getSubarea() {
        return subarea;
    }

    public void setSubarea(Subarea subarea) {
        this.subarea = subarea;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codmateria != null ? codmateria.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Materia)) {
            return false;
        }
        Materia other = (Materia) object;
        if ((this.codmateria == null && other.codmateria != null) || (this.codmateria != null && !this.codmateria.equals(other.codmateria))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.proyecto.entities.Materia[ codmateria=" + codmateria + " ]";
    }
    
}
