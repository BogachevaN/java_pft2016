package ru.stqa.pft.addressbook.test;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

/**
 * Created by Natusik on 28.10.2016.
 */
public class ContactModificationTests extends TestBase {

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
  public void testContactModification(){
    Set<ContactData> befor = app.contact().all();
    ContactData modifiedContact = befor.iterator().next();
    ContactData contact = new ContactData().withId(modifiedContact.getId()).withFirstname("Elena")
      .withLastname("Ivanova").withAddress("Novosibirsk city, Lenina street 5 - 14")
            .withMobile("89135685945").withEmail("Ivanova@mail.ru");
    app.contact().modify(contact);
    Set<ContactData> after = app.contact().all();
    Assert.assertEquals(after.size(), befor.size());

    befor.remove(modifiedContact);
    befor.add(contact);
    Assert.assertEquals(befor, after);
  }


}
