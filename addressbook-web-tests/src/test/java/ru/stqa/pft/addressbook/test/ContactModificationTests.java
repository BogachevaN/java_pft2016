package ru.stqa.pft.addressbook.test;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

/**
 * Created by Natusik on 28.10.2016.
 */
public class ContactModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.getNavigationHelper().goToHomePage();
    if (!app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("Elena", "Ivanova", "Novosibirsk city, Lenina street 5 - 14", "89135685945", "Ivanova@mail.ru", "test1"));
    }
  }

  @Test
  public void testContactModification(){
    List<ContactData> befor = app.getContactHelper().getContactList();
    int index = befor.size() - 1;
    ContactData contact = new ContactData(befor.get(index).getId(),"Elena", "Ivanova", "Novosibirsk city, Lenina street 5 - 14", "89135685945", "Ivanova@mail.ru", null);
    app.getContactHelper().modifyContact(index, contact);
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), befor.size());

    befor.remove(index);
    befor.add(contact);
    Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
    befor.sort(byId);
    after.sort(byId);
    Assert.assertEquals(befor, after);
  }


}
