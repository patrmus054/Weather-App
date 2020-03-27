package com.example.chat_project.Communication.local.resources.communication.data;

public class Emails extends CommunicationData {
    private String[] emails;

    public Emails(String[] emails) {
        this.emails = emails;
    }

    public String[] getEmails() {
        return emails;
    }
}
