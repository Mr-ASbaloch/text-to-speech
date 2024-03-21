package com.example.text_to_speech;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    Button convertButton , clearbtn;
    TextToSpeech textToSpeech;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);
        convertButton = findViewById(R.id.convertButton);
        clearbtn=findViewById(R.id.delButton);

        clearbtn.setOnClickListener(new  View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText("");
//                Toast.makeText(MainActivity.this, "Text Cleared ", Toast.LENGTH_SHORT).show();

            }
        });

        textToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR) {
                    textToSpeech.setLanguage(Locale.US);
                } else {
                    Toast.makeText(MainActivity.this, "Text to Speech initialization failed.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        convertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = editText.getText().toString();
                if (!text.isEmpty()) {
                    textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null);
                } else {
                    Toast.makeText(MainActivity.this, "Please enter some text.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        if (textToSpeech != null) {
            textToSpeech.stop();
            editText.setText("");
            textToSpeech.shutdown();
        }
        super.onDestroy();
    }
}
