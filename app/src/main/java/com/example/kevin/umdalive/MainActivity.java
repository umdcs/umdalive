package com.example.kevin.umdalive;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickNewClub(View view) {
        Intent intent = new Intent(this, Club.class);
        startActivity(intent);
        // display toast in long period of time
        Toast.makeText(getApplicationContext(),"Make a New Club!", Toast.LENGTH_LONG).show();
    }
}
