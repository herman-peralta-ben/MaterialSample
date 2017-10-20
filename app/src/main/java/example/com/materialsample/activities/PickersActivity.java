package example.com.materialsample.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.DatePicker;
import android.widget.TimePicker;

import example.com.materialsample.R;

// todo OY dialog picker
public class PickersActivity extends AppCompatActivity {

    private DatePicker datePicker;
    private TimePicker timePicker;

    public static Intent getStartIntent(@NonNull Context from) {
        Intent intent = new Intent(from, PickersActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pickers);

        datePicker = (DatePicker) findViewById(R.id.datePicker);
        timePicker = (TimePicker) findViewById(R.id.timePicker);
    }

}
