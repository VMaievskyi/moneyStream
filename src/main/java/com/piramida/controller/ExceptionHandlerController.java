package com.piramida.controller;

import org.springframework.web.bind.annotation.ExceptionHandler;

import com.piramida.entity.dto.MessageDto;

public class ExceptionHandlerController {

    @ExceptionHandler(value = { IllegalArgumentException.class })
    public MessageDto handleUnexpectedError(final Exception exc) {
	final MessageDto message = new MessageDto(exc.getMessage());
	return message;
    }
}
