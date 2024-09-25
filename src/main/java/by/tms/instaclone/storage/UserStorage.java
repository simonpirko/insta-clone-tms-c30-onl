package by.tms.instaclone.storage;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import by.tms.instaclone.model.User_new;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class UserStorage {

    String fileName = "db/user.csv";
    ClassLoader classLoader = getClass().getClassLoader();
    File csvFile = new File(Objects.requireNonNull(classLoader.getResource(fileName)).getFile());

    public UserStorage() {
    }

    public void saveAllUser(List<User_new> users) {
        CsvMapper mapper = new CsvMapper();
        mapper.findAndRegisterModules();
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        CsvSchema schema = mapper.schemaFor(User_new.class).withColumnSeparator(';').withoutQuoteChar().withoutHeader();
        ObjectWriter writer = mapper.writer(schema);
        try {
            new FileWriter(csvFile, StandardCharsets.UTF_8).close();
            for (User_new user : users) {
                writer.writeValue(new FileWriter(csvFile, StandardCharsets.UTF_8, true), user);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void addUser(User_new user) {
        CsvMapper mapper = new CsvMapper();
        mapper.findAndRegisterModules();
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        CsvSchema schema = mapper.schemaFor(User_new.class).withColumnSeparator(';').withoutQuoteChar().withoutHeader();
        ObjectWriter writer = mapper.writer(schema);
        try {
            writer.writeValue(new FileWriter(csvFile, StandardCharsets.UTF_8, true), user);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeUser(String username) {
        List<User_new> users = getAllUsers();
        users.removeIf(user -> user.getUsername().equals(username));
        saveAllUser(users);
    }

    public Optional<User_new> getUserByUsername(String username) {
        List<User_new> users = getAllUsers();
        for (User_new user : users) {
            if (user.getUsername().equals(username)) {
                return Optional.of(user);
            }
        }
        return Optional.empty();
    }

    public void changePassword(String username, String newPassword) {
        List<User_new> users = getAllUsers();
        for (User_new user : users) {
            if (user.getUsername().equals(username)) {
                user.setPassword(newPassword);
            }
        }
        saveAllUser(users);
    }

    public void changeName(String username, String newName) {
        List<User_new> users = getAllUsers();
        for (User_new user : users) {
            if (user.getUsername().equals(username)) {
                user.setName(newName);
            }
        }
        saveAllUser(users);
    }

    public void changeUsername(String oldUsername, String newUsername) {
        List<User_new> users = getAllUsers();
        for (User_new user : users) {
            if (user.getUsername().equals(oldUsername)) {
                user.setUsername(newUsername);
            }
        }
        saveAllUser(users);
    }

    public List<User_new> getAllUsers() {
        try (Reader reader = new FileReader(csvFile)) {
            CsvMapper mapper = new CsvMapper();
            mapper.findAndRegisterModules();
            mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
            CsvSchema schema = mapper.schemaFor(User_new.class).withColumnSeparator(';').withoutQuoteChar().withoutHeader();
            MappingIterator<User_new> iterator = mapper.readerFor(User_new.class).with(schema).readValues(reader);
            return iterator.readAll();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
