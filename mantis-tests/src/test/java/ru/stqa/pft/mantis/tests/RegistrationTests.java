package ru.stqa.pft.mantis.tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.model.MailMessage;

import javax.mail.MessagingException;
import javax.xml.rpc.ServiceException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;


/**
 * Created by Пользователь on 10.12.2016.
 */
public class RegistrationTests extends TestBase {

   // @BeforeMethod
    public void startMailServer(){
        app.mail().start();
    }

    @Test
    public void testRegistration() throws IOException, MessagingException, ServiceException {
        skipIfNotFixed(0000003);
        long now = System.currentTimeMillis();
        String email = String.format("user1%s@localhost.locadomain", now);
        String user = String.format("user%s", now);
        String password = "password";
        app.james().createUser(user, password);
        app.registration().start(user, email);
        //List<MailMessage> mailMessages = app.mail().waitForMail(2, 10000);
        List<MailMessage> mailMessages = app.james().waitForMail(user, password, 60000);
        String confirmationLink = app.registration().findConfirmationLink(mailMessages, email);
        app.registration().finish(confirmationLink, password);
        assertTrue(app.newSession().login(user, password));
    }



   // @AfterMethod (alwaysRun = true)
    public void stopMailServer(){
        app.mail().stop();
    }
}
