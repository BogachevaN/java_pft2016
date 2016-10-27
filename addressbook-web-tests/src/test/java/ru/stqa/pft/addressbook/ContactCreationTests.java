package ru.stqa.pft.addressbook;

import org.testng.annotations.Test;

public class ContactCreationTests extends TestBase{

    
    @Test
    public void testContactCreation() {
        app.initContactCreation();
        app.fillContactForm(new ContactData("Elena", "Ivanova", "Novosibirsk city, Lenina street 5 - 14", "89135685945", "Ivanova@mail.ru"));
        app.submitContactCreation();
        app.goToHomePage();
    }


}
