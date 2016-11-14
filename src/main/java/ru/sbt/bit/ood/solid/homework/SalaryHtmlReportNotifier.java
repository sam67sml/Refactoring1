package ru.sbt.bit.ood.solid.homework;

import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SalaryHtmlReportNotifier {

    private Connection connection;

    public SalaryHtmlReportNotifier(Connection databaseConnection) {
        this.connection = databaseConnection;
    }

    public void generateAndSendHtmlSalaryReport(String departmentId, LocalDateRange dateRange, String recipients) {
        try {
            ResultSet results = getResultSet(departmentId, dateRange);
            StringBuilder resultingHtml = getResultingHtml(results);
            sendMail(recipients, resultingHtml);
        } catch (SQLException | MessagingException e) {
            e.printStackTrace();
        }
    }

    private void sendMail(String recipients, StringBuilder resultingHtml) throws MessagingException {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("mail.google.com");
        MimeMessage message = constructMessage(recipients, resultingHtml, mailSender);
        mailSender.send(message);
    }

    private MimeMessage constructMessage(String recipients, StringBuilder resultingHtml, JavaMailSenderImpl mailSender) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo(recipients);
        helper.setText(resultingHtml.toString(), true);
        helper.setSubject("Monthly department salary report");
        return message;
    }

    private StringBuilder getResultingHtml(ResultSet results) throws SQLException {
        StringBuilder resultingHtml = new StringBuilder();
        resultingHtml.append("<html><body><table><tr><td>Employee</td><td>Salary</td></tr>");
        double totals = getTotals(results, resultingHtml);
        resultingHtml.append("<tr><td>Total</td><td>").append(totals).append("</td></tr>");
        resultingHtml.append("</table></body></html>");
        return resultingHtml;
    }

    private ResultSet getResultSet(String departmentId, LocalDateRange dateRange) throws SQLException {
        final String SQL_COMMAND = "select emp.id as emp_id, emp.name as amp_name, sum(salary) as salary from employee emp left join" +
                "salary_payments sp on emp.id = sp.employee_id where emp.department_id = ? and" +
                " sp.date >= ? and sp.date <= ? group by emp.id, emp.name";

        PreparedStatement ps = connection.prepareStatement(SQL_COMMAND);
        ps.setString(0, departmentId);
        ps.setDate(1, new Date(dateRange.getDateFrom().toEpochDay()));
        ps.setDate(2, new Date(dateRange.getDateTo().toEpochDay()));
        return ps.executeQuery();
    }

    private double getTotals(ResultSet results, StringBuilder resultingHtml) throws SQLException {
        double totals = 0D;
        while (results.next()) {
            resultingHtml.append("<tr>"); // add row start tag
            resultingHtml.append("<td>").append(results.getString("emp_name")).append("</td>"); // appending employee name
            resultingHtml.append("<td>").append(results.getDouble("salary")).append("</td>"); // appending employee salary for period
            resultingHtml.append("</tr>"); // add row end tag
            totals += results.getDouble("salary"); // add salary to totals
        }
        return totals;
    }
}
