package com.piramida.entity;

import java.util.Map;

public final class QueueTypeHolder {

    private Map<String, QueueType> queueTypes;

    public Map<String, QueueType> getQueueTypes() {
	return queueTypes;
    }

    public void setQueueTypes(final Map<String, QueueType> queueTypes) {
	this.queueTypes = queueTypes;
    }

}
