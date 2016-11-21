package ru.sbt.notifiers.mail;

import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import ru.sbt.notifiers.Notifier;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * JavaSchool SBT
 * Created by Sam on 21.11.2016.
 */
public class MailNotifier implements Notifier {
    private final MailParameters mailParameters;

    private final JavaMailSenderImpl mailSender = new JavaMailSenderImpl();


    public MailNotifier(MailParameters mailParameters) {
        this.mailParameters = mailParameters;
    }

    @Override
    public void send(Object messageText) {
        mailSender.setHost(mailParameters.getHost());
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper messageHelper = new MimeMessageHelper(message, true);
            messageHelper.setTo(mailParameters.getRecipients());
            messageHelper.setText(messageText.toString(), mailParameters.isHtml());
            messageHelper.setSubject(mailParameters.getSubject());
            mailSender.send(message);

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

    }
}
