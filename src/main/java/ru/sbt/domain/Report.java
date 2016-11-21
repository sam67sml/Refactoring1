package ru.sbt.domain;

import java.util.List;

/**
 * JavaSchool SBT
 * Created by Sam on 21.11.2016.
 */
public interface Report<E> {
    List<? extends E> getEmployees();

    double getTotals();
}
