package ru.course.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterDescription;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.course.addressbook.model.GroupData;
import ru.course.addressbook.model.Groups;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class GroupDataGenerator {

    @Parameter(names = "-c", description = "Group count")
    public int count;

    @Parameter(names = "-f", description = "Target file")
    public String file;

    @Parameter(names = "-d", description = "Data format")
    public String format;

    public static void main(String[] args) throws IOException {
        GroupDataGenerator generator = new GroupDataGenerator();
        JCommander jc = new JCommander(generator);
        try {
            jc.parse(args);
        } catch (ParameterException ex) {
            jc.usage();
            return;
        }
        generator.run();
    }

    private void run() throws IOException {
        List<GroupData> groups = generateGroupData(count);
        if (format.equals("csv"))
            saveAsCSV(groups, new File(file));
        else if (format.equals("json"))
            saveToJSON(groups, new File(file));
        else
            System.out.println(String.format("Unrecognized format %s", format));
    }

    private void saveAsCSV(List<GroupData> groups, File file) throws IOException {
        try (Writer w = new FileWriter(file)) {
            for (GroupData g : groups) {
                w.write(String.format("%s;%s;%s\n", g.getName(), g.getHeader(), g.getFooter()));
            }
        }
    }

    private void saveToJSON(List<GroupData> groups, File file) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
        String json = gson.toJson(groups);
        try (Writer w = new FileWriter(file)) {
            w.write(json);
        }
    }

    private static List<GroupData> generateGroupData(int count) {
        List<GroupData> groups = new ArrayList<GroupData>();
        for (int i = 0; i < count; i++) {
            groups.add(new GroupData().withName(String.format("name %s", i))
                    .withHeader(String.format("header %s", i))
                    .withFooter(String.format("footer %s", i)));
        }
        return groups;
    }
}
