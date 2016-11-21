package ru.sbt.dataconverter.htmlserializer;

import ru.sbt.dataconverter.View;

import java.util.ArrayList;
import java.util.List;

/**
 * JavaSchool SBT
 * Created by Sam on 21.11.2016.
 */
public class HtmlView implements View {

    private final List<View> items = new ArrayList<>();
    private final String body;

    public HtmlView(String body) {
        this.body = body;
    }

    @Override
    public void addItem(View item) {
        items.add(item);
    }

    @Override
    public void removeItem(View item) {
        items.remove(item);
    }

    @Override
    public List<View> getItems() {
        return items;
    }

    @Override
    public StringBuilder toStringBuilder() {
        final StringBuilder builder = new StringBuilder();

        builder.append("<html><body><table><tr><td>Employee</td><td>Salary</td></tr>");

        builder.append(body);

        builder.append("</table></body></html>");

        return builder;
    }

    @Override
    public String toString() {
        return this.toStringBuilder().toString();
    }
}
