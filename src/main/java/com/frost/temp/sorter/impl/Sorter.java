package com.frost.temp.sorter.impl;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;

public class Sorter<T> implements com.frost.temp.sorter.Sorter {

    private List<T> list;
    private Comparator<T> comparator;
    private Function<T, String> toString;

    public Sorter(List<T> list) {
        this(list, Comparator.comparing(Object::toString), Objects::toString);
    }

    public Sorter(List<T> list, Comparator<T> comparator, Function<T, String> toString) {
        if (Objects.isNull(list) || list.size() == 0 || Objects.isNull(comparator))
            throw new NullPointerException("The collection or comparable object cannot be null or empty.");

        this.list = list;
        this.comparator = comparator;
        this.toString = toString;
    }

    @Override
    public String sortAndToString() {
        list.sort(comparator);
        return toString();
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();

        for (int index = 0; index < list.size(); index++)
            result.append((index + 1) + " - " + toString.apply(list.get(index)) + '\n');

        return result.toString();
    }
}
