package ru.sbt.dao.jdbc;

import ru.sbt.dao.Department;
import ru.sbt.domain.DateRange;
import ru.sbt.domain.Employee;
import ru.sbt.domain.Report;
import ru.sbt.domain.jdbc.JdbcReport;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * JavaSchool SBT
 * Created by Sam on 21.11.2016.
 */
public class JdbcDepartment implements Department<Employee> {

    private final Connection connection;
    private final String SQL_QUERY = "select emp.id as emp_id, emp.name as amp_name, sum(salary) as salary from employee emp left join" +
            "salary_payments sp on emp.id = sp.employee_id where emp.department_id = ? and" +
            " sp.date >= ? and sp.date <= ? group by emp.id, emp.name";

    public JdbcDepartment(Connection connection) {
        this.connection = connection;
    }

    @SuppressWarnings("JpaQueryApiInspection")
    private PreparedStatement getPreparedStatement(String departmentId, DateRange dateRange) {
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(SQL_QUERY);
            ps.setString(0, departmentId);
            ps.setDate(1, new java.sql.Date(dateRange.getDateFrom().toEpochDay()));
            ps.setDate(2, new java.sql.Date(dateRange.getDateTo().toEpochDay()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ps;
    }

    private Employee createEmployee(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("emp_id");
        String name = resultSet.getString("amp_name");
        double salary = resultSet.getDouble("salary");

        return new Employee(id, name, salary);
    }

    private void close(PreparedStatement preparedStatement) {
        Objects.requireNonNull(preparedStatement);
        try {
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Report<Employee> generateReport(List<Employee> employees) {
        double totalSalary = 0D;
        for (Employee employee : employees) {
            totalSalary += employee.getSalary();
        }
        return new JdbcReport(employees, totalSalary);
    }

    @Override
    public Report<Employee> getReport(String departmentId, DateRange dateRange) {
        final PreparedStatement preparedStatement = getPreparedStatement(departmentId, dateRange);
        final List<Employee> employees = new LinkedList<>();
        try {
            final ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Employee employee = createEmployee(resultSet);
                employees.add(employee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(preparedStatement);
        }

        return generateReport(employees);
    }
}
