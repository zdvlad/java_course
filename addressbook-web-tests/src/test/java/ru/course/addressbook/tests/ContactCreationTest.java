package ru.course.addressbook.tests;

import org.testng.annotations.Test;
import ru.course.addressbook.model.ContactData;

public class ContactCreationTest extends TestBase{

  @Test
  public void testContactCreation() throws Exception {
    app.getContactHelper().initContactCreation();
    app.getContactHelper().fillContactsData(new ContactData("Denis", "Zakharov", "9023557076", "z.d.vlad96@mail.ru"));
    app.getContactHelper().submitContactCreation();
    app.getNavigationHelper().returnHomePage();
  }
}
