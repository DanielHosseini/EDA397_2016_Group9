package com.group9.eda397.ui.activities;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.group9.eda397.R;

/**
 * Created by Daniel on 2016-04-21.
 */
public class CardPresenter extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.card_presenter);

        Intent myIntent = getIntent();
        String numberToShow = myIntent.getStringExtra("numberToShow");

        if (numberToShow != null) {
            TextView cardText = (TextView)findViewById(R.id.text_view_selectedCard);
            cardText.setText(numberToShow);
        }
    }
}
