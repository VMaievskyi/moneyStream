package com.piramida.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.piramida.entity.Account;
import com.piramida.entity.dto.MessageDto;
import com.piramida.facade.queue.QueueFacade;

@Controller
@RequestMapping("/queue")
public class QueueController {
    @Autowired
    @Qualifier("queueFacade")
    private QueueFacade queueFacade;

    @Secured(value = "ROLE_USER")
    @RequestMapping(method = RequestMethod.PUT)
    @ResponseBody
    public MessageDto putInQueue(
	    @RequestParam(required = true) final String queueType) {
	queueFacade.putInQueue(queueType, getPrincipal());
	return new MessageDto("queue.put.in");
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping(method = RequestMethod.DELETE)
    @ResponseBody
    public MessageDto removeFromQueue(
	    @RequestParam(required = true) final Integer id) {
	queueFacade.removeFromQueue(id);
	return new MessageDto("queue.delete");
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public MessageDto swapQueues(
	    @RequestParam(required = true) final Integer id1,
	    @RequestParam(required = true) final Integer id2) {

	queueFacade.swapQueues(id1, id2);
	return new MessageDto("queue.swap");
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping(method = RequestMethod.PUT)
    @ResponseBody
    public MessageDto putInQueueCheat(
	    @RequestParam(required = true) final String queueType,
	    @RequestParam(required = true) final Integer accountId) {
	queueFacade.putInQueue(queueType, accountId);
	return null;
    }

    private Account getPrincipal() {
	return (Account) SecurityContextHolder.getContext().getAuthentication()
		.getPrincipal();
    }
}
