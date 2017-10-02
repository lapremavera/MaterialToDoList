package com.lapremavera.materialtodolist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ToDoDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do_details);

        String text = getIntent().getStringExtra("Name");
        TextView title = (TextView) findViewById(R.id.detailsTitleText);
        title.setText(text);
    }

    public void onBackPressed() {
        super.onBackPressed();
        finishAfterTransition();
    }

}
