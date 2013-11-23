package com.piramida.facade.pendingqueue;

import com.piramida.entity.dto.PendingQueueDtoList;

public interface PendingQueueFacade {

    PendingQueueDtoList getAllRecordsForCurrentAccount();
}
