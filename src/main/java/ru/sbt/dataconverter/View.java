package ru.sbt.dataconverter;

import java.util.List;

/**
 * JavaSchool SBT
 * Created by Sam on 21.11.2016.
 */
public interface View {
    void addItem(View item);

    void removeItem(View item);

    List<View> getItems();

    StringBuilder toStringBuilder();
}
