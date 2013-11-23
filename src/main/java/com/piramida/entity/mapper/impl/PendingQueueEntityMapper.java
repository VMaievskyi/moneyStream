package com.piramida.entity.mapper.impl;

import org.springframework.stereotype.Component;

import com.piramida.entity.PendingQueue;
import com.piramida.entity.dto.PendingQueueDto;

@Component("pendingQueueEntityMapper")
public class PendingQueueEntityMapper extends
	AbstractDtoEntityMapper<PendingQueueDto, PendingQueue> {

    @Override
    public PendingQueue map(final PendingQueueDto source) {
	return null;
    }

    @Override
    public PendingQueueDto unmap(final PendingQueue source) {
	final PendingQueueDto dto = new PendingQueueDto();
	dto.setCreationDate(source.getCreationDate());
	dto.setId(source.getSecureId());
	dto.setQueueType(source.getQueue().getQueueType());
	return dto;
    }
}
