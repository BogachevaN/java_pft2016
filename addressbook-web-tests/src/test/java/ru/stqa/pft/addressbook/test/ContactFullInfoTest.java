package ru.stqa.pft.addressbook.test;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Natusik on 19.11.2016.
 */
public class ContactFullInfoTest extends TestBase {

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
  public void testFullInfo(){
    app.goTo().homePage();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
    String ContactFullInfo = cleaned(app.contact().InfoFromFullInfoForm(contact));

    assertThat(ContactFullInfo, equalTo(cleaned(mergeInfo(contactInfoFromEditForm))));
  }

  private String mergeInfo(ContactData contact) {
    return Arrays.asList(contact.getFirstname(), contact.getLastname(), contact.getAddress(),
            contact.getHomePhone(),contact.getMobilePhone(),contact.getWorkPhone(),
            contact.getEmail(),contact.getEmail2(),contact.getEmail3(),
            contact.getAddress2())
            .stream().filter((s) -> !s.equals("")).collect(Collectors.joining("\n"));
  }

  public static String cleaned (String s){
    return s.replaceAll("\\s","").replaceAll("\n","").replaceAll("[-():]","").replaceAll("[HMW]","");
  }
}
