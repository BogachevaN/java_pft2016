package ru.stqa.pft.mantis.model;

/**
 * Created by Пользователь on 12.12.2016.
 */
public class MailMessage {

    public String to;
    public String text;

    public MailMessage(String to, String text){
        this.to = to;
        this.text = text;
    }
}
