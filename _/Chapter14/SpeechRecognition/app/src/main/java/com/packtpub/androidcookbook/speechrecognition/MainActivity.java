package com.packtpub.androidcookbook.speechrecognition;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private final int REQUEST_SPEECH=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PackageManager pm = getPackageManager();
        List<ResolveInfo> activities = pm.queryIntentActivities(
                new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH), 0);
        if (activities.size() == 0) {
            findViewById(R.id.imageButton).setEnabled(false);
            Toast.makeText(this, "Speech Recognition Not Supported", Toast.LENGTH_LONG).show();
        }

        SpeechRecognizer speechRecognizer = SpeechRecognizer.createSpeechRecognizer(this);
    }

    public void speakNow(View view){
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        startActivityForResult(intent, REQUEST_SPEECH);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==REQUEST_SPEECH && resultCode == RESULT_OK && data!=null) {
            ArrayList<String> result = data
                    .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);

//            float[] confidence = data
//                    .getFloatArrayExtra(RecognizerIntent.EXTRA_CONFIDENCE_SCORES);

            TextView textView = (TextView)findViewById(R.id.textView);
            if (result.size()>0){
                textView.setText("");
                for (String item : result ) {
                    textView.append(item+"\n");
                }
            }
        }
    }

}
