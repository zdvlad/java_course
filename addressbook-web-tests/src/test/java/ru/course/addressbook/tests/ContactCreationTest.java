package ru.course.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.course.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class ContactCreationTest extends TestBase{

  @Test
  public void testContactCreation() throws Exception {
      List<ContactData> before = app.getContactHelper().getContactList();
      app.getContactHelper().initContactCreation();
      ContactData contactData = new ContactData("Anna", "Valova", "98764332", "valova@mail.ru");
      app.getContactHelper().fillContactsData(contactData);
      app.getContactHelper().submitContactCreation();
      app.getContactHelper().returnHomePage();
      List<ContactData> after = app.getContactHelper().getContactList();
      Assert.assertEquals(after.size(), before.size()+1);

      contactData.setId(after.stream().max((c1, c2) -> Integer.compare(c1.getId(), c2.getId())).get().getId());
      before.add(contactData);
      Comparator<? super ContactData> byId = (c1,c2)->Integer.compare(c1.getId(),c2.getId());
      before.sort(byId);
      after.sort(byId);
      Assert.assertEquals(before, after);
  }
}
