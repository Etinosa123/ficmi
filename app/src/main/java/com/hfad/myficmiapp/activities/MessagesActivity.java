package com.hfad.myficmiapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.hfad.myficmiapp.R;
import com.hfad.myficmiapp.model.BookmarkModel;
import com.hfad.myficmiapp.model.TheWordModel;

public class MessagesActivity extends AppCompatActivity {

    TextView speaker, topic, text, message;
    TheWordModel theWordModel = null;
    BookmarkModel bookmarkModel = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messages);

        final Object obj = getIntent().getSerializableExtra("messages");

        if (obj instanceof TheWordModel){
            theWordModel = (TheWordModel)  obj;
        }
        if (obj instanceof BookmarkModel){
            bookmarkModel = (BookmarkModel) obj;
        }
        speaker = findViewById(R.id.speaker);
        topic = findViewById(R.id.topic);
        text = findViewById(R.id.text);
        message = findViewById(R.id.message);

        //getting the text and adding to the message layout
        if (theWordModel != null){
            speaker.setText(theWordModel.getSpeaker());
            topic.setText(theWordModel.getTopic());
            text.setText(theWordModel.getText());
            message.setText(theWordModel.getMessage());

        }

        if (bookmarkModel != null){
            speaker.setText(bookmarkModel.getSpeaker());
            topic.setText(bookmarkModel.getTopic());
            text.setText(bookmarkModel.getText());
            message.setText(bookmarkModel.getMessage());

        }

    }
}