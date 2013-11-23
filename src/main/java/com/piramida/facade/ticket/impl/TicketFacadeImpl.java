package com.piramida.facade.ticket.impl;

import org.apache.commons.lang.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.piramida.entity.ReceiptAction;
import com.piramida.facade.ticket.TicketFacade;
import com.piramida.service.pendingqueue.PendingQueueService;
import com.piramida.service.queue.QueueService;

@Service("ticketFacade")
public class TicketFacadeImpl implements TicketFacade {

    @Autowired
    private PendingQueueService pendingQueueService;
    @Autowired
    private QueueService queueService;

    @Override
    public void manageReceiptStatus(final String receiptId,
	    final String receiptAction) {
	Validate.notNull(receiptId, "receiptId should be set");
	Validate.notNull(receiptAction, "receipt action shou;d be set");

	final ReceiptAction action = ReceiptAction.valueOf(receiptAction);
	switch (action) {
	case APPROVE:
	    pendingQueueService.approvePendingQueue(receiptId);
	    break;
	case REJECT:
	    pendingQueueService.rejectPendingQueue(receiptId);
	    break;
	default:
	    break;
	}
    }

}
