package com.piramida.entity;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created with IntelliJ IDEA.
 * User: slava
 * Date: 10/20/13
 * Time: 2:49 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class Queue {
    private int id;

    @javax.persistence.Column(name = "id")
    @Id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private Integer position;

    @javax.persistence.Column(name = "position")
    @Basic
    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    private String queueType;

    @javax.persistence.Column(name = "queueType")
    @Basic
    public String getQueueType() {
        return queueType;
    }

    public void setQueueType(String queueType) {
        this.queueType = queueType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Queue queue = (Queue) o;

        if (id != queue.id) return false;
        if (position != null ? !position.equals(queue.position) : queue.position != null) return false;
        if (queueType != null ? !queueType.equals(queue.queueType) : queue.queueType != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (position != null ? position.hashCode() : 0);
        result = 31 * result + (queueType != null ? queueType.hashCode() : 0);
        return result;
    }
}
