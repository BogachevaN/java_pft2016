package ru.stqa.pft.mantis.tests;

import com.sun.jna.platform.win32.Netapi32Util;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.mantis.model.MailMessage;
import ru.stqa.pft.mantis.model.UserData;

import java.io.IOException;
import java.sql.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.testng.Assert.assertTrue;

/**
 * Created by Пользователь on 13.12.2016.
 */
public class ChangePasswordTests extends TestBase {

    @BeforeMethod
    public void startMailServer(){
        app.mail().start();
    }

    @Test
    public void testChangePassword() throws IOException {
        String password = "new_password";
        app.registration().loginAdmin();
        app.registration().goToPageUsers();
        UserData user = chooseUser();
        app.registration().change(user);
        List<MailMessage> mailMessages = app.mail().waitForMail(1, 10000);
        String confirmationLink = app.registration().findConfirmationLink(mailMessages, user.getEmail());
        app.registration().finish(confirmationLink, password);
        assertTrue(app.newSession().login(user.getName(), password));
    }

    private UserData chooseUser() {
        Connection conn = null;
        Set<UserData> users = new HashSet<UserData>();
        try {
            conn =
                    DriverManager.getConnection("jdbc:mysql://localhost/bugtracker?serverTimezone=UTC&user=root&password=");
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("select id, username, email from mantis_user_table");
            while (rs.next()) {
                if (rs.getInt("id") != 1) {
                    users.add(new UserData().withId(rs.getInt("id")).withName(rs.getString("username"))
                            .withEmail(rs.getString("email")));
                }
            }
            rs.close();
            st.close();
            conn.close();
        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        return users.iterator().next();
    }

    @AfterMethod(alwaysRun = true)
    public void stopMailServer(){
            app.mail().stop();
        }
}
