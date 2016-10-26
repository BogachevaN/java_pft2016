package ru.stqa.pft.addressbook;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class ContactCreationTests extends TestBase{

    
    @Test
    public void testContactCreation() {
        initContactCreation();
        fillContactForm(new ContactData("Elena", "Ivanova", "Novosibirsk city, Lenina street 5 - 14", "89135685945", "Ivanova@mail.ru"));
        submitContactCreation();
        goToHomePage();
    }


}
