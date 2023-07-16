package ru.course.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.course.addressbook.model.ContactData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator {

    @Parameter(names = "-c", description = "Contact count")
    public int count;

    @Parameter(names = "-f", description = "Target file")
    public String file;

    @Parameter(names = "-d", description = "Data format")
    public String format;

    public static void main(String[] args) throws IOException {
        ContactDataGenerator generator = new ContactDataGenerator();
        JCommander jc  = new JCommander(generator);
        try{
            jc.parse(args);
        } catch (ParameterException ex){
            jc.usage();
            return;
        }
        generator.run();
    }

    private void run() throws IOException {
        List<ContactData> contacts = generateContactData(count);
        if(format.equals("csv"))
            saveAsCSV(contacts, new File(file));
        else if(format.equals("json"))
            saveToJSON(contacts, new File(file));
        else
            System.out.println(String.format("Unrecognized format %s", format));
    }

    private void saveAsCSV(List<ContactData> contacts, File file) throws IOException {
        Writer w = new FileWriter(file);
        for(ContactData contact : contacts)
        {
            w.write(String.format("%s;%s;%s\n", contact.getFirstName(), contact.getLastName(), contact.getEmail()));
        }
        w.close();
    }

    private void saveToJSON(List<ContactData> contacts, File file) throws IOException {
        Gson gson  = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
        String json = gson.toJson(contacts);
        Writer w = new FileWriter(file);
        w.write(json);
        w.close();
    }

    private static List<ContactData> generateContactData(int count) {
        List<ContactData> contactData = new ArrayList<ContactData>();
        for (int i = 0; i < count; i++) {
            contactData.add(new ContactData().withFirstName(String.format("FirstName %s", i))
                    .withLastName(String.format("LastName %s", i))
                    .withEmail(String.format("Email %s", i))
            .withMobilePhoneNumber(String.format("8888%s",i))
            .withAddress(String.format("ул. Пионерская %s", i+10)));
        }
        return contactData;
    }
}
