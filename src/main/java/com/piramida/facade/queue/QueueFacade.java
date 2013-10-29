package com.piramida.facade.queue;

import com.piramida.entity.Queue;

public interface QueueFacade {
    Queue findById(final int id);

    Queue findFirst(final String queueType);
}
