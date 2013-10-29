package com.piramida.facade;

import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.piramida.facade.queue.impl.DefaultQueueFacade;
import com.piramida.service.queue.QueueService;

public class QueueFacadeTest {

    private static final String QUEUE_TYPE = "C500";
    private static final int ID = 0;
    private DefaultQueueFacade testInstance;
    @Mock
    private QueueService queueServiceMock;

    @Before
    public void setUp() {
	MockitoAnnotations.initMocks(this);
	testInstance = new DefaultQueueFacade();
	testInstance.setQueueService(queueServiceMock);
    }

    @Test
    public void shouldFindById() {
	testInstance.findById(ID);
	verify(queueServiceMock).findById(ID);
    }

    @Test
    public void shouldFindFirst() {
	testInstance.findFirst(QUEUE_TYPE);
	verify(queueServiceMock).getFirst(QUEUE_TYPE);
    }
}
