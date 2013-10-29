package com.piramida.entity;

public class QueueType {

    private int sumToPay;
    private int requiredPaymentCount;
    private Boolean isVIPOnly;
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

    public Boolean isVIPOnly() {
	return isVIPOnly;
    }

    public void setVIPOnly(final Boolean isVIPOnly) {
	this.isVIPOnly = isVIPOnly;
    }

    public int getNumberOfVisiblePositions() {
	return numberOfVisiblePositions;
    }

    public void setNumberOfVisiblePositions(final int numberOfVisiblePositions) {
	this.numberOfVisiblePositions = numberOfVisiblePositions;
    }

}
