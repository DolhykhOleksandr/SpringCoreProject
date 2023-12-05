package com.example.springcoredemo.repository.queries;

public enum ProductQuery {

    GET_PRODUCT_BY_ORDER("" +
            "SELECT ID, NAME, COST " +
            "FROM PRODUCT P " +
            "   INNER JOIN ORDER_PRODUCT OP " +
            "       ON ID = PRODUCT_ID " +
            "WHERE OP.ORDER_ID = ?"),

    SAVE_PRODUCT("" +
            "INSERT INTO PRODUCT (name, cost) " +
            "VALUES (?,?)"),

    UPDATE_PRODUCT("" +
            "UPDATE PRODUCT " +
            "SET NAME = ?, COST = ? " +
            "WHERE ID = ?"),

    GET_ALL_PRODUCTS("" +
            "SELECT ID, NAME, COST " +
            "FROM PRODUCT"),

    GET_PRODUCT_BY_ID("" +
            "SELECT ID, NAME, COST " +
            "FROM PRODUCT " +
            "WHERE ID = ?"),

    DELETE_PRODUCT("" +
            "DELETE FROM PRODUCT " +
            "WHERE ID = ?");

    private final String value;

    ProductQuery(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}