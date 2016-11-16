package ru.stqa.pft.addressbook.test;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Set;

/**
 * Created by Natusik on 28.10.2016.
 */
public class GroupModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions(){
    app.goTo().groupPage();
    if (app.group().all().size() == 0){
      app.group().create(new GroupData().withName("test1"));
    }
  }

  @Test
  public void testGroupModification(){
    Set<GroupData> befor = app.group().all();
    GroupData modifiedGroup = befor.iterator().next();
    GroupData group = new GroupData()
            .withId(modifiedGroup.getId()).withName("test1").withHeader("test2").withFooter("test3");
    app.group().modify(group);
    Set<GroupData> after = app.group().all();
    Assert.assertEquals(after.size(), befor.size());

    befor.remove(modifiedGroup);
    befor.add(group);
    Assert.assertEquals(befor, after);
  }


}
