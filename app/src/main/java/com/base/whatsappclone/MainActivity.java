package com.base.whatsappclone;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText editTextMessage;
    private Button buttonSend;
    private LinearLayout chatMessageContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        if (intent != null) {
            String profileName = intent.getStringExtra("profileName");

            if (profileName != null) {
                // Set the profile name as the title of the action bar
                getSupportActionBar().setTitle(profileName);
            }
        }

        // Initialize UI elements
        editTextMessage = findViewById(R.id.editTextMessage);
        buttonSend = findViewById(R.id.buttonSend);
        chatMessageContainer = findViewById(R.id.chatMessageContainer);

        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get the message from the EditText
                String message = editTextMessage.getText().toString();

                // Create a new TextView to display the message
                TextView messageTextView = new TextView(MainActivity.this);
                messageTextView.setText(message);

                // Customize the appearance of the messageTextView as needed
                messageTextView.setTextSize(16);
                messageTextView.setPadding(16, 8, 16, 8);

                // Add the messageTextView to the chatMessageContainer
                chatMessageContainer.addView(messageTextView);



                // Do something with the message (e.g., display it in the chat area)
                // You can add it to the chatMessageContainer as a TextView or in any desired format.

                // Clear the EditText after sending the message
                editTextMessage.setText("");
            }
        });



    }
}