package com.example.springcoredemo.exception;


    public class GlovoDaoException extends RuntimeException{

        public GlovoDaoException() {}

        public GlovoDaoException(String message) {
            super(message);
        }

        public GlovoDaoException(String message, Throwable cause) {
            super(message, cause);
        }

    }

