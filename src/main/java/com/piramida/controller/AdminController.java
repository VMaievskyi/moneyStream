package com.piramida.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.piramida.entity.Account;
import com.piramida.entity.ActivationStatus;
import com.piramida.entity.dto.AccountDto;
import com.piramida.entity.dto.MessageDto;
import com.piramida.facade.account.AccountFacade;
import com.piramida.facade.queue.QueueFacade;
import com.piramida.facade.ticket.TicketFacade;

@Controller
@RequestMapping("/controller/admin")
public class AdminController {

    @Autowired
    private QueueFacade queueFacade;
    @Autowired
    private AccountFacade accountFacade;
    @Autowired
    private TicketFacade ticketFacade;

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

    @Secured("ROLE_ADMIN")
    @RequestMapping(method = RequestMethod.PUT, value = "/account")
    @ResponseBody
    public MessageDto createAdminAccount(@RequestBody final AccountDto account) {
	final Account accountModel = accountFacade.createAccount(account);
	accountModel.setStatus(ActivationStatus.ACTIVE);
	accountModel.setRole("ROLE_ADMIN");
	accountFacade.updateAccount(accountModel);
	return new MessageDto("adminAccount.created");
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping(method = RequestMethod.POST, value = "/receiptStatus")
    @ResponseBody
    public MessageDto manageReceiptStatus(@RequestParam final String receiptId,
	    @RequestParam final String receiptAction) {
	ticketFacade.manageReceiptStatus(receiptId, receiptAction);
	return new MessageDto("queue.ticket.status." + receiptAction);
    }
}
