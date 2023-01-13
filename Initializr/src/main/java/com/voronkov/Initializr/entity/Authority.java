package com.voronkov.Initializr.entity;

import lombok.Data;
import org.springframework.validation.BindingResult;

import javax.persistence.*;

@Entity
@Data
@Table(name = "authority")
public class Authority{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;
}