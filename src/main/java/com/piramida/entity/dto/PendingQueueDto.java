package com.piramida.entity.dto;

import java.util.Date;

public class PendingQueueDto {

    private String id;
    private String queueType;
    private Date creationDate;

    public String getId() {
	return id;
    }

    public void setId(final String id) {
	this.id = id;
    }

    public Date getCreationDate() {
	return creationDate;
    }

    public void setCreationDate(final Date creationDate) {
	this.creationDate = creationDate;
    }

    public String getQueueType() {
	return queueType;
    }

    public void setQueueType(final String queueType) {
	this.queueType = queueType;
    }

}
