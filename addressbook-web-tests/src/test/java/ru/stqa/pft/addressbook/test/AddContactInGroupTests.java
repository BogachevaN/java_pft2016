package ru.stqa.pft.addressbook.test;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

/**
 * Created by Пользователь on 14.12.2016.
 */
public class AddContactInGroupTests extends TestBase {
    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().contacts().size() == 0){
            app.goTo().homePage();
            app.contact().create(new ContactData().withFirstname("Elena").withLastname("Ivanova")
                    .withAddress("Novosibirsk city, Lenina street 5 - 14").withMobilePhone("89135685945")
                    .withEmail("Ivanova@mail.ru"));
        }
        if (app.db().groups().size() == 0){
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("test1"));
        }
    }

    @Test
    public void testAddContactInGroup(){
        app.goTo().homePage();
        Groups groups = app.db().groups();
        Contacts contacts = app.db().contacts();
        Boolean contactInGroup = false;
        ContactData contactForTest = null;
        int countGroupOnContactBefor = 0;
        for (ContactData contact : contacts) {
            if (contactInGroup == false) {
                for (GroupData group : groups) {
                    if (contactInGroup == false) {
                        Groups contactInGroups = contact.getGroups();
                        if (contactInGroups.size() != 0) {
                            for (GroupData groupContact : contactInGroups) {
                                if (group != groupContact) {
                                    countGroupOnContactBefor = contact.getGroups().size();
                                    contactForTest = contact;
                                    app.contact().addContactInGroup(contact, group);
                                    contactInGroup = true;
                                }
                            }
                        } else {
                            countGroupOnContactBefor = contact.getGroups().size();
                            contactForTest = contact;
                            app.contact().addContactInGroup(contact, group);
                            contactInGroup = true;
                        }
                    }
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
        Assert.assertEquals(contactForTest.getGroups().size(), countGroupOnContactBefor + 1);
    }
}
