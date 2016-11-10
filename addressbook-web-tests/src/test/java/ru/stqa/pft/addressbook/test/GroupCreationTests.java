package ru.stqa.pft.addressbook.test;


import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

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


    group.setId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId());
    befor.add(group);
    Assert.assertEquals(new HashSet<Object>(befor), new HashSet<Object>(after));
  }

}
