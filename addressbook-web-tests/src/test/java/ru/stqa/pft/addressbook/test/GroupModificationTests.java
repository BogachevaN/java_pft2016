package ru.stqa.pft.addressbook.test;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

/**
 * Created by Natusik on 28.10.2016.
 */
public class GroupModificationTests extends TestBase {

  @Test
  public void testGroupModification(){
    app.getNavigationHelper().gotoGroupPage();
    int befor = app.getGroupHalper().getGroupCount();
    if (! app.getGroupHalper().isThereAGroup()){
      app.getGroupHalper().createGroup(new GroupData("test1", null, null));
    }
    app.getGroupHalper().selectGroup();
    app.getGroupHalper().initGroupModification();
    app.getGroupHalper().fillGroupForm(new GroupData("test1", "test2", "test3"));
    app.getGroupHalper().submitGroupModification();
    app.getGroupHalper().returnToGroupPage();
    int after = app.getGroupHalper().getGroupCount();
    Assert.assertEquals(after, befor);
  }
}
