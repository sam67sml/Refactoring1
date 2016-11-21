package ru.sbt.dao;

import ru.sbt.domain.DateRange;
import ru.sbt.domain.Report;

/**
 * JavaSchool SBT
 * Created by Sam on 21.11.2016.
 */
public interface Department<E> {
    Report<E> getReport(String departmentId, DateRange dateRange);
}
