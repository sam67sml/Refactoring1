package ru.sbt.dataconverter.htmlserializer;

import org.junit.Before;
import org.junit.Test;
import ru.sbt.dataconverter.Serializer;
import ru.sbt.dataconverter.View;
import ru.sbt.domain.Employee;
import ru.sbt.domain.Report;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * JavaSchool SBT
 * Created by Sam on 21.11.2016.
 */
public class HtmlSerializerTest {
    Report report;
    Employee employee;

    @Before
    public void setUp() throws Exception {
        report = mock(Report.class);
        employee = mock(Employee.class);
    }

    @Test
    public void serialize() throws Exception {
        final String EXPECTED_HTML_REPORT = "<html><body><table><tr><td>Employee</td><td>Salary</td>" +
                "</tr><tr><td>John Doe</td><td>100.0</td></tr><tr><td>Jane Dow</td><td>50.0</td>" +
                "</tr><tr><td>Total</td><td>150.0</td></tr></table></body></html>";
        List<Employee> employees = Arrays.asList(employee, employee);
        when(report.getEmployees()).thenReturn(employees);
        when(report.getTotals()).thenReturn(150.0);
        when(employee.getName()).thenReturn("John Doe", "Jane Dow");
        when(employee.getSalary()).thenReturn(100.0, 50.0);

        Serializer serializer = new HtmlSerializer();
        View view = (View) serializer.serialize(report);
        assertEquals("failed buildTest", EXPECTED_HTML_REPORT, view.toString());
    }

}