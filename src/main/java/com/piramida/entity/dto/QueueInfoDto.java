package com.piramida.entity.dto;

import java.io.Serializable;
import java.util.List;

import com.piramida.entity.QueueType;

public class QueueInfoDto implements Serializable {

    private QueueType queue;
    List<QueueDto> queueDtos;

    public QueueType getQueue() {
	return queue;
    }

    public void setQueue(final QueueType queue) {
	this.queue = queue;
    }

    public List<QueueDto> getQueueDtos() {
	return queueDtos;
    }

    public void setQueueDtos(final List<QueueDto> queueDtos) {
	this.queueDtos = queueDtos;
    }

}
