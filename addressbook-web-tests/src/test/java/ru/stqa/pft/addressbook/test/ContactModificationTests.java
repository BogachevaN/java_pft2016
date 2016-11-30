package ru.stqa.pft.addressbook.test;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

/**
 * Created by Natusik on 28.10.2016.
 */
public class ContactModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().contacts().size() == 0){
      app.goTo().homePage();
      app.contact().create(new ContactData().withFirstname("Elena").withLastname("Ivanova")
              .withAddress("Novosibirsk city, Lenina street 5 - 14").withMobilePhone("89135685945")
              .withEmail("Ivanova@mail.ru").withGroup("test1"));
    }
  }

  @Test
  public void testContactModification(){
    Contacts befor = app.db().contacts();
    ContactData modifiedContact = befor.iterator().next();
    ContactData contact = new ContactData().withId(modifiedContact.getId()).withFirstname("Elena")
      .withLastname("Ivanova").withAddress("Novosibirsk city, Lenina street 5 - 14").withHomePhone("25245")
            .withMobilePhone("89135685945").withEmail("Ivanova@mail.ru");
    app.goTo().homePage();
    app.contact().modify(contact);
    assertEquals(app.contact().count(), befor.size());
    Contacts after = app.db().contacts();
    assertThat(after, equalTo(befor.without(modifiedContact).withAdded(contact)));
  }


}
