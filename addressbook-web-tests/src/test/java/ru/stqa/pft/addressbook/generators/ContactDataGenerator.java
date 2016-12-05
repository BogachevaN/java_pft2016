package ru.stqa.pft.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.thoughtworks.xstream.XStream;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Natusik on 21.11.2016.
 */
public class ContactDataGenerator {
  @Parameter(names = "-c", description = "Contact count")
  public int count;

  @Parameter (names = "-f", description = "Target file")
  public String file;

  @Parameter (names = "-d", description = "Data format")
  public String format;

  public static void main (String[] args) throws IOException {
    ContactDataGenerator generator = new ContactDataGenerator();
    JCommander jCommander = new JCommander(generator);
    try {
      jCommander.parse(args);
    } catch (ParameterException ex){
      jCommander.usage();
      return;
    }
    generator.run();


  }

  private void run() throws IOException {
    List<ContactData> contacts = genarateContacts(count);
    if (format.equals("csv")) {
      saveAsCsv(contacts, new File(file));
    } else if (format.equals("xml")){
      saveAsXml(contacts, new File(file));
    }
  }

  private void saveAsXml(List<ContactData> contacts, File file) throws IOException {
    XStream xstream = new XStream();
    xstream.processAnnotations(ContactData.class);
    String xml = xstream.toXML(contacts);
    try (Writer writer = new FileWriter (file)) {
      writer.write(xml);
    }
  }

  private void saveAsCsv(List<ContactData> contacts, File file) throws IOException {
    try(Writer writer = new FileWriter(file)) {
      for (ContactData contact : contacts) {
        writer.write(String.format("%s;%s;%s;%s;%s;%s;%s;%s;%s\n", contact.getFirstname(), contact.getLastname(),
                contact.getAddress(), contact.getHomePhone(), contact.getMobilePhone(),
                contact.getWorkPhone(), contact.getEmail(), contact.getGroups(), contact.getPhoto()));
      }
    }
  }

  private List<ContactData> genarateContacts(int count) {
    List<ContactData> cotacts = new ArrayList<>();
    for(int i = 0; i<count; i++){
      File photo = new File("src/test/resources/stru.png");
      cotacts.add(new ContactData().withFirstname(String.format("Elena%s", i)).withLastname(String.format("Ivanova%s", i))
              .withAddress(String.format("Novosibirsk city, Lenina street 5 - %s", i)).
                      withHomePhone(String.format("222%s", i)).withMobilePhone("89135685945")
              .withWorkPhone(String.format("333%s", i)).withEmail(String.format("Ivanova%s@mail.ru", i)).withPhoto(photo));
    }
    return cotacts;
  }
}

