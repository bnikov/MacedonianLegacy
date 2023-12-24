package com.example.macedonianlegacy.Homework_1;

import java.util.ArrayList;
import java.util.List;

public class Pipe<T> {
    private List<Filter<T>> filters = new ArrayList<Filter<T>>();

    public void addFilter(Filter<T> filter){
        filters.add(filter);
    }

    public T runFilters(T input){
        System.out.println("a");
        for (Filter<T> filter: filters) {
            System.out.println("b");
            input = filter.execute(input);

        }
        return input;
    }
}
