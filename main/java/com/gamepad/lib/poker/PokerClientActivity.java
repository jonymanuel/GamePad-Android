package com.gamepad.lib.poker;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.gamepad.R;
import com.gamepad.lib.GPC;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * Created by Fabian on 26.01.14.
 */
public class PokerClientActivity extends Activity {
    ImageParser imageParser;
    ImageView player_card1, player_card2;
    Deck deck;
    SensorManager sensorManager;
    Sensor accelerometer;
    SensorEventListener accelerometerListener;
    Card card1, card2;
    Vector<Integer> upDownIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.poker_client);
        upDownIndicator = new Vector();
        deck = new Deck();
        player_card1 = (ImageView) findViewById(R.id.player_card1);
        player_card2 = (ImageView) findViewById(R.id.player_card2);
        imageParser = new ImageParser(this);
        GPC.getCmdParser().RegisterCommand(new CardUpdateCommand());
        MyPokerGame.setPokerClientActivity(this);
        getAccelerometer();
    }

    private SensorEventListener createAccelerometerListener() {
        if (accelerometerListener == null) {
            accelerometerListener = new SensorEventListener() {
                @Override
                public void onSensorChanged(SensorEvent arg0) {
                    final SensorEvent event = arg0;
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            sensorChanged(event);
                        }
                    });
                }

                @Override
                public void onAccuracyChanged(Sensor sensor, int i) {

                }
            };
        }
        return accelerometerListener;
    }

    @Override
    public void onStop() {
        super.onStop();
        if (accelerometer != null) {
            sensorManager.unregisterListener(accelerometerListener);
        }
    }

    private void sensorChanged(SensorEvent arg0) {
        if(card1 == null || card2 == null)
        {
            return;
        }
        float distance = arg0.values[0];
        float max = accelerometer.getMaximumRange();
        Log.d("SENSOR", String.valueOf(distance));
        if (distance >= max){
            imageParser.setSVGImage(player_card1, getResources().getIdentifier(deck.getCardResource(card1), "raw", getPackageName()));
            imageParser.setSVGImage(player_card2, getResources().getIdentifier(deck.getCardResource(card2), "raw", getPackageName()));
        }
        else{
            imageParser.setSVGImage(player_card1, getResources().getIdentifier("back", "raw", getPackageName()));
            imageParser.setSVGImage(player_card2, getResources().getIdentifier("back", "raw", getPackageName()));
        }
    }

    private void getAccelerometer() {
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        List<Sensor> sensorList = sensorManager.getSensorList(Sensor.TYPE_PROXIMITY);
        if (sensorList.size() > 0) {
            accelerometer = sensorList.get(0);
            sensorManager.registerListener(createAccelerometerListener(), accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    public void drawCards(Card card1, Card card2) {
        final Card card1F = card1;
        final Card card2F = card2;
        this.card1 = card1;
        this.card2 = card2;
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                imageParser.setSVGImage(player_card1, getResources().getIdentifier(deck.getCardResource(card1F), "raw", getPackageName()));
                imageParser.setSVGImage(player_card2, getResources().getIdentifier(deck.getCardResource(card2F), "raw", getPackageName()));
            }
        });

    }

    @Override
    public void onBackPressed() {

    }
}
