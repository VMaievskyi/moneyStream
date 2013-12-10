package com.piramida.controller.navigation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.piramida.facade.queue.QueueFacade;

@Controller
@RequestMapping("/")
public class MainNavigationController {

    @Autowired
    @Qualifier("queueFacade")
    private QueueFacade queueFacade;

    @RequestMapping
    public String index() {
	return "index";
    }

    @RequestMapping("/queues")
    public String showQueues(final Model model) {
	model.addAttribute("queues", queueFacade.getAllQueueTypes());
	return "allQueues";
    }

}
