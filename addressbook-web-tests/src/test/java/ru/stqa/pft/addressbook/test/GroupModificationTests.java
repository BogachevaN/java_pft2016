package ru.stqa.pft.addressbook.test;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.HashSet;
import java.util.List;

/**
 * Created by Natusik on 28.10.2016.
 */
public class GroupModificationTests extends TestBase {

  @Test
  public void testGroupModification(){
    app.getNavigationHelper().gotoGroupPage();
    if (! app.getGroupHalper().isThereAGroup()){
      app.getGroupHalper().createGroup(new GroupData("test1", null, null));
    }
    List<GroupData> befor = app.getGroupHalper().getGroupList();
    app.getGroupHalper().selectGroup(befor.size() - 1);
    app.getGroupHalper().initGroupModification();
    GroupData group = new GroupData(befor.get(befor.size() - 1).getId(), "test1", "test2", "test3");
    app.getGroupHalper().fillGroupForm(group);
    app.getGroupHalper().submitGroupModification();
    app.getGroupHalper().returnToGroupPage();
    List<GroupData> after = app.getGroupHalper().getGroupList();
    Assert.assertEquals(after.size(), befor.size());

    befor.remove(befor.size() - 1);
    befor.add(group);
    Assert.assertEquals(new HashSet<Object>(befor), new HashSet<Object>(after));
  }
}
