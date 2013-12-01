package com.piramida.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.piramida.entity.dto.PendingQueueDtoList;
import com.piramida.facade.pendingqueue.PendingQueueFacade;

@Controller
@RequestMapping(value = "/controller/pendingQueue")
public class PendinqQueueController {

    @Autowired
    private PendingQueueFacade pendingQueueFacade;

    @Secured(value = { "ROLE_USER", "ROLE_ADMIN" })
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public PendingQueueDtoList getAllPendingQueues() {
	return pendingQueueFacade.getAllRecordsForCurrentAccount();
    }
}
