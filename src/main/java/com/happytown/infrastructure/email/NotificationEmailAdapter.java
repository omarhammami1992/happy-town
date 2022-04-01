package com.happytown.infrastructure.email;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import com.happytown.domain.entity.Notification;
import com.happytown.domain.entity.NotificationException;
import com.happytown.domain.port.NotificationPort;

@Component
public class NotificationEmailAdapter implements NotificationPort {

   @Value("${mail.smtp.host}")
   private String smtpHost;

   @Value("${mail.smtp.port}")
   private Integer smtpPort;

   @Value("${mail.smtp.from}")
   private String from;

   @Override
   public void notify(Notification notification) {
      try {
         Properties props = new Properties();
         props.put("mail.smtp.host", smtpHost);
         props.put("mail.smtp.port", smtpPort);
         Session session = Session.getInstance(props, null);

         Message msg = new MimeMessage(session);
         msg.setFrom(new InternetAddress(from));
         msg.setRecipient(Message.RecipientType.TO, new InternetAddress(notification.getTo()));
         msg.setSubject(notification.getSubject());
         msg.setText(notification.getBody());

         Transport.send(msg);
      } catch (MessagingException e) {
         throw new NotificationException("Error while sending email", e);
      }
   }
}
