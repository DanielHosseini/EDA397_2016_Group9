package com.group9.eda397.ui.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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

        View mainView = (View)findViewById(R.id.layout_card_presenter);
        mainView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
