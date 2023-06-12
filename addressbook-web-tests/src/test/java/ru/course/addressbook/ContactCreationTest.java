package ru.course.addressbook;

import org.testng.annotations.Test;

public class ContactCreationTest extends TestBase{

  @Test
  public void testContactCreation() throws Exception {
    initContactCreation();
    fillContactsData(new ContactData("Denis", "Zakharov", "9023557076", "z.d.vlad96@mail.ru"));
    submitContactCreation();
    returnHomePage();
  }
}
