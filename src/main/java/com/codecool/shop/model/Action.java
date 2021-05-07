package com.codecool.shop.model;

public enum Action {

    ORDER_FORM("order form"),
    SELECT_PAYMENT_METHOD("select payment method"),
    CORRECT_PAYMENT("correct payment"),
    ORDER_SUMMARY("order summary");

    private String action;

    Action(String action) {
        this.action = action;
    }

    public String getAction() { return action; }
}