package com.nfsindustries.randomgenerator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Set;

public class NumberGeneratorActivity extends AppCompatActivity {

    TextView generatedNumbersTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number_generator);
        generatedNumbersTextView = (TextView)findViewById(R.id.generatedNumbersTextView);

        LuckyRandomGenerator generator = new LuckyRandomGenerator();
        Set<Integer> tickets = generator.generateLotteryTickets(6);
        generatedNumbersTextView.setText(tickets.toString());
    }
}
