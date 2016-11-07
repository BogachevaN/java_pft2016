package ru.stqa.pft.addressbook.test;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

public class GroupDeletionTests extends TestBase {

    @Test
    public void testGroupDeletion() {

        app.getNavigationHelper().gotoGroupPage();
        int befor = app.getGroupHalper().getGroupCount();
        if (! app.getGroupHalper().isThereAGroup()){
            app.getGroupHalper().createGroup(new GroupData("test1", null, null));
        }
        app.getGroupHalper().selectGroup();
        app.getGroupHalper().deleteSelectedGroups();
        app.getGroupHalper().returnToGroupPage();
        int after = app.getGroupHalper().getGroupCount();
        Assert.assertEquals(after, befor - 1);
    }


}
