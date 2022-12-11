package com.andresFVA.turnosOdont.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import java.util.HashSet;
import java.util.Set;

@JsonIgnoreProperties({"hibernateLazyInitializer"})//Ignora items asi marcados, ya que getReferenceById obtiene una
// referencia y no el objeto por lo que no puede acceder a sus propiedades, y agrega este campo para marcarlo.
// Si se requiere de una las propiedades mejor cambiar por findById.  ver:
//       https://www.javacodemonk.com/difference-between-getone-and-findbyid-in-spring-data-jpa-3a96c3ff
@Entity
public class Dentist {
    @Id
    Long matricula;
    String apellido;
    String nombre;
    @JsonBackReference/*En conjunto con el JsonManagedReference en Turno para cuando retorne el objeto como JSON no
    intente llenar los turnos, pero en turnos si los dentistas*/
    @OneToMany(mappedBy = "dentist")
    Set<Turno> turnos=new HashSet<>();

    public Set<Turno> getTurnos() {
        return turnos;
    }

    public void setTurnos(Set<Turno> turnos) {
        this.turnos = turnos;
    }

    public Long getMatricula() {
        return matricula;
    }

    public void setMatricula(Long matricula) {
        this.matricula = matricula;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
