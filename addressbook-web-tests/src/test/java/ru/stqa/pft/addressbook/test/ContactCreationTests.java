package ru.stqa.pft.addressbook.test;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase {

    
    @Test
    public void testContactCreation() {
        app.goTo().homePage();
        List<ContactData> befor = app.contact().list();
        ContactData contact = new ContactData().withFirstname("Elena").withLastname("Ivanova")
                .withAddress("Novosibirsk city, Lenina street 5 - 14").withMobile("89135685945")
                .withEmail("Ivanova@mail.ru").withGroup("test1");
        app.contact().create(contact);
        app.contact().returnToHomePage();
        List<ContactData> after = app.contact().list();

        befor.add(contact);
        Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
        befor.sort(byId);
        after.sort(byId);
        Assert.assertEquals(befor, after);
    }


}
