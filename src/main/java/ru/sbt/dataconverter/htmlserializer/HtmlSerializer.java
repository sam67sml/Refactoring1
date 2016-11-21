package ru.sbt.dataconverter.htmlserializer;

import ru.sbt.dataconverter.Serializer;
import ru.sbt.dataconverter.View;
import ru.sbt.domain.Employee;
import ru.sbt.domain.Report;

import java.util.List;
import java.util.Objects;

/**
 * JavaSchool SBT
 * Created by Sam on 21.11.2016.
 */
public class HtmlSerializer implements Serializer<Report, View> {

    @Override
    public View serialize(Report report) {
        Objects.requireNonNull(report);
        final List<Employee> employees = report.getEmployees();
        StringBuilder sb = new StringBuilder();
        employees.forEach(e -> {
            sb.append("<tr>");
            sb.append("<td>").append(e.getName()).append("</td>");
            sb.append("<td>").append(e.getSalary()).append("</td>");
            sb.append("</tr>");
        });
        sb.append("<tr><td>Total</td><td>").append(report.getTotals()).append("</td></tr>");

        return new HtmlView(sb.toString());
    }

}
