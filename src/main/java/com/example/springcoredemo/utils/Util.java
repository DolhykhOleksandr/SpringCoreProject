package com.example.springcoredemo.utils;

import java.util.Objects;

public class Util {

    public static boolean anyNull(Object... vars) {
        for (Object obj : vars) {
            if (Objects.isNull(obj))
                return true;
        }
        return false;
    }
}