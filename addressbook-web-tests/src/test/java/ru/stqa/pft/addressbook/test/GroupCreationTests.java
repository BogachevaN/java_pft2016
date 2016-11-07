package ru.stqa.pft.addressbook.test;


import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;


public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() {
    app.getNavigationHelper().gotoGroupPage();
    int befor = app.getGroupHalper().getGroupCount();
    app.getGroupHalper().createGroup(new GroupData("test1", null, null));
    int after = app.getGroupHalper().getGroupCount();
    Assert.assertEquals(after, befor + 1);
  }

}
