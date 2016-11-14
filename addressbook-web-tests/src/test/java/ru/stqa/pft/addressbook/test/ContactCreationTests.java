package ru.stqa.pft.addressbook.test;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class ContactCreationTests extends TestBase {

    
    @Test
    public void testContactCreation() {
        app.getNavigationHelper().goToHomePage();
        List<ContactData> befor = app.getContactHelper().getContactList();
        ContactData contact = new ContactData("Elena", "Ivanova", "Novosibirsk city, Lenina street 5 - 14", "89135685945", "Ivanova@mail.ru", "test1");
        app.getContactHelper().createContact(contact);
        app.getContactHelper().returnToHomePage();
        List<ContactData> after = app.getContactHelper().getContactList();

        befor.add(contact);
        Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
        befor.sort(byId);
        after.sort(byId);
        Assert.assertEquals(befor, after);
    }


}
