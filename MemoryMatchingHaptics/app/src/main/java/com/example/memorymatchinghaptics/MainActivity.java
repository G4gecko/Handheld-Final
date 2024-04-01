package com.example.memorymatchinghaptics;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.os.VibratorManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    TextView timer;
    ImageView tile11,tile12,tile13,tile14,tile21,tile22,tile23,tile24,tile31,tile32,tile33,tile34,
            tile41,tile42,tile43,tile44;
    Integer[] cardsArray = {101, 102, 103, 104, 105, 106, 107, 108, 201, 202, 203, 204, 205, 206, 207,
            208};

    int image101, image102, image103, image104, image105, image106, image107, image108, image201,
            image202, image203, image204, image205, image206, image207, image208;

    int firstCard, secondCard;
    int clickedFirst, clickedSecond;
    int cardNumber = 1;
    int mistakes = 0;
    int bestTimeMistakes = 0;
    String bestTime = "";

    private boolean timerStarted = false;
    private final Handler timerHandler = new Handler(Looper.getMainLooper());
    private Runnable timerRunnable;
    private int secondsPassed = 0;
    private Vibrator vibrator;
    private final long[] wrongPattern = {0, 50, 50, 100};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent =getIntent();
        if (intent != null) {
            if (intent.getStringExtra("best_time") != null) {
                bestTime = intent.getStringExtra("best_time");
            }
            bestTimeMistakes = intent.getIntExtra("best_time_mistakes", 0);
        }


        timer = findViewById(R.id.timer);
        tile11 = findViewById(R.id.tile11);
        tile12 = findViewById(R.id.tile12);
        tile13 = findViewById(R.id.tile13);
        tile14 = findViewById(R.id.tile14);
        tile21 = findViewById(R.id.tile21);
        tile22 = findViewById(R.id.tile22);
        tile23 = findViewById(R.id.tile23);
        tile24 = findViewById(R.id.tile24);
        tile31 = findViewById(R.id.tile31);
        tile32 = findViewById(R.id.tile32);
        tile33 = findViewById(R.id.tile33);
        tile34 = findViewById(R.id.tile34);
        tile41 = findViewById(R.id.tile41);
        tile42 = findViewById(R.id.tile42);
        tile43 = findViewById(R.id.tile43);
        tile44 = findViewById(R.id.tile44);

        tile11.setTag("0");
        tile12.setTag("1");
        tile13.setTag("2");
        tile14.setTag("3");
        tile21.setTag("4");
        tile22.setTag("5");
        tile23.setTag("6");
        tile24.setTag("7");
        tile31.setTag("8");
        tile32.setTag("9");
        tile33.setTag("10");
        tile34.setTag("11");
        tile41.setTag("12");
        tile42.setTag("13");
        tile43.setTag("14");
        tile44.setTag("15");

        VibratorManager vibratorManager = (VibratorManager) getSystemService(Context.VIBRATOR_MANAGER_SERVICE);
        vibrator = vibratorManager.getDefaultVibrator();

        //load images
        frontOfCardsResources();

        //shuffle images
        Collections.shuffle(Arrays.asList(cardsArray));

        tile11.setOnClickListener(view -> {
            int theCard = Integer.parseInt((String)view.getTag());
            setCardFace(tile11, theCard);
        });

        tile12.setOnClickListener(view -> {
            int theCard = Integer.parseInt((String)view.getTag());
            setCardFace(tile12, theCard);
        });

        tile13.setOnClickListener(view -> {
            int theCard = Integer.parseInt((String)view.getTag());
            setCardFace(tile13, theCard);
        });

        tile14.setOnClickListener(view -> {
            int theCard = Integer.parseInt((String)view.getTag());
            setCardFace(tile14, theCard);
        });

        tile21.setOnClickListener(view -> {
            int theCard = Integer.parseInt((String)view.getTag());
            setCardFace(tile21, theCard);
        });

        tile22.setOnClickListener(view -> {
            int theCard = Integer.parseInt((String)view.getTag());
            setCardFace(tile22, theCard);
        });

        tile23.setOnClickListener(view -> {
            int theCard = Integer.parseInt((String)view.getTag());
            setCardFace(tile23, theCard);
        });

        tile24.setOnClickListener(view -> {
            int theCard = Integer.parseInt((String)view.getTag());
            setCardFace(tile24, theCard);
        });

        tile31.setOnClickListener(view -> {
            int theCard = Integer.parseInt((String)view.getTag());
            setCardFace(tile31, theCard);
        });

        tile32.setOnClickListener(view -> {
            int theCard = Integer.parseInt((String)view.getTag());
            setCardFace(tile32, theCard);
        });

        tile33.setOnClickListener(view -> {
            int theCard = Integer.parseInt((String)view.getTag());
            setCardFace(tile33, theCard);
        });

        tile34.setOnClickListener(view -> {
            int theCard = Integer.parseInt((String)view.getTag());
            setCardFace(tile34, theCard);
        });

        tile41.setOnClickListener(view -> {
            int theCard = Integer.parseInt((String)view.getTag());
            setCardFace(tile41, theCard);
        });

        tile42.setOnClickListener(view -> {
            int theCard = Integer.parseInt((String)view.getTag());
            setCardFace(tile42, theCard);
        });

        tile43.setOnClickListener(view -> {
            int theCard = Integer.parseInt((String)view.getTag());
            setCardFace(tile43, theCard);
        });

        tile44.setOnClickListener(view -> {
            int theCard = Integer.parseInt((String)view.getTag());
            setCardFace(tile44, theCard);
        });

        timerRunnable = new Runnable() {
            @Override
            public void run() {
                secondsPassed++;
                int minutes = secondsPassed / 60;
                int seconds = secondsPassed % 60;
                timer.setText(String.format("Time: %02d:%02d", minutes, seconds));
                timerHandler.postDelayed(this, 1000);
            }
        };
    }

    private void setCardFace(ImageView tile, int card){
        if(cardsArray[card] == 101){
            tile.setImageResource(image101);
        } else if(cardsArray[card] == 102){
            tile.setImageResource(image102);
        } else if(cardsArray[card] == 103){
            tile.setImageResource(image103);
        } else if(cardsArray[card] == 104){
            tile.setImageResource(image104);
        } else if(cardsArray[card] == 105){
            tile.setImageResource(image105);
        } else if(cardsArray[card] == 106){
            tile.setImageResource(image106);
        } else if(cardsArray[card] == 107){
            tile.setImageResource(image107);
        } else if(cardsArray[card] == 108){
            tile.setImageResource(image108);
        } else if(cardsArray[card] == 201){
            tile.setImageResource(image201);
        } else if(cardsArray[card] == 202){
            tile.setImageResource(image202);
        } else if(cardsArray[card] == 203){
            tile.setImageResource(image203);
        } else if(cardsArray[card] == 204){
            tile.setImageResource(image204);
        } else if(cardsArray[card] == 205){
            tile.setImageResource(image205);
        } else if(cardsArray[card] == 206){
            tile.setImageResource(image206);
        } else if(cardsArray[card] == 207){
            tile.setImageResource(image207);
        } else if(cardsArray[card] == 208){
            tile.setImageResource(image208);
        }

        //check card image and save it to a temp variable
        if(cardNumber == 1){
            firstCard = cardsArray[card];
            if(firstCard > 200){
                firstCard = firstCard - 100;
            }
            cardNumber = 2;
            clickedFirst = card;

            tile.setEnabled(false);
        } else if(cardNumber == 2){
            secondCard = cardsArray[card];
            if(secondCard > 200){
                secondCard = secondCard - 100;
            }
            cardNumber = 1;
            clickedSecond = card;

            tile11.setEnabled(false);
            tile12.setEnabled(false);
            tile13.setEnabled(false);
            tile14.setEnabled(false);
            tile21.setEnabled(false);
            tile22.setEnabled(false);
            tile23.setEnabled(false);
            tile24.setEnabled(false);
            tile31.setEnabled(false);
            tile32.setEnabled(false);
            tile33.setEnabled(false);
            tile34.setEnabled(false);
            tile41.setEnabled(false);
            tile42.setEnabled(false);
            tile43.setEnabled(false);
            tile44.setEnabled(false);

            Handler handler = new Handler(Looper.getMainLooper());
            //check if images are equal values
            handler.postDelayed(this::calculate, 350);
        }

        if (!timerStarted) {
            timerStarted = true;
            timerHandler.postDelayed(timerRunnable, 1000);
        }
    }

    private void calculate(){
        //if images are equal, remove them
        if(firstCard == secondCard){
            if(clickedFirst == 0){
                tile11.setVisibility(View.INVISIBLE);
            } else if(clickedFirst == 1){
                tile12.setVisibility(View.INVISIBLE);
            } else if(clickedFirst == 2){
                tile13.setVisibility(View.INVISIBLE);
            } else if(clickedFirst == 3){
                tile14.setVisibility(View.INVISIBLE);
            } else if(clickedFirst == 4){
                tile21.setVisibility(View.INVISIBLE);
            } else if(clickedFirst == 5){
                tile22.setVisibility(View.INVISIBLE);
            } else if(clickedFirst == 6){
                tile23.setVisibility(View.INVISIBLE);
            } else if(clickedFirst == 7){
                tile24.setVisibility(View.INVISIBLE);
            } else if(clickedFirst == 8){
                tile31.setVisibility(View.INVISIBLE);
            } else if(clickedFirst == 9){
                tile32.setVisibility(View.INVISIBLE);
            } else if(clickedFirst == 10){
                tile33.setVisibility(View.INVISIBLE);
            } else if(clickedFirst == 11){
                tile34.setVisibility(View.INVISIBLE);
            } else if(clickedFirst == 12){
                tile41.setVisibility(View.INVISIBLE);
            } else if(clickedFirst == 13){
                tile42.setVisibility(View.INVISIBLE);
            } else if(clickedFirst == 14){
                tile43.setVisibility(View.INVISIBLE);
            } else if(clickedFirst == 15){
                tile44.setVisibility(View.INVISIBLE);
            }

            if(clickedSecond == 0){
                tile11.setVisibility(View.INVISIBLE);
            } else if(clickedSecond == 1){
                tile12.setVisibility(View.INVISIBLE);
            } else if(clickedSecond == 2){
                tile13.setVisibility(View.INVISIBLE);
            } else if(clickedSecond == 3){
                tile14.setVisibility(View.INVISIBLE);
            } else if(clickedSecond == 4){
                tile21.setVisibility(View.INVISIBLE);
            } else if(clickedSecond == 5){
                tile22.setVisibility(View.INVISIBLE);
            } else if(clickedSecond == 6){
                tile23.setVisibility(View.INVISIBLE);
            } else if(clickedSecond == 7){
                tile24.setVisibility(View.INVISIBLE);
            } else if(clickedSecond == 8){
                tile31.setVisibility(View.INVISIBLE);
            } else if(clickedSecond == 9){
                tile32.setVisibility(View.INVISIBLE);
            } else if(clickedSecond == 10){
                tile33.setVisibility(View.INVISIBLE);
            } else if(clickedSecond == 11){
                tile34.setVisibility(View.INVISIBLE);
            } else if(clickedSecond == 12){
                tile41.setVisibility(View.INVISIBLE);
            } else if(clickedSecond == 13){
                tile42.setVisibility(View.INVISIBLE);
            } else if(clickedSecond == 14){
                tile43.setVisibility(View.INVISIBLE);
            } else if(clickedSecond == 15){
                tile44.setVisibility(View.INVISIBLE);
            }
        } else {
            tile11.setImageResource(R.drawable.imageback);
            tile12.setImageResource(R.drawable.imageback);
            tile13.setImageResource(R.drawable.imageback);
            tile14.setImageResource(R.drawable.imageback);
            tile21.setImageResource(R.drawable.imageback);
            tile22.setImageResource(R.drawable.imageback);
            tile23.setImageResource(R.drawable.imageback);
            tile24.setImageResource(R.drawable.imageback);
            tile31.setImageResource(R.drawable.imageback);
            tile32.setImageResource(R.drawable.imageback);
            tile33.setImageResource(R.drawable.imageback);
            tile34.setImageResource(R.drawable.imageback);
            tile41.setImageResource(R.drawable.imageback);
            tile42.setImageResource(R.drawable.imageback);
            tile43.setImageResource(R.drawable.imageback);
            tile44.setImageResource(R.drawable.imageback);

            vibratePattern(wrongPattern);
            mistakes++;
        }

        tile11.setEnabled(true);
        tile12.setEnabled(true);
        tile13.setEnabled(true);
        tile14.setEnabled(true);
        tile21.setEnabled(true);
        tile22.setEnabled(true);
        tile23.setEnabled(true);
        tile24.setEnabled(true);
        tile31.setEnabled(true);
        tile32.setEnabled(true);
        tile33.setEnabled(true);
        tile34.setEnabled(true);
        tile41.setEnabled(true);
        tile42.setEnabled(true);
        tile43.setEnabled(true);
        tile44.setEnabled(true);

        //check if game is over
        checkEnd();
    }

    private void checkEnd(){
        if(tile11.getVisibility() == View.INVISIBLE &&
                tile12.getVisibility() == View.INVISIBLE &&
                tile13.getVisibility() == View.INVISIBLE &&
                tile14.getVisibility() == View.INVISIBLE &&
                tile21.getVisibility() == View.INVISIBLE &&
                tile22.getVisibility() == View.INVISIBLE &&
                tile23.getVisibility() == View.INVISIBLE &&
                tile24.getVisibility() == View.INVISIBLE &&
                tile31.getVisibility() == View.INVISIBLE &&
                tile32.getVisibility() == View.INVISIBLE &&
                tile33.getVisibility() == View.INVISIBLE &&
                tile34.getVisibility() == View.INVISIBLE &&
                tile41.getVisibility() == View.INVISIBLE &&
                tile42.getVisibility() == View.INVISIBLE &&
                tile43.getVisibility() == View.INVISIBLE &&
                tile44.getVisibility() == View.INVISIBLE){

            System.out.println(mistakes);
            System.out.println(timer.getText());
            if (bestTime == ""){
                bestTime = timer.getText().toString();
            }
            if (bestTimeMistakes == 0){
                bestTimeMistakes = mistakes;
            }
            System.out.println(bestTime);
            String time1Value = bestTime.substring(6);
            String time2Value = timer.getText().toString().substring(6);
            int bestTimeValue = Integer.parseInt(time1Value.replace(":", ""));
            int currentTimeValue = Integer.parseInt(time2Value.replace(":", ""));
            if (bestTimeValue > currentTimeValue) {
                bestTime = timer.getText().toString();
                bestTimeMistakes = mistakes;
                Toast.makeText(getApplicationContext(), "New Record", Toast.LENGTH_LONG).show();
            } else {
                System.out.println(bestTime + " and " + timer.getText().toString() + " are equal");
            }
            timerHandler.removeCallbacks(timerRunnable);
            showPopup(MainActivity.this, bestTime, bestTimeMistakes);
        }
    }


    private void vibratePattern(long[] pattern){
        VibrationEffect effect = VibrationEffect.createWaveform(pattern, -1);
        vibrator.vibrate(effect);
    }

    private void frontOfCardsResources(){
        image101 = R.drawable.image101;
        image102 = R.drawable.image102;
        image103 = R.drawable.image103;
        image104 = R.drawable.image104;
        image105 = R.drawable.image105;
        image106 = R.drawable.image106;
        image107 = R.drawable.image107;
        image108 = R.drawable.image108;
        image201 = R.drawable.image201;
        image202 = R.drawable.image202;
        image203 = R.drawable.image203;
        image204 = R.drawable.image204;
        image205 = R.drawable.image205;
        image206 = R.drawable.image206;
        image207 = R.drawable.image207;
        image208 = R.drawable.image208;
    }
    private void showPopup(Context context, String bestTime, int bestTimeMistakes) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View popupView = inflater.inflate(R.layout.popup_layout, null);

        TextView bestTimeTextView = popupView.findViewById(R.id.best_time_text_view);
        TextView currentGameTimeTextView = popupView.findViewById(R.id.current_game_time_text_view);
        TextView mistakesTextView = popupView.findViewById(R.id.mistakes_text_view);
        TextView currentGameMistakesTextView = popupView.findViewById(R.id.current_game_mistakes_text_view);

        bestTimeTextView.setText("Best " + this.bestTime); // Set the best time
        currentGameTimeTextView.setText("Current Game " + timer.getText().toString());
        mistakesTextView.setText("Best Time Mistakes: " + this.bestTimeMistakes);
        currentGameMistakesTextView.setText("Current Game Mistakes: " + mistakes);// Set the number of mistakes

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        alertDialogBuilder.setView(popupView);

        alertDialogBuilder.setPositiveButton("New", (dialog, i) -> {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            intent.putExtra("best_time", bestTime);
            intent.putExtra("best_time_mistakes", bestTimeMistakes);
            startActivity(intent);
            finish();
        });

        alertDialogBuilder.setNegativeButton("Exit", (dialog, i) -> {

        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
}