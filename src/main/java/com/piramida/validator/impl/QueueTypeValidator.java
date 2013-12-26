package com.piramida.validator.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.piramida.entity.QueueTypeHolder;

@Component
public class QueueTypeValidator implements Validator {

    @Autowired
    private QueueTypeHolder queueTypeHolder;

    @Override
    public void validate(final Object target, final Errors errors) {
	final String toCheck = (String) target;
	if (queueTypeHolder.getQueuTypeByName(toCheck) == null) {
	    errors.reject("such queue doesn't exists");
	}
    }

    @Override
    public boolean supports(final Class<?> clazz) {
	return (String.class.equals(clazz));
    }

}
