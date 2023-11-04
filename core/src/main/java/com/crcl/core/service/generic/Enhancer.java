package com.crcl.core.service.generic;

public @FunctionalInterface interface Enhancer<T> {
    T enhance(T t);
}
