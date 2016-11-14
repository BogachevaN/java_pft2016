package ru.stqa.pft.addressbook.test;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

public class GroupDeletionTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions(){
      app.getNavigationHelper().gotoGroupPage();
      if (! app.getGroupHalper().isThereAGroup()){
        app.getGroupHalper().createGroup(new GroupData("test1", null, null));
      }
    }

    @Test
    public void testGroupDeletion() {
      List<GroupData> befor = app.getGroupHalper().getGroupList();
      app.getGroupHalper().selectGroup(befor.size() - 1);
      app.getGroupHalper().deleteSelectedGroups();
      app.getGroupHalper().returnToGroupPage();
      List<GroupData> after = app.getGroupHalper().getGroupList();
      Assert.assertEquals(after.size(), befor.size() - 1);

      befor.remove(befor.size() - 1);
      Assert.assertEquals(after, befor);

    }


}
