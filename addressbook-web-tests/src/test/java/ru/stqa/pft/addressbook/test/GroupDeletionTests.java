package ru.stqa.pft.addressbook.test;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Set;

public class GroupDeletionTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions(){
      app.goTo().groupPage();
      if (app.group().all().size() == 0){
        app.group().create(new GroupData().withName("test1"));
      }
    }

    @Test
    public void testGroupDeletion() {
      Set<GroupData> befor = app.group().all();
      GroupData deleteGroup = befor.iterator().next();
      app.group().delete(deleteGroup);
      Set<GroupData> after = app.group().all();
      Assert.assertEquals(after.size(), befor.size() - 1);

      befor.remove(deleteGroup);
      Assert.assertEquals(after, befor);

    }




}
