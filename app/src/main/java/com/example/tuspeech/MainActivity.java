package com.example.tuspeech;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText editText;
    private Button btnSpeech;
    private TTSManager ttsManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        configView();
    }

    private void configView() {

        //Inicializamos el controlador
        ttsManager = new TTSManager();
        ttsManager.init(this);

        editText = findViewById(R.id.activityMainEtText);
        btnSpeech = findViewById(R.id.activityMainBtnSpeech);

        //Listener para el botón
        btnSpeech.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ttsManager.initQueue(editText.getText().toString());
            }
        });
    }

    /**Sobrecargamos el método onDestroy para liberar el controlador*/
    @Override
    protected void onDestroy() {
        super.onDestroy();
        ttsManager.shutDown();
    }
}