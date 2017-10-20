package example.com.materialsample.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import example.com.materialsample.R;

public class BottomNavigationActivity extends AppCompatActivity {

    public static Intent getStartIntent(@NonNull Context from) {
        Intent intent = new Intent(from, BottomNavigationActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_navigation);
    }
}
