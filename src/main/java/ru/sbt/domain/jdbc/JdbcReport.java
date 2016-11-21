package ru.sbt.domain.jdbc;

import ru.sbt.domain.Employee;
import ru.sbt.domain.Report;

import java.util.List;

/**
 * JavaSchool SBT
 * Created by Sam on 21.11.2016.
 */
public class JdbcReport implements Report<Employee> {
    private final List<Employee> employees;
    private final double totalSalary;

    public JdbcReport(List<Employee> employees, double totalSalary) {
        this.employees = employees;
        this.totalSalary = totalSalary;
    }

    @Override
    public List<? extends Employee> getEmployees() {
        return employees;
    }

    @Override
    public double getTotals() {
        return totalSalary;
    }

    @Override
    public String toString() {
        return "Report{" +
                "employees=" + employees +
                ", totalSalary=" + totalSalary +
                '}';
    }
}
