package com.example.room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import java.util.Random;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    TextInputEditText id, name;
    Button join, create;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //... code
        id = findViewById(R.id.textInputLayout);
        name = findViewById(R.id.textInputLayout2);
        join = findViewById(R.id.button);
        create = findViewById(R.id.button_create);


        join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String meeting_id = id.getText().toString();
                if (meeting_id.length() != 10) {
                    id.setError("Invalid");
                    id.requestFocus();
                    return;
                }
                String meeting_name = name.getText().toString();
                if (meeting_name.isEmpty()) {
                    name.setError("Invalid");
                    name.requestFocus();
                    return;
                }
                startMeeting(meeting_id, meeting_name);
            }
        });

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String meeting_name = name.getText().toString();
                if (meeting_name.isEmpty()) {
                    name.setError("Invalid");
                    name.requestFocus();
                    return;
                }
                startMeeting(getRandomMeetingID(), meeting_name);
            }
        });
    }

    public void startMeeting(String id, String name) {
        String user_id = UUID.randomUUID().toString();
        Intent i = new Intent(this, Room.class);
        i.putExtra("id", id);
        i.putExtra("name", name);
        i.putExtra("userID", user_id);
        startActivity(i);
    }

    public String getRandomMeetingID() {
        StringBuilder id_ = new StringBuilder();
        while (id_.length() != 10) {
            int random = new Random().nextInt(10);
            id_.append(random);
        }
        return id_.toString();
    }
}