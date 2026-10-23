package ru.stellarburgers.data;

import com.github.javafaker.Faker;

public class UserData {

    public static Faker user = new Faker();
    public final static String EMAIL = user.internet().emailAddress();
    public final static String PASSWORD = user.name().lastName() + user.regexify("[0-9]{4}[!@#$%^&*]");
    public final static String NAME = user.name().firstName();
}
