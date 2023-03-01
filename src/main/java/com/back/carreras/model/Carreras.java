
package com.back.carreras.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;


import javax.persistence.Temporal;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity

public class Carreras implements Serializable {
     @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idCarreras;
    private String temporadaCarrera;
    private int cantPilCarrera;
    private int multiplCarrera;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaCarrera;
    

  @ManyToOne
  
  private Autodromo autodromo;
  
  @ManyToOne
 
  private Categorias categorias;

    public Carreras() {
    }

    public Carreras(Long idCarreras, String temporadaCarrera, int cantPilCarrera, int multiplCarrera, Date fechaCarrera, Autodromo autodromo, Categorias categorias) {
        this.idCarreras = idCarreras;
        this.temporadaCarrera = temporadaCarrera;
        this.cantPilCarrera = cantPilCarrera;
        this.multiplCarrera = multiplCarrera;
        this.fechaCarrera = fechaCarrera;
        this.autodromo = autodromo;
        this.categorias = categorias;
    }

    

    

   

}
