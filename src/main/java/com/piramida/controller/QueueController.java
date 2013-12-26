package com.piramida.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.piramida.controller.exception.BusinessException;
import com.piramida.entity.Queue;
import com.piramida.facade.queue.QueueFacade;

@Controller
@RequestMapping("/queue")
public class QueueController {

    @Autowired
    @Qualifier("queueFacade")
    private QueueFacade queueFacade;
    @Autowired
    @Qualifier("queueTypeValidator")
    private Validator queueTypeValidator;

    @Secured(value = "ROLE_USER")
    @RequestMapping(method = RequestMethod.POST, value = "/{queueType}")
    public String putInQueue(@PathVariable final String queueType,
	    final Model model) throws BusinessException // TODO: Check exception
							// throwing strategy.
    {
	final BindingResult result = new DataBinder(null).getBindingResult();
	queueTypeValidator.validate(queueType, result);
	if (result.hasErrors()) {
	    model.addAttribute("exception", "wrong queue");
	} else {
	    final Queue queue = queueFacade.putInQueue(queueType);
	    if (queue != null) {
		model.addAttribute("wallets", queue.getAccount().getWallets());
	    }
	}
	return result.hasErrors() ? "queue" : "walletsPage";
    }

    /*
     * @Secured(value = "ROLE_USER")
     * 
     * @RequestMapping(method = RequestMethod.GET, value = "/types")
     * 
     * @ResponseBody public List<QueueType> getAllQueueTypes() throws
     * BusinessException { return queueFacade.getAllQueueTypes(); }
     */

    @Secured(value = "ROLE_USER")
    @RequestMapping(method = RequestMethod.GET)
    public String showQueues(final Model model) {
	model.addAttribute("queues", queueFacade.getAllQueueTypes());
	return "allQueues";
    }

    @Secured(value = "ROLE_USER")
    @RequestMapping(value = "/infoForQueue", method = RequestMethod.GET)
    public String getQueueInfo(

	    @RequestParam(value = "currentQueue", required = true) final String currentQueue,
	    final Model model) {
	final BindingResult result = new DataBinder(null).getBindingResult();
	queueTypeValidator.validate(currentQueue, result);
	if (result.hasErrors()) {
	    model.addAttribute("exception", "wrong queue");
	} else {
	    model.addAttribute("queueInfo",
		    queueFacade.getInfoForQueueType(currentQueue));
	}
	return result.hasErrors() ? "allQueues" : "queueInfo";
    }
}
