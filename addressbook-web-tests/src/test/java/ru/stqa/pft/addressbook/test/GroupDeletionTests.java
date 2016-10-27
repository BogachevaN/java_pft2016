package ru.stqa.pft.addressbook.test;

import org.testng.annotations.Test;

public class GroupDeletionTests extends TestBase {

    @Test
    public void testGroupDeletion() {

        app.getNavigationHelper().gotoGroupPage();
        app.getGroupHalper().selectGroup();
        app.getGroupHalper().deleteSelectedGroups();
        app.getGroupHalper().returnToGroupPage();
    }


}
