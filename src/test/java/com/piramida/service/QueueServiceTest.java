package com.piramida.service;

import static org.mockito.Mockito.verify;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.piramida.dao.queue.QueueDao;
import com.piramida.entity.Account;
import com.piramida.entity.ActivationStatus;
import com.piramida.entity.Queue;
import com.piramida.entity.QueueType;
import com.piramida.service.queue.impl.DefaultQueueService;

public class QueueServiceTest {

    private static final int PAYMENT_COUNT = 0;
    private static final int REQUIRED_PAYMENT_COUNT = QueueType.C500
	    .getRequiredPaymentCount();
    @Mock
    private QueueDao queueDaoMock;
    private Queue queue;
    private DefaultQueueService testInstance;

    @Before
    public void setUp() {
	MockitoAnnotations.initMocks(this);
	testInstance = new DefaultQueueService();
	testInstance.setQueueDao(queueDaoMock);
	initTestQueue();
    }

    @Test
    public void shouldAddRecordToDB() {
	initTestQueue();
	testInstance.putInQueue(queue);
	verify(queueDaoMock).save(queue);
    }

    @Test
    public void shouldRemoveRecord() {
	initTestQueue();
	testInstance.remove(queue);
	verify(queueDaoMock).delete(queue);
    }

    @Test
    public void shouldSwitchRowsPosition() {
	initTestQueue();
	final Queue secondRow = new Queue();
	testInstance.switchPositions(queue, secondRow);
	verify(queueDaoMock).switchPositions(queue, secondRow);
    }

    @Test
    public void shouldReturnFirstRow() {
	initTestQueue();
	Mockito.when(queueDaoMock.getFirst(queue.getQueueType())).thenReturn(
		queue);
	final Queue firstRow = testInstance.getFirst(queue.getQueueType());
	verify(queueDaoMock).getFirst(queue.getQueueType());
	Assert.assertNotNull("Nothing were returned", firstRow);
    }

    @Test
    public void shouldIncreasePaymentCount() {
	initTestQueue();
	Mockito.when(queueDaoMock.getFirst(queue.getQueueType())).thenReturn(
		queue);
	testInstance.increaseFirstRowPaymentCount(queue.getQueueType());
	verify(queueDaoMock).getFirst(queue.getQueueType());
	Assert.assertEquals("payment count wasn't increased",
		PAYMENT_COUNT + 1, queue.getPaymentCount().intValue());
    }

    @Test
    public void shouldRemoveFirstWhenPayedOff() {
	initTestQueue();

	final int oneStepFromPayOff = REQUIRED_PAYMENT_COUNT - 1;
	queue.setPaymentCount(oneStepFromPayOff);
	Mockito.when(queueDaoMock.getFirst(queue.getQueueType())).thenReturn(
		queue);
	testInstance.increaseFirstRowPaymentCount(queue.getQueueType());
	verify(queueDaoMock).getFirst(queue.getQueueType());
	verify(queueDaoMock).delete(queue);
    }

    private void initTestQueue() {
	queue = new Queue();
	queue.setAccount(getAccount());
	queue.setQueueType(QueueType.C500);
	queue.setStatus(ActivationStatus.ACTIVE);
	queue.setPaymentCount(PAYMENT_COUNT);
	queue.setRequiredPaymentCount(REQUIRED_PAYMENT_COUNT);
	queue.setPosition(1);
    }

    private Account getAccount() {
	final Account account = new Account();
	account.setId(1);
	return account;
    }
}
