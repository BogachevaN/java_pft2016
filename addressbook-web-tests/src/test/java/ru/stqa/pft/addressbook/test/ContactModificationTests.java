package ru.stqa.pft.addressbook.test;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

/**
 * Created by Natusik on 28.10.2016.
 */
public class ContactModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().homePage();
    if (app.contact().list().size() == 0) {
      app.contact().create(new ContactData().withFirstname("Elena").withLastname("Ivanova")
              .withAddress("Novosibirsk city, Lenina street 5 - 14").withMobile("89135685945")
              .withEmail("Ivanova@mail.ru").withGroup("test1"));
    }
  }

  @Test
  public void testContactModification(){
    List<ContactData> befor = app.contact().list();
    int index = befor.size() - 1;
    ContactData contact = new ContactData().withId(befor.get(index).getId()).withFirstname("Elena")
      .withLastname("Ivanova").withAddress("Novosibirsk city, Lenina street 5 - 14")
            .withMobile("89135685945").withEmail("Ivanova@mail.ru");
    app.contact().modify(index, contact);
    List<ContactData> after = app.contact().list();
    Assert.assertEquals(after.size(), befor.size());

    befor.remove(index);
    befor.add(contact);
    Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
    befor.sort(byId);
    after.sort(byId);
    Assert.assertEquals(befor, after);
  }


}
