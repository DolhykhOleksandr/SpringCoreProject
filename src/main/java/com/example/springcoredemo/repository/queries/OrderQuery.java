package com.example.springcoredemo.repository.queries;


public enum OrderQuery {

    GET_ORDER_BY_ID("" +
            "SELECT ID, DATE, COST " +
            "FROM PUBLIC.ORDER " +
            "WHERE ID = ?"),

    GET_ALL_ORDERS("" +
            "SELECT ID, DATE, COST " +
            "FROM PUBLIC.ORDER"),

    SAVE_ORDERS("" +
            "INSERT INTO PUBLIC.ORDER (DATE, COST) " +
            "VALUES (?, ?)"),

    UPDATE_ORDER("" +
            "UPDATE PUBLIC.ORDER " +
            "SET DATE = ?, COST = ? " +
            "WHERE ID = ?"),

    DELETE_ORDER("" +
            "DELETE FROM PUBLIC.ORDER " +
            "WHERE ID = ? ");

    private final String value;

    OrderQuery(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}


