package com.haciendamanso.erp.model;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "usuarios")
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // 👈 IMPORTANTE: usar Integer, no int

    private String nombre;
    private int edad;
    private String ciudad;
}