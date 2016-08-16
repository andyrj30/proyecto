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
    @NamedQuery(name = "Materia.findByCargahoraria", query = "SELECT m FROM Materia m WHERE m.cargahoraria = :cargahoraria"),
    @NamedQuery(name = "Materia.findByCargatutorial", query = "SELECT m FROM Materia m WHERE m.cargatutorial = :cargatutorial"),
    @NamedQuery(name = "Materia.findByColor", query = "SELECT m FROM Materia m WHERE m.color = :color")})
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
    @Column(name = "cargahoraria")
    private int cargahoraria;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cargatutorial")
    private int cargatutorial;
    @Size(max = 10)
    @Column(name = "color")
    private String color;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codmateria")
    private List<Distributivodocente> distributivodocenteList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codmateria")
    private List<Distributivoclase> distributivoclaseList;
    @JoinColumn(name = "idsemestre", referencedColumnName = "idsemestre")
    @ManyToOne
    private Semestre idsemestre;
    @JoinColumn(name = "idsubarea", referencedColumnName = "idsubarea")
    @ManyToOne(optional = false)
    private Subarea idsubarea;

    public Materia() {
    }

    public Materia(String codmateria) {
        this.codmateria = codmateria;
    }

    public Materia(String codmateria, String materia, int cargahoraria, int cargatutorial) {
        this.codmateria = codmateria;
        this.materia = materia;
        this.cargahoraria = cargahoraria;
        this.cargatutorial = cargatutorial;
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

    public int getCargahoraria() {
        return cargahoraria;
    }

    public void setCargahoraria(int cargahoraria) {
        this.cargahoraria = cargahoraria;
    }

    public int getCargatutorial() {
        return cargatutorial;
    }

    public void setCargatutorial(int cargatutorial) {
        this.cargatutorial = cargatutorial;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public List<Distributivodocente> getDistributivodocenteList() {
        return distributivodocenteList;
    }

    public void setDistributivodocenteList(List<Distributivodocente> distributivodocenteList) {
        this.distributivodocenteList = distributivodocenteList;
    }

    public List<Distributivoclase> getDistributivoclaseList() {
        return distributivoclaseList;
    }

    public void setDistributivoclaseList(List<Distributivoclase> distributivoclaseList) {
        this.distributivoclaseList = distributivoclaseList;
    }

    public Semestre getIdsemestre() {
        return idsemestre;
    }

    public void setIdsemestre(Semestre idsemestre) {
        this.idsemestre = idsemestre;
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
