package com.crcl.core.service.generic;

/**
 * An interface for enhancing objects of type T.
 *
 * @param <T> the type of object to be enhanced.
 */
public @FunctionalInterface interface Enhancer<T> {
    T enhance(T t);
}
