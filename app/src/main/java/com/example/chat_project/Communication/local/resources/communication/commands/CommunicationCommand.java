package com.example.chat_project.Communication.local.resources.communication.commands;

import java.io.Serializable;

public class CommunicationCommand implements Serializable, CommandName {

    @Override
    public String getCommandName() {
        return "DISCONNECT";
    }
}
