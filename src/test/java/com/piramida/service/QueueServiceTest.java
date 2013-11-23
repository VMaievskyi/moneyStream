package com.piramida.service;

import static org.mockito.Mockito.verify;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.piramida.controller.exception.BusinessException;
import com.piramida.dao.queue.QueueDao;
import com.piramida.entity.Account;
import com.piramida.entity.Queue;
import com.piramida.entity.QueueType;
import com.piramida.service.queue.impl.QueueServiceImpl;

public class QueueServiceTest {

    private static final int COUNT_TO_RETURN = 5;
    private static final int START_IDX = 1;
    private static final int ID = 2;
    private static final int REQUIRED_PAYMENT_COUNT = 2;
    @Mock
    private QueueDao queueDaoMock;
    @Mock
    private QueueType queueTypeMock;
    private Queue queue;
    private QueueServiceImpl testInstance;

    @Before
    public void setUp() {
	MockitoAnnotations.initMocks(this);
	testInstance = new QueueServiceImpl();
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
    public void shouldFindAllWithRange() {
	testInstance.findAllRange(START_IDX, COUNT_TO_RETURN);
	verify(queueDaoMock).findAllRange(START_IDX, COUNT_TO_RETURN);
    }

    @Test
    public void shouldFindById() {
	final Queue result = testInstance.findById(ID);
	verify(queueDaoMock).findById(ID);
    }

    @Ignore
    // TODO: UPDATE THIS TEST
    @Test
    public void shouldIncreasePaymentCount() throws BusinessException {
	initTestQueue();
	Mockito.when(queueDaoMock.getFirst(queue.getQueueType())).thenReturn(
		queue);
	// testInstance.placeNewQueueRecord(queue.getQueueType(), new
	// Account());
	verify(queueDaoMock).getFirst(queue.getQueueType());
	verify(queueDaoMock).save(queue);
    }

    @Test
    public void shouldRemoveRecord() {
	initTestQueue();
	testInstance.remove(queue);
	verify(queueDaoMock).delete(queue);
    }

    @Test
    public void shouldReturnFirstRow() {
	initTestQueue();
	Mockito.when(queueDaoMock.getFirst(queue.getQueueType())).thenReturn(
		queue);
	Mockito.when(queueTypeMock.getName()).thenReturn(queue.getQueueType());
	final Queue firstRow = testInstance.getFirst(queueTypeMock);
	verify(queueDaoMock).getFirst(queue.getQueueType());
	Assert.assertNotNull("Nothing were returned", firstRow);
    }

    @Test
    public void shouldSwitchRowsPosition() {
	initTestQueue();
	final Queue secondRow = new Queue();
	testInstance.switchPositions(queue, secondRow);
	verify(queueDaoMock).switchPositions(queue, secondRow);
    }

    private Account getAccount() {
	final Account account = new Account();
	account.setId(1);
	return account;
    }

    private void initTestQueue() {
	queue = new Queue();
	queue.setAccount(getAccount());
	queue.setQueueType("C500");
	queue.setRequiredPaymentCount(REQUIRED_PAYMENT_COUNT);
    }
}
