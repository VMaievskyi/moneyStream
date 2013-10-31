package com.piramida.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.piramida.entity.dto.MessageDto;
import com.piramida.facade.queue.QueueFacade;

@Controller
@RequestMapping("/admin")
@Secured("ROLE_ADMIN")
public class AdminController {
    @Autowired
    @Qualifier("queueFacade")
    private QueueFacade queueFacade;

    public void aproveQueueRequest(
	    @RequestParam(required = true) final Integer queueId) {

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
	return new MessageDto("queue.cheat");
    }

}
