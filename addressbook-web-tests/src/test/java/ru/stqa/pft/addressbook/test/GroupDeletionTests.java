package ru.stqa.pft.addressbook.test;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;


import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

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
      Groups befor = app.group().all();
      GroupData deleteGroup = befor.iterator().next();
      app.group().delete(deleteGroup);
      Groups after = app.group().all();
      assertEquals(after.size(), befor.size() - 1);
      assertThat(after, equalTo(befor.without(deleteGroup)));

    }




}
