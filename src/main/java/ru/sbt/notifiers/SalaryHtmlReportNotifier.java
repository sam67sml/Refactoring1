package ru.sbt.notifiers;

import ru.sbt.dao.Department;
import ru.sbt.dataconverter.Serializer;
import ru.sbt.dataconverter.View;
import ru.sbt.domain.DateRange;
import ru.sbt.domain.Employee;
import ru.sbt.domain.Report;
import ru.sbt.factory.ReportFactory;
import ru.sbt.factory.htmlreportfactory.SalaryHtmlReportNotifierFactory;
import ru.sbt.notifiers.mail.MailParameters;

import java.sql.Connection;

/**
 * JavaSchool SBT
 * Created by Sam on 21.11.2016.
 */
public class SalaryHtmlReportNotifier {
    private final Department<Employee> department;
    private final Serializer<Report, View> serializer;
    private final Notifier notifier;


    public SalaryHtmlReportNotifier(Connection connection, MailParameters mailParameters) {
        ReportFactory reportFactory = new SalaryHtmlReportNotifierFactory(connection, mailParameters);
        department = reportFactory.createDepartment();
        serializer = reportFactory.createSerializer();
        notifier = reportFactory.createNotifier();
    }

    public void generateAndSendHtmlMailSalaryReport(String departmentId, DateRange dateRange) {

        Report<Employee> report = department.getReport(departmentId, dateRange);
        View view = serializer.serialize(report);
        notifier.send(view);


    }
}
