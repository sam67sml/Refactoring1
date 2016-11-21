package ru.sbt.factory.htmlreportfactory;

import ru.sbt.dao.Department;
import ru.sbt.dao.jdbc.JdbcDepartment;
import ru.sbt.dataconverter.Serializer;
import ru.sbt.dataconverter.htmlserializer.HtmlSerializer;
import ru.sbt.factory.ReportFactory;
import ru.sbt.notifiers.Notifier;
import ru.sbt.notifiers.mail.MailNotifier;
import ru.sbt.notifiers.mail.MailParameters;

import java.sql.Connection;

/**
 * JavaSchool SBT
 * Created by Sam on 21.11.2016.
 */
public class SalaryHtmlReportNotifierFactory implements ReportFactory {
    private final Department department;
    private final Serializer serializer;
    private final Notifier notifier;

    public SalaryHtmlReportNotifierFactory(Connection connection, MailParameters params) {
        department = new JdbcDepartment(connection);
        serializer = new HtmlSerializer();
        notifier = new MailNotifier(params);
    }

    @Override
    public Department createDepartment() {
        return department;
    }

    @Override
    public Serializer createSerializer() {
        return serializer;
    }

    @Override
    public Notifier createNotifier() {
        return notifier;
    }
}
