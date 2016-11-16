package ru.stqa.pft.addressbook.test;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

/**
 * Created by Natusik on 28.10.2016.
 */
public class GroupModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions(){
    app.goTo().groupPage();
    if (app.group().list().size() == 0){
      app.group().create(new GroupData().withName("test1"));
    }
  }

  @Test
  public void testGroupModification(){
    List<GroupData> befor = app.group().list();
    int index = befor.size() - 1;
    GroupData group = new GroupData()
            .withId(befor.get(index).getId()).withName("test1").withHeader("test2").withFooter("test3");
    app.group().modify(index, group);
    List<GroupData> after = app.group().list();
    Assert.assertEquals(after.size(), befor.size());

    befor.remove(index);
    befor.add(group);
    Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId()) ;
    befor.sort(byId);
    after.sort(byId);
    Assert.assertEquals(befor, after);
  }


}
