package com.nfsindustries.randomgenerator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

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
        generatedNumbersTextView = (TextView)findViewById(R.id.generatedNumbersTextView);
        generatedStringTextView = (TextView)findViewById(R.id.generatedStringTextView);
        locale = this.getResources().getConfiguration().locale.getCountry();
        generator = new LuckyRandomGenerator(locale);

        Set<Integer> tickets = generator.generateLotteryTickets(6);
        generatedNumbersTextView.setText(tickets.toString());

        generatedStringTextView.setText(generator.generateRandomString(8));
    }
}
