package com.example.chat_project.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.example.chat_project.Communication.local.resources.communication.commands.CommunicationCommand;
import com.example.chat_project.Communication.local.resources.communication.commands.Greeting;
import com.example.chat_project.Communication.local.resources.communication.commands.ShowLyricsCommand;
import com.example.chat_project.Communication.local.resources.communication.data.CommunicationData;
import com.example.chat_project.Communication.local.resources.communication.data.Emails;
import com.example.chat_project.Communication.local.resources.communication.data.EmptyData;
import com.example.chat_project.Communication.local.resources.communication.data.Lyrics;
import com.example.chat_project.Communication.local.resources.communication.data.Text;
import com.example.chat_project.Communication.local.resources.communication.packages.CommunicationPackage;
import com.example.chat_project.Communication.local.server.services.ServerService;

import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class LyricsMaster extends AppCompatActivity {

    public ServerService serverService;
    private List<String> emails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.emails = new ArrayList<>();
        this.serverService = new ServerService();
        super.onCreate(savedInstanceState);
        startConnection();
    }

    @Override
    public void onBackPressed() {
        /* Disabled go back button action in activity  */
    }

    private void startConnection() {
        serverService.startCommunication(getApplicationContext());
        registerClientGreetingJob();
        registerServerShowLyricJob();
        this.sendGreetingsToOtherDevices();
    }

    private void registerClientGreetingJob() {
        final Toast connected = Toast.makeText(getBaseContext(), "Connected", Toast.LENGTH_SHORT);
        serverService.getJobHandler().registerJob(new Greeting(), inputData -> {
            emails.add(((Text) inputData).getText());
            connected.show();
        });
    }

    private void registerServerShowLyricJob() {
        serverService.getJobHandler().registerJob(new ShowLyricsCommand(), inputData -> {
            sendCommunicationPackageToOtherDevices(new ShowLyricsCommand(), inputData);
        });
    }

    public void sendGreetingsToOtherDevices() {
        this.sendCommunicationPackageToOtherDevices(new Greeting(), new Emails(emails.toArray(new String[emails.size()])));
    }

    private void sendCommunicationPackageToOtherDevices(
            CommunicationCommand communicationCommand,
            CommunicationData communicationData
    ) {
        CommunicationPackage communicationPackage = this.serverService.getNewCommunicationPackage();
        communicationPackage.setCommunicationCommand(communicationCommand);
        communicationPackage.setCommunicationData(communicationData);
        this.serverService.getOutputMessageQueue().put(communicationPackage);
    }
}
