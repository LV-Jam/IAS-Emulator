package com.architecture;

public class Nullable<T> {
    private T value;

    public Nullable() {
        value = null;
    }

    public Nullable(T value) {
        this.value = value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public boolean hasValue() {
        return (value != null);
    }

    public T getValue() {
        if (! hasValue()) throw new NullPointerException();

        return value;
    }

    public T getValueOrElse(T _else) {
        if (hasValue()) return value;

        return _else;
    }
}
