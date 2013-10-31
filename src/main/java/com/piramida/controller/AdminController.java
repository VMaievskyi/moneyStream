package com.piramida.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

}
