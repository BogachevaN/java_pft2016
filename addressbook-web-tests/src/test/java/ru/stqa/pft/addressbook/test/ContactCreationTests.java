package ru.stqa.pft.addressbook.test;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class ContactCreationTests extends TestBase {

    
    @Test
    public void testContactCreation() {
        app.goTo().homePage();
        Set<ContactData> befor = app.contact().all();
        ContactData contact = new ContactData().withFirstname("Elena").withLastname("Ivanova")
                .withAddress("Novosibirsk city, Lenina street 5 - 14").withMobile("89135685945")
                .withEmail("Ivanova@mail.ru").withGroup("test1");
        app.contact().create(contact);
        app.contact().returnToHomePage();
        Set<ContactData> after = app.contact().all();

        contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt());
        befor.add(contact);
        Assert.assertEquals(befor, after);
    }


}
