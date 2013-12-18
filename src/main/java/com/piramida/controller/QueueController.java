package com.piramida.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.piramida.controller.exception.BusinessException;
import com.piramida.entity.QueueType;
import com.piramida.entity.dto.MessageDto;
import com.piramida.entity.dto.QueueInfoDto;
import com.piramida.facade.queue.QueueFacade;

@Controller
@RequestMapping("/queue")
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

    @Secured(value = "ROLE_USER")
    @RequestMapping(method = RequestMethod.GET, value = "/types")
    @ResponseBody
    public List<QueueType> getAllQueueTypes() throws BusinessException {
	return queueFacade.getAllQueueTypes();
    }

    @Secured(value = "ROLE_USER")
    @RequestMapping(method = RequestMethod.GET)
    public String showQueues(final Model model) {
	model.addAttribute("queues", queueFacade.getAllQueueTypes());
	return "allQueues";
    }

    @Secured(value = "ROLE_USER")
    @RequestMapping(value = "/infoForQueue", method = RequestMethod.GET)
    public QueueInfoDto getQueueInfo(
	    @ModelAttribute final QueueType currentQueue) {
	return queueFacade.getInfoForQueueType(currentQueue);

    }

}
