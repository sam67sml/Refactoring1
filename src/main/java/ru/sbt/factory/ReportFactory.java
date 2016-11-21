package ru.sbt.factory;

import ru.sbt.dao.Department;
import ru.sbt.dataconverter.Serializer;
import ru.sbt.notifiers.Notifier;

/**
 * JavaSchool SBT
 * Created by Sam on 21.11.2016.
 */
public interface ReportFactory {

    Department createDepartment();

    Serializer createSerializer();

    Notifier createNotifier();
}
