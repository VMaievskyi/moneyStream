package com.piramida.facade;

import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import com.piramida.controller.exception.BusinessException;
import com.piramida.entity.Queue;
import com.piramida.entity.QueueType;
import com.piramida.entity.QueueTypeHolder;
import com.piramida.facade.queue.impl.QueueFacadeImpl;
import com.piramida.service.queue.QueueService;

public class QueueFacadeTest {

    private static final int SUMM_TO_PAY = 500;
    private static final int REQUIRED_PAYMENT_COUNT = 5;
    private static final int NUMBER_OF_VISIBLE_POSITIONS = 10;
    private static final String QUEUE_TYPE = "C500";
    private static final int ID = 0;
    @Mock
    private Queue queue;
    @Spy
    private final QueueFacadeImpl testInstance = new QueueFacadeImpl() {

	@Override
	protected Queue createBlankQueue() {
	    return queue;
	}
    };
    @Mock
    private QueueService queueServiceMock;
    @Mock
    private QueueTypeHolder queueTypeHolderMock;
    private QueueType queueType;

    @Before
    public void setUp() {
	MockitoAnnotations.initMocks(this);
	testInstance.setQueueService(queueServiceMock);
	testInstance.setQueueTypeHolder(queueTypeHolderMock);

	createQueueType();

	Mockito.when(queueTypeHolderMock.getQueuTypeByName(QUEUE_TYPE))
		.thenReturn(queueType);
    }

    @Ignore
    @Test
    public void shouldPutInQueue() throws BusinessException {
	testInstance.putInQueue(QUEUE_TYPE);
	verify(queue).setQueueType(QUEUE_TYPE);
	verify(queue).setRequiredPaymentCount(REQUIRED_PAYMENT_COUNT);
	verify(queueServiceMock).putInQueue(queue);
    }

    private void createQueueType() {
	queueType = new QueueType();
	queueType.setNumberOfVisiblePositions(NUMBER_OF_VISIBLE_POSITIONS);
	queueType.setRequiredPaymentCount(REQUIRED_PAYMENT_COUNT);
	queueType.setSumToPay(SUMM_TO_PAY);
	queueType.setVIPOnly(Boolean.FALSE);
    }

}
