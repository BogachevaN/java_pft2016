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
      if (app.db().groups().size() == 0){
        app.goTo().groupPage();
        app.group().create(new GroupData().withName("test1"));
      }
    }

    @Test
    public void testGroupDeletion() {
      Groups befor = app.db().groups();
      GroupData deleteGroup = befor.iterator().next();
      app.goTo().groupPage();
      app.group().delete(deleteGroup);
      assertEquals(app.group().count(), befor.size() - 1);
      Groups after = app.db().groups();
      assertThat(after, equalTo(befor.without(deleteGroup)));

    }




}
