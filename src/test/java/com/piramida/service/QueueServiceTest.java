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
	testInstance.putInQueue(queue);
	verify(queueDaoMock).save(queue);
    }

    @Test
    public void shouldRemoveRecord() {
	testInstance.remove(queue);
	verify(queueDaoMock).delete(queue);
    }

    @Test
    public void shouldSwitchRowsPosition() {
	final Queue secondRow = new Queue();
	testInstance.switchPositions(queue, secondRow);
	verify(queueDaoMock).switchPositions(queue, secondRow);
    }

    @Test
    public void shouldReturnFirstRow() {
	Mockito.when(queueDaoMock.getFirst()).thenReturn(queue);
	final Queue firstRow = testInstance.getFirst();
	verify(queueDaoMock).getFirst();
	Assert.assertNotNull("Nothing were returned", firstRow);

    }

    private void initTestQueue() {
	queue = new Queue();
	queue.setAccount(getAccount());
	queue.setQueueType(QueueType.C500);
	queue.setStatus(ActivationStatus.ACTIVE);
	queue.setPaymentCount(0);
	queue.setRequiredPaymentCount(QueueType.C500.getRequiredPaymentCount());
	queue.setPosition(1);
    }

    private Account getAccount() {
	final Account account = new Account();
	account.setId(1);
	return account;
    }
}
