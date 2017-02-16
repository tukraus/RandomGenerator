package com.nfsindustries.randomgenerator;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.Set;

public class NumberGeneratorActivity extends AppCompatActivity {

    TextView generatedNumbersTextView;
    TextView generatedStringTextView;
    String locale;
    LuckyRandomGenerator generator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number_generator);
        setUpViews();
        animateViews();
        locale = this.getResources().getConfiguration().locale.getCountry();
        generator = new LuckyRandomGenerator(locale);
        generateRandom();
    }

    private void generateRandom() {
        Set<Integer> tickets = generator.generateLotteryTickets(6);
        generatedNumbersTextView.setText(tickets.toString());
        generatedStringTextView.setText(generator.generateRandomString(8));
    }

    private void setUpViews() {
        generatedNumbersTextView = (TextView) findViewById(R.id.generatedNumbersTextView);
        generatedStringTextView = (TextView) findViewById(R.id.generatedStringTextView);
    }

    private void animateViews() {
        Animation animation =
                AnimationUtils.loadAnimation(getApplicationContext(),
                        R.anim.blink);

        generatedNumbersTextView.startAnimation(animation);
        generatedStringTextView.startAnimation(animation);
    }
}
