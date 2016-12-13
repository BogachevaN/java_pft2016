package ru.stqa.pft.mantis.model;

/**
 * Created by Пользователь on 13.12.2016.
 */

public class UserData {
    public int id;
    public String name;
    public String email;

    public int getId() {
        return id;
    }

    public UserData withId(int id) {
        this.id = id;
        return this;
    }


    public String getName() {
        return name;
    }

    public UserData withName(String name) {
        this.name = name;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserData withEmail(String email) {
        this.email = email;
        return this;
    }
}
