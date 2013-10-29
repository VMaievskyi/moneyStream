package com.piramida.entity;

public class QueueType {

    private int sumToPay;
    private int requiredPaymentCount;
    private boolean isVIPOnly;
    private int numberOfVisiblePositions;

    public int getSumToPay() {
	return sumToPay;
    }

    public void setSumToPay(final int sumToPay) {
	this.sumToPay = sumToPay;
    }

    public int getRequiredPaymentCount() {
	return requiredPaymentCount;
    }

    public void setRequiredPaymentCount(final int requiredPaymentCount) {
	this.requiredPaymentCount = requiredPaymentCount;
    }

    public boolean isVIPOnly() {
	return isVIPOnly;
    }

    public void setVIPOnly(final boolean isVIPOnly) {
	this.isVIPOnly = isVIPOnly;
    }

    public int getNumberOfVisiblePositions() {
	return numberOfVisiblePositions;
    }

    public void setNumberOfVisiblePositions(final int numberOfVisiblePositions) {
	this.numberOfVisiblePositions = numberOfVisiblePositions;
    }

}
