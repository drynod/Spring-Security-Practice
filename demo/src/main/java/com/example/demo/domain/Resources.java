package com.example.demo.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter @Setter
public class Resources {

    @Id
    @GeneratedValue
    @Column(name = "resource_id")
    private Long id;

    private String httpMethod;
    private int orderNum;

    private String resourceType;


}
