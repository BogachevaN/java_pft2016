package ru.stqa.pft.addressbook.test;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;


/**
 * Created by Пользователь on 15.12.2016.
 */
public class DeleteContactOffGroup extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        Boolean AddContactInGroup = false;
        if (app.db().contacts().size() == 0){
            app.goTo().homePage();
            app.contact().create(new ContactData().withFirstname("Elena").withLastname("Ivanova")
                    .withAddress("Novosibirsk city, Lenina street 5 - 14").withMobilePhone("89135685945")
                    .withEmail("Ivanova@mail.ru"));
            if (app.db().groups().size() != 0){
                AddContactInGroup = true;
            }else {
                app.goTo().groupPage();
                app.group().create(new GroupData().withName("test1"));
                AddContactInGroup = true;
            }
        }
        if (app.db().groups().size() == 0){
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("test1"));
            if (app.db().contacts().size() != 0){
                AddContactInGroup = true;
            } else {
                app.goTo().homePage();
                app.contact().create(new ContactData().withFirstname("Elena").withLastname("Ivanova")
                        .withAddress("Novosibirsk city, Lenina street 5 - 14").withMobilePhone("89135685945")
                        .withEmail("Ivanova@mail.ru"));
                AddContactInGroup = true;
            }
        }
        if (AddContactInGroup == true){
            ContactData contact = app.db().contacts().iterator().next();
            GroupData group = app.db().groups().iterator().next();
            app.contact().addContactInGroup(contact,group);

        }
    }


    @Test
    public void testDeleteContactOffGroup () {
        app.goTo().homePage();
        Contacts contacts = app.db().contacts();
        Boolean testOK = false;
        ContactData contactForTest = null;
        int countGroupBeforTest = 0;
        for (ContactData contact: contacts) {
            if (testOK == false) {
                if (contact.getGroups().size() != 0) {
                    countGroupBeforTest = contact.getGroups().size();
                    GroupData group = contact.getGroups().iterator().next();
                    app.contact().deleteContactOffGroup(contact, group);
                    testOK = true;
                    contactForTest = contact;
                }
            }
        }
        Contacts after = app.db().contacts();
        for (ContactData cont: after){
            if (cont.getId() == contactForTest.getId()){
                contactForTest = cont;
                break;
            }
        }
        app.goTo().homePage();
        Assert.assertEquals(contactForTest.getGroups().size(), countGroupBeforTest - 1);
    }
}
