package ru.stqa.pft.addressbook.test;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

/**
 * Created by Natusik on 28.10.2016.
 */
public class ContactDeletionTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.getNavigationHelper().goToHomePage();
    if (!app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("Elena", "Ivanova", "Novosibirsk city, Lenina street 5 - 14", "89135685945", "Ivanova@mail.ru", "test1"));
    }
  }

  @Test
  public void testContactDeletion(){
    List<ContactData> befor = app.getContactHelper().getContactList();
    app.getContactHelper().selectContact(befor.size() - 1);
    app.getContactHelper().deleteSelectedContacts();
    app.getContactHelper().agreeContactDeletion();
    app.getNavigationHelper().goToHomePage();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), befor.size() - 1);

    befor.remove(befor.size() - 1);
    Assert.assertEquals(after, befor);
  }
}
