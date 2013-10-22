package com.piramida.entity;

public enum QueueType {
    C500(500), C1000(1000);

    private int cost;

    private QueueType(final int cost) {
	this.cost = cost;
    }

    public int getCost() {
	return cost;
    }

    public void setCost(final int cost) {
	this.cost = cost;
    }

}
