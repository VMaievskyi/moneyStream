package com.piramida.entity.dto;

import java.util.List;

public class PendingQueueDtoList {

    private List<PendingQueueDto> pendingQueueDtos;

    public List<PendingQueueDto> getPendingQueueDtos() {
	return pendingQueueDtos;
    }

    public void setPendingQueueDtos(final List<PendingQueueDto> pendingQueueDtos) {
	this.pendingQueueDtos = pendingQueueDtos;
    }

    public PendingQueueDtoList(final List<PendingQueueDto> pendingQueueDtos) {
	super();
	this.pendingQueueDtos = pendingQueueDtos;
    }

    public PendingQueueDtoList() {
	super();
    }

}
