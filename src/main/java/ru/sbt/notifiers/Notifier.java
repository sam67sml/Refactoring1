package ru.sbt.notifiers;

/**
 * JavaSchool SBT
 * Created by Sam on 21.11.2016.
 */
public interface Notifier<T> {
    <T> void send(T t);
}
