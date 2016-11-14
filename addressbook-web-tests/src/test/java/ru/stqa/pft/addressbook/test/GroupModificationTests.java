package ru.stqa.pft.addressbook.test;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

/**
 * Created by Natusik on 28.10.2016.
 */
public class GroupModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions(){
    app.getNavigationHelper().gotoGroupPage();
    if (! app.getGroupHalper().isThereAGroup()){
      app.getGroupHalper().createGroup(new GroupData("test1", null, null));
    }
  }

  @Test
  public void testGroupModification(){
    List<GroupData> befor = app.getGroupHalper().getGroupList();
    int index = befor.size() - 1;
    GroupData group = new GroupData(befor.get(index).getId(), "test1", "test2", "test3");
    app.getGroupHalper().modifyGroup(index, group);
    List<GroupData> after = app.getGroupHalper().getGroupList();
    Assert.assertEquals(after.size(), befor.size());

    befor.remove(index);
    befor.add(group);
    Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId()) ;
    befor.sort(byId);
    after.sort(byId);
    Assert.assertEquals(befor, after);
  }


}
