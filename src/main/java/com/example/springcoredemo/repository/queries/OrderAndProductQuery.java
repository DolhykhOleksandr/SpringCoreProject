package com.example.springcoredemo.repository.queries;

public enum OrderAndProductQuery {

    SAVE_ORDER_PRODUCT("" +
            "INSERT INTO ORDER_PRODUCT (ORDER_ID, PRODUCT_ID) " +
            "VALUES (?, ?)"),

    DELETE_ORDER_PRODUCT("" +
            "DELETE FROM ORDER_PRODUCT " +
            "WHERE ORDER_ID = ?");

    private String value;

    OrderAndProductQuery(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}