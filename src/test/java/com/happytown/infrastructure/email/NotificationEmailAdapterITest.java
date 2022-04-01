package com.happytown.infrastructure.email;

import static java.nio.charset.StandardCharsets.UTF_8;
import static javax.mail.internet.MimeUtility.decode;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import javax.mail.MessagingException;
import org.apache.commons.io.IOUtils;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.migrationsupport.rules.EnableRuleMigrationSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import com.github.sleroy.fakesmtp.core.ServerConfiguration;
import com.github.sleroy.fakesmtp.model.EmailModel;
import com.github.sleroy.junit.mail.server.test.FakeSmtpRule;
import com.happytown.domain.entity.Notification;

@SpringBootTest
@EnableRuleMigrationSupport
@TestPropertySource(properties = {"mail.smtp.host=localhost", "mail.smtp.port=9999"})
class NotificationEmailAdapterITest {

   @Autowired
   private NotificationEmailAdapter notificationEmailAdapter;

   @Rule
   public FakeSmtpRule smtpServer = new FakeSmtpRule(
         ServerConfiguration.create()
               .bind("localhost")
               .charset("UTF-8")
               .port(9999)
               .relayDomains("example.fr"));


   @Test
   void notify_should_send_email_when_server_is_up() throws IOException, MessagingException {
      // Given
      String to = "camille.moulin@example.fr";
      String subject = "Gift";
      String body = "A gift will be sent to you";

      // When
      notificationEmailAdapter.notify(new Notification(to, subject, body));

      // Then
      EmailModel email = smtpServer.mailBox().get(0);
      ByteArrayInputStream mailInputStream = new ByteArrayInputStream(email.getEmailStr().getBytes(UTF_8));
      String emailStr = IOUtils.toString(decode(mailInputStream, "quoted-printable"), UTF_8);
      assertThat(smtpServer.mailBox()).hasSize(1);
      AssertionsForClassTypes.assertThat(email.getFrom()).isEqualTo("mairie@happytown.com");
      AssertionsForClassTypes.assertThat(email.getTo()).isEqualTo(to);
      AssertionsForClassTypes.assertThat(email.getSubject()).isEqualTo(subject);
      AssertionsForClassTypes.assertThat(emailStr).contains(body);
   }
}