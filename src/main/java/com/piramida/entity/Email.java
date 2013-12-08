package com.piramida.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Email {

    private Integer id;
    private String text;
    private String recepient;
    private Integer tryCounter;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Id
    public Integer getId() {
	return id;
    }

    public void setId(final Integer id) {
	this.id = id;
    }

    @Column(name = "text", columnDefinition = "TEXT")
    public String getText() {
	return text;
    }

    public void setText(final String text) {
	this.text = text;
    }

    @Column(name = "recepient")
    public String getRecepient() {
	return recepient;
    }

    public void setRecepient(final String recepient) {
	this.recepient = recepient;
    }

    @Column(name = "tryCounter")
    public Integer getTryCounter() {
	return tryCounter;
    }

    public void setTryCounter(final Integer tryCounter) {
	this.tryCounter = tryCounter;
    }
}
