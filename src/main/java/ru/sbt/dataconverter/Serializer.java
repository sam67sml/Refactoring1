package ru.sbt.dataconverter;

/**
 * JavaSchool SBT
 * Created by Sam on 21.11.2016.
 */
public interface Serializer<T, R> {
    R serialize(T t);
}
