package ru.sbt.bit.ood.solid.homework;

import java.time.LocalDate;
import java.util.Objects;

/**
 * JavaSchool SBT
 * Created by Sam on 11.11.2016.
 */
public class LocalDateRange {
    private final LocalDate dateFrom;
    private final LocalDate dateTo;

    public LocalDateRange(LocalDate dateFrom, LocalDate dateTo) {
        Objects.requireNonNull(dateFrom); Objects.requireNonNull(dateTo);
        if (dateTo.toEpochDay() - dateFrom.toEpochDay() < 0)
            throw new IllegalArgumentException("DateTo must be less that DateFrom");
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
    }

    public LocalDate getDateFrom() {
        return dateFrom;
    }

    public LocalDate getDateTo() {
        return dateTo;
    }
}
