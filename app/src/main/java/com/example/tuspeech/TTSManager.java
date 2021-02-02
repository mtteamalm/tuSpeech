package com.example.tuspeech;

import android.content.Context;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import java.util.Locale;

/**
 * Created by Nilanchala
 * http://www.javatechig.com
 */
public class TTSManager {

    private TextToSpeech mTts = null;
    private boolean isLoaded = false;

    public void init(Context context) {
        try {
            mTts = new TextToSpeech(context, onInitListener);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private TextToSpeech.OnInitListener onInitListener = new TextToSpeech.OnInitListener() {
        @Override
        public void onInit(int status) {
            //Añadimos línea para indicar que el idioma sea en Español de España
            Locale spanish = new Locale("es","ES");
            if (status == TextToSpeech.SUCCESS) {
                if (status == TextToSpeech.SUCCESS) {
                    //int result = mTts.setLanguage(Locale.US);
                    int result = mTts.setLanguage(spanish);

                    isLoaded = true;

                    if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                        //Modificamos los mensajes de error
                        Log.e("error", "ERROR: Este idioma no está soportado");
                    }
                } else {
                    Log.e("error", "ERROR: Fallo en la inicialización!");
                }
            }
        }
    };


    public void shutDown() {
        mTts.shutdown();
    }

    public void addQueue(String text) {
        if (isLoaded)
            mTts.speak(text, TextToSpeech.QUEUE_ADD, null);
        else
            Log.e("error", "TTS Not Initialized");
    }

    public void initQueue(String text) {

        if (isLoaded)
            mTts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
        else
            Log.e("error", "TTS Not Initialized");
    }
}