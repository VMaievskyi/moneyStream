package com.piramida.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class Test implements Serializable
{
	@Id
	private long id;
	@Column(name = "name")
	private String name;
}
