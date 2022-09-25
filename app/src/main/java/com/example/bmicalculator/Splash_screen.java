package com.example.bmicalculator;

import static com.example.bmicalculator.R.drawable.back_img;
import static com.example.bmicalculator.R.drawable.white_back;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.SparseLongArray;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;

import com.airbnb.lottie.LottieAnimationView;

public class Splash_screen extends AppCompatActivity {

    ImageButton next1, next2, next3, next4 , refreshImageButton;
    ViewFlipper viewFlipper;
    ImageView imgBoy, imgGirl, menHeight, menWight, finalImage ;
    LottieAnimationView scanning;
    SeekBar seekBarHeight, seekBarWeight;
    TextView heightTxt, wieghtTxt, calculatingTxt;
    float currentProgress, currentProgress2, heightCmM, floatBmi;
    String mintprogress = "11";
    String mintprogress2 = "5";
    String typeOfUser = "0";
    String mbmi;
    Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        getSupportActionBar().hide();

        viewFlipper = findViewById(R.id.viewFlipper);
        next1 = findViewById(R.id.next1);
        next2 = findViewById(R.id.next2);
        next3 = findViewById(R.id.next3);
        next4 = findViewById(R.id.next4);
        refreshImageButton = findViewById(R.id.refreshImageButton);
        imgBoy = findViewById(R.id.imgBoy);
        imgGirl = findViewById(R.id.imgGirl);
        menWight = findViewById(R.id.menWight);
        menHeight = findViewById(R.id.menHeight);
        seekBarHeight = findViewById(R.id.seekBarHeight);
        seekBarWeight = findViewById(R.id.seekBarWeight);
        finalImage = findViewById(R.id.finalImage);
        scanning = findViewById(R.id.scanning);

        calculatingTxt = findViewById(R.id.calculatingTxt);
        heightTxt = findViewById(R.id.heightTxt);
        wieghtTxt = findViewById(R.id.wieghtTxt);

        dialog = new Dialog(this);


        imgBoy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imgBoy.setBackground(ContextCompat.getDrawable(getApplicationContext(), back_img));
                imgGirl.setBackground(ContextCompat.getDrawable(getApplicationContext(), white_back));
                typeOfUser = "Male";
                menHeight.setImageResource(R.drawable.young_man);
                menWight.setImageResource(R.drawable.young_man);
                finalImage.setImageResource(R.drawable.young_man);
            }
        });

        imgGirl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imgGirl.setBackground(ContextCompat.getDrawable(getApplicationContext(), back_img));
                imgBoy.setBackground(ContextCompat.getDrawable(getApplicationContext(), white_back));
                typeOfUser = "Female";
                menHeight.setImageResource(R.drawable.young_women);
                menWight.setImageResource(R.drawable.young_women);
                finalImage.setImageResource(R.drawable.young_women);
            }
        });

        seekBarHeight.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                float scale = ((i / 200.0f) + 1);
                float scalex = ((i / 200.0f) + 1);
                menHeight.setScaleY(scale);
                menHeight.setScaleX(scalex);

                seekBarHeight.setMax(200);

                currentProgress = i;
                mintprogress = String.valueOf(currentProgress);
                heightTxt.setText("Height: " + mintprogress + " cm");

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        seekBarWeight.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar1, int j, boolean bb) {
                float scale = ((j / 300.0f) + 1);
                menWight.setScaleX(scale);
                finalImage.setScaleX(scale);

                seekBarWeight.setMax(140);

                currentProgress2 = j;

                mintprogress2 = String.valueOf(currentProgress2);
                wieghtTxt.setText("Wight: " + mintprogress2 + " kg");


            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        next1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                viewFlipper.showNext();

            }
        });


        next2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (typeOfUser.equals("0")) {
                    Toast.makeText(Splash_screen.this, "select your gender first.", Toast.LENGTH_SHORT).show();
                } else {
                    viewFlipper.showNext();
                }

            }
        });

        next3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (mintprogress.equals("11")) {
                    Toast.makeText(Splash_screen.this, "select your height first", Toast.LENGTH_SHORT).show();
                } else if (currentProgress < 30.0) {
                    Toast.makeText(Splash_screen.this, "Height is incorrect.", Toast.LENGTH_SHORT).show();
                } else {
                    viewFlipper.showNext();
                }
            }
        });

        next4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (mintprogress2.equals("5")) {
                    Toast.makeText(Splash_screen.this, "select your weight first", Toast.LENGTH_SHORT).show();
                } else if (currentProgress2 <= 2.0) {
                    Toast.makeText(Splash_screen.this, "Height is incorrect.", Toast.LENGTH_SHORT).show();
                } else {
                    viewFlipper.showNext();


//                    calculation
                    heightCmM = currentProgress / 100;
                    floatBmi = currentProgress2 / (heightCmM * heightCmM);
                    mbmi = String.valueOf(floatBmi);

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            condition();
                            calculatingTxt.setVisibility(View.INVISIBLE);
                            refreshImageButton.setVisibility(View.VISIBLE);
                            scanning.setVisibility(View.INVISIBLE);
                        }
                    }, 5000);

                }
            }
        });

        refreshImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Splash_screen.this , RealSplash.class);
                startActivity(intent);
                finish();
            }
        });

    }

    private void normalDialog() {
        dialog.setContentView(R.layout.normal_dialogbox);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();

        TextView normalDailogTxt = dialog.findViewById(R.id.normalDailogTxt);
        normalDailogTxt.setText(mbmi);

    }

    private void underDialog() {
        dialog.setContentView(R.layout.underwieght_dialogbox);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();

        TextView underDailogTxt = dialog.findViewById(R.id.underDailogTxt);
        underDailogTxt.setText(mbmi);

    }

    private void overDialog() {
        dialog.setContentView(R.layout.overweight_dialogbox);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();

        TextView overDailogTxt = dialog.findViewById(R.id.overDailogTxt);
        overDailogTxt.setText(mbmi);

    }

    private void condition() {
        if (floatBmi <= 18.4) {
            underDialog();
        } else if (floatBmi > 18.4 && floatBmi <= 24.9) {
            normalDialog();
        } else if (floatBmi > 24.9) {
            overDialog();
        }
    }

}


