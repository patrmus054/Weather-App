package com.example.chat_project.activities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.chat_project.Communication.local.client.services.ClientService;
import com.example.chat_project.Communication.local.jobs.JobHandler;
import com.example.chat_project.Communication.local.queues.OutputMessageQueueCollection;
import com.example.chat_project.Communication.local.resources.communication.commands.Greeting;
import com.example.chat_project.Communication.local.resources.communication.commands.ShowLyricsCommand;
import com.example.chat_project.Communication.local.resources.communication.data.CommunicationData;
import com.example.chat_project.Communication.local.resources.communication.data.Emails;
import com.example.chat_project.Communication.local.resources.communication.data.EmptyData;
import com.example.chat_project.Communication.local.resources.communication.data.Lyrics;
import com.example.chat_project.Communication.local.resources.communication.data.Text;
import com.example.chat_project.Communication.local.resources.communication.packages.CommunicationPackage;
import com.example.chat_project.MainActivity;
import com.example.chat_project.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LyricsSlave extends AppCompatActivity {

    private List<String> list = new ArrayList<>();
    private ArrayAdapter<String> adapter;
    private String email;
    private String[] emails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lyrics_slave);

        registerServerGreetingJob();
        registerServerShowLyricJob();

        greetServer();

        ListView messages = findViewById(R.id.messages);
        adapter = new ArrayAdapter<>(this, R.layout.list, list);
        messages.setAdapter(adapter);
        showSelectEmail();
    }

    private void showSelectEmail() {
        LayoutInflater li = LayoutInflater.from(this);
        View promptView = li.inflate(R.layout.prompt, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setView(promptView);
        final EditText userInput = promptView.findViewById(R.id.editTextDialogUserInput);
        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("OK",
                        (dialog, id) -> {
                            for (String s : emails) {
                                if (email.equals(s)) {
                                    AlertDialog alert = new AlertDialog.Builder(this)
                                            .setIcon(android.R.drawable.ic_dialog_alert)
                                            .setTitle("This email is already taken")
                                            .setMessage("Choose other email")
                                            .setPositiveButton("OK", (dialogInterface, i) -> finish())
                                            .show();
                                    showSelectEmail();
                                    return;
                                }
                            }
                            email = userInput.getText().toString();
                        })
                .setNegativeButton("Cancel",
                        (dialog, id) -> {
                            Intent clientActivityIntent = new Intent(this, MainActivity.class);
                            this.startActivity(clientActivityIntent);
                        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    @Override
    public void onBackPressed() {
        /* Disabled go back button action in activity  */
    }

    public void onSendClick(View v) {
        EditText messageText = findViewById(R.id.messageText);
        if (messageText.length() == 0) return;
        Lyrics lyrics = new Lyrics();
        lyrics.setTitle(new SimpleDateFormat("HH:mm:ss").format(new Date()) + " (" + email + ") ");
        lyrics.setLyrics(messageText.getText().toString());
        messageText.setText("");
        messageText.getText().clear();
        CommunicationPackage communicationPackage = new CommunicationPackage();
        communicationPackage.setCommunicationCommand(new ShowLyricsCommand());
        communicationPackage.setCommunicationData(lyrics);
        OutputMessageQueueCollection.getInstance().put(communicationPackage);
        receiveMessage(createMessage(lyrics));
        runOnUiThread(() -> adapter.notifyDataSetChanged());
    }

    private void receiveMessage(String message) {
        list.add(message);
    }

    private String createMessage(CommunicationData data) {
        return ((Lyrics) data).getTitle() + ' ' + ((Lyrics) data).getLyrics();
    }

    private void registerServerGreetingJob() {
        final Context applicationContext = getApplicationContext();
        final Toast connected = Toast.makeText(applicationContext, "Connected", Toast.LENGTH_SHORT);
        JobHandler.getInstance().registerJob(new Greeting(), inputData -> {
            emails = ((Emails) inputData).getEmails();
        });
    }

    private void registerServerShowLyricJob() {
        JobHandler.getInstance().registerJob(new ShowLyricsCommand(), inputData -> {
            receiveMessage(createMessage(inputData));
            runOnUiThread(() -> adapter.notifyDataSetChanged());
        });
    }

    private void greetServer() {
        ClientService clientService = new ClientService();
        clientService.startServerDiscovery(this);
        CommunicationPackage communicationPackage = new CommunicationPackage();
        communicationPackage.setCommunicationCommand(new Greeting());
        communicationPackage.setCommunicationData(new Text(email));
        OutputMessageQueueCollection.getInstance().put(communicationPackage);
    }
}
