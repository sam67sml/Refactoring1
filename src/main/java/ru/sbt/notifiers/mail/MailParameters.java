package ru.sbt.notifiers.mail;

/**
 * JavaSchool SBT
 * Created by Sam on 21.11.2016.
 */
public class MailParameters {
    private final String recipients;
    private final String subject;
    private final String host;
    private final boolean isHtml;

    public MailParameters(String recipients, String subject, String host, boolean isHtml) {
        this.recipients = recipients;
        this.subject = subject;
        this.host = host;
        this.isHtml = isHtml;
    }

    public String getRecipients() {
        return recipients;
    }

    public String getSubject() {
        return subject;
    }

    public String getHost() {
        return host;
    }

    public boolean isHtml() {
        return isHtml;
    }
}
