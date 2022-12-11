package com.andresFVA.turnosOdont.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;
@JsonIgnoreProperties({"hibernateLazyInitializer"})//Ignora items asi marcados, ya que getReferenceById obtiene una
// referencia y no el objeto por lo que no puede acceder a sus propiedades, y agrega este campo para marcarlo.
// Si se requiere de una las propiedades mejor cambiar por findById.  ver:
//       https://www.javacodemonk.com/difference-between-getone-and-findbyid-in-spring-data-jpa-3a96c3ff
@Entity
public class Patient {
    @Id
    private Long DNI;

    private String nombre;
    private String apellido;
    private String domicilio;
    private LocalDateTime fecha_alta= LocalDateTime.now();

    @OneToMany(mappedBy = "patient")
    private Set<Turno> turnos;
    /* Por el momento no se requiere Get ni Set de turnos y asi también se evita una referencia cíclica pues
      turnos tiene pacientes, equivalente a usar las anotaciones: @JsonManagedReference y @JsonBackReference
      en la relación Dentist-Turno*/

    public Long getDNI() {
        return DNI;
    }

    public void setDNI(Long DNI) {
        this.DNI = DNI;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public LocalDateTime getFecha_alta() {
        return fecha_alta;
    }

    public void setFecha_alta(LocalDateTime fecha_alta) {
        this.fecha_alta = fecha_alta;
    }
}
