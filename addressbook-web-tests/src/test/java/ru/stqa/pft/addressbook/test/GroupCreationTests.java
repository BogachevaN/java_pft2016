package ru.stqa.pft.addressbook.test;


import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;


public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() {
    app.getNavigationHelper().gotoGroupPage();
    List<GroupData> befor = app.getGroupHalper().getGroupList();
    GroupData group = new GroupData("test1", null, null);
    app.getGroupHalper().createGroup(group);
    List<GroupData> after = app.getGroupHalper().getGroupList();
    Assert.assertEquals(after.size(), befor.size() + 1);

    befor.add(group);
    Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId()) ;
    befor.sort(byId);
    after.sort(byId);
    Assert.assertEquals(befor, after);
  }

}
