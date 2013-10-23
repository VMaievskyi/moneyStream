package com.piramida.entity;

public enum QueueType {
    C500(500, 2), C1000(1000, 3);

    private int cost;
    private final int requiredPaymentCount;

    private QueueType(final int cost, final int requiredPaymentCount) {
	this.cost = cost;
	this.requiredPaymentCount = requiredPaymentCount;
    }

    public int getCost() {
	return cost;
    }

    public void setCost(final int cost) {
	this.cost = cost;
    }

    public int getRequiredPaymentCount() {
	return requiredPaymentCount;
    }

}
