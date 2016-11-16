package ru.stqa.pft.addressbook.test;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;
import java.util.Set;

/**
 * Created by Natusik on 28.10.2016.
 */
public class ContactDeletionTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().homePage();
    if (app.contact().all().size() == 0) {
      app.contact().create(new ContactData().withFirstname("Elena").withLastname("Ivanova")
              .withAddress("Novosibirsk city, Lenina street 5 - 14").withMobile("89135685945")
              .withEmail("Ivanova@mail.ru").withGroup("test1"));
    }
  }

  @Test
  public void testContactDeletion(){
    Set<ContactData> befor = app.contact().all();
    ContactData deleteContact = befor.iterator().next();
    app.contact().delete(deleteContact);
    app.goTo().homePage();
    Set<ContactData> after = app.contact().all();
    Assert.assertEquals(after.size(), befor.size() - 1);

    befor.remove(deleteContact);
    Assert.assertEquals(after, befor);
  }


}
