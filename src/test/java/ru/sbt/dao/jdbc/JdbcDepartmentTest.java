package ru.sbt.dao.jdbc;

import org.junit.Assert;
import org.junit.Test;
import ru.sbt.dao.Department;
import ru.sbt.domain.DateRange;
import ru.sbt.domain.Report;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * JavaSchool SBT
 * Created by Sam on 21.11.2016.
 */
public class JdbcDepartmentTest {
    @Test
    public void getReport() throws Exception {
        final String EXCEPTED = "Report{employees=[Employee{id=1, name='John Doe', salary=100.0}, Employee{id=2, name='Jane Dow', salary=50.0}], totalSalary=150.0}";
        Connection someFakeConnection = mock(Connection.class);
        ResultSet mockResultSet = getMockedResultSet(someFakeConnection);
        Department departament = new JdbcDepartment(someFakeConnection);
        Report report = departament.getReport(anyString(), new DateRange(LocalDate.now(), LocalDate.now()));
        Assert.assertEquals("failed getReportJDBC", EXCEPTED, report.toString());
    }

    private ResultSet getMockedResultSet(Connection someFakeConnection) throws SQLException {
        PreparedStatement someFakePreparedStatement = mock(PreparedStatement.class);
        ResultSet mockResultSet = mock(ResultSet.class);
        when(someFakePreparedStatement.executeQuery()).thenReturn(mockResultSet);
        when(someFakeConnection.prepareStatement(anyString())).thenReturn(someFakePreparedStatement);
        when(mockResultSet.next()).thenReturn(true, true, false);
        when(mockResultSet.getInt("emp_id")).thenReturn(1, 2);
        when(mockResultSet.getString("amp_name")).thenReturn("John Doe", "Jane Dow");
        when(mockResultSet.getDouble("salary")).thenReturn(100.0, 50.0, 100.0, 50.0);
        return mockResultSet;
    }

}