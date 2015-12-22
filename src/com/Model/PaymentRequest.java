package com.Model;

public class PaymentRequest {
	String channel;
    int amount;

    public PaymentRequest(String channel, int amount) {
        this.channel = channel;
        this.amount = amount;
    }
}
