package com.piramida.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.piramida.controller.exception.BusinessException;
import com.piramida.entity.Queue;
import com.piramida.entity.QueueType;
import com.piramida.facade.queue.QueueFacade;

@Controller
@RequestMapping("/queue")
public class QueueController {

    @Autowired
    @Qualifier("queueFacade")
    private QueueFacade queueFacade;

    @Secured(value = "ROLE_USER")
    @RequestMapping(method = RequestMethod.POST, value = "/{queueType}")
    public String putInQueue(final Model model,
	    @PathVariable final String queueType) throws BusinessException {
	final Queue queue = queueFacade.putInQueue(queueType);
	if (queue != null) {
	    model.addAttribute("wallets", queue.getAccount().getWallets());
	}
	return "walletsPage";
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
    public String getQueueInfo(
	    final Model model,
	    @RequestParam(value = "currentQueue", required = true) final String currentQueue) {

	model.addAttribute("queueInfo",
		queueFacade.getInfoForQueueType(currentQueue));
	return "queueInfo";
    }

}
