package com.piramida.entity.mapper.impl;

import com.piramida.entity.Queue;
import com.piramida.entity.dto.QueueDto;

public class QueueDtoEntityMapper extends
	AbstractDtoEntityMapper<QueueDto, Queue> {

    @Override
    public Queue map(final QueueDto source) {
	return null;
    }

    @Override
    public QueueDto unmap(final Queue source) {
	final QueueDto dto = new QueueDto();
	dto.setAccountId(source.getAccount().getId());
	dto.setId(source.getId());
	return dto;
    }
}
