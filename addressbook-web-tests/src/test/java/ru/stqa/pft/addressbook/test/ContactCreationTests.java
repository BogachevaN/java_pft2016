package ru.stqa.pft.addressbook.test;


import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;


import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;


public class ContactCreationTests extends TestBase {

    
    @Test
    public void testContactCreation() {
        app.goTo().homePage();
        Contacts befor = app.contact().all();
        ContactData contact = new ContactData().withFirstname("Elena").withLastname("Ivanova")
                .withAddress("Novosibirsk city, Lenina street 5 - 14").withMobile("89135685945")
                .withEmail("Ivanova@mail.ru").withGroup("test1");
        app.contact().create(contact);
        app.contact().returnToHomePage();
        assertEquals(app.contact().count(), befor.size() + 1);
        Contacts after = app.contact().all();
        assertThat(after, equalTo(
                befor.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
    }


}
