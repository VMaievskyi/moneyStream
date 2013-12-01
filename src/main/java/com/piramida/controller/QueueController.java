package com.piramida.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.piramida.controller.exception.BusinessException;
import com.piramida.entity.dto.MessageDto;
import com.piramida.facade.queue.QueueFacade;

@Controller
@RequestMapping("/controller/queue")
public class QueueController {

    @Autowired
    @Qualifier("queueFacade")
    private QueueFacade queueFacade;

    @Secured(value = "ROLE_USER")
    @RequestMapping(method = RequestMethod.PUT, value = "/{queueType}")
    @ResponseBody
    public MessageDto putInQueue(@PathVariable final String queueType)
	    throws BusinessException {
	queueFacade.putInQueue(queueType);
	return new MessageDto("queue.put.in");
    }

}
