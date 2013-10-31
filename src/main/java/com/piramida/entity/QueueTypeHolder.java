package com.piramida.entity;

import java.util.Map;
import java.util.Set;

public class QueueTypeHolder {

    private Map<String, QueueType> queueTypes;

    public Set<String> getAllQueueTypeNames() {
	return queueTypes.keySet();
    }

    public QueueType getQueuTypeByName(final String key) {
	return queueTypes.get(key);
    }

    public void putQueuType(final String key, final QueueType value) {
	queueTypes.put(key, value);
    }

    public Map<String, QueueType> getQueueTypes() {
	return queueTypes;
    }

    public void setQueueTypes(final Map<String, QueueType> queueTypes) {
	this.queueTypes = queueTypes;
    }

}
