package ru.stqa.pft.addressbook.test;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.testng.Assert.assertEquals;

/**
 * Created by Natusik on 28.10.2016.
 */
public class ContactDeletionTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().homePage();
    if (app.contact().all().size() == 0) {
      app.contact().create(new ContactData().withFirstname("Elena").withLastname("Ivanova")
              .withAddress("Novosibirsk city, Lenina street 5 - 14").withMobilePhone("89135685945")
              .withEmail("Ivanova@mail.ru").withGroup("test1"));
    }
  }

  @Test
  public void testContactDeletion(){
    Contacts befor = app.contact().all();
    ContactData deleteContact = befor.iterator().next();
    app.contact().delete(deleteContact);
    app.goTo().homePage();
    assertEquals(app.contact().count(), befor.size() - 1);
    Contacts after = app.contact().all();
    MatcherAssert.assertThat(after, CoreMatchers.equalTo(befor.without(deleteContact)));

  }


}
