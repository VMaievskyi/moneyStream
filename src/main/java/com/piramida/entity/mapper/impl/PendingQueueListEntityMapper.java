package com.piramida.entity.mapper.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.collect.Lists;
import com.piramida.entity.PendingQueue;
import com.piramida.entity.dto.PendingQueueDto;
import com.piramida.entity.dto.PendingQueueDtoList;
import com.piramida.entity.mapper.factory.MapperFactory;

@Component("pendingQueueListEntityMapper")
public class PendingQueueListEntityMapper extends
	AbstractDtoEntityMapper<PendingQueueDtoList, List<PendingQueue>> {

    @Autowired
    private MapperFactory mapperFactory;

    @Override
    public List<PendingQueue> map(final PendingQueueDtoList source) {
	return null;
    }

    @Override
    public PendingQueueDtoList unmap(final List<PendingQueue> source) {
	final List<PendingQueueDto> queueDtoList = Lists.newLinkedList();
	if (source != null) {
	    final AbstractDtoEntityMapper mapper = mapperFactory
		    .createInstance(PendingQueueDto.class);
	    for (final PendingQueue queue : source) {
		queueDtoList.add((PendingQueueDto) mapper.unmap(queue));
	    }
	}
	return new PendingQueueDtoList(queueDtoList);
    }
}
