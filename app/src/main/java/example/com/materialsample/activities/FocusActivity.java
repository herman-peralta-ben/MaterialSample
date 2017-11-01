package example.com.materialsample.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.OnFocusChange;
import example.com.materialsample.R;

public class FocusActivity extends AppCompatActivity {

    public static Intent getStartIntent(@NonNull Context from) {
        Intent intent = new Intent(from, FocusActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_focus);

        ButterKnife.bind(this);
    }

    @OnFocusChange(R.id.input1_text)
    public void onInput1Focus(boolean hasFocus) {
        log("input 1 focus " + hasFocus);
    }

    @OnFocusChange(R.id.input2_text)
    public void onInput2Focus(boolean hasFocus) {
        log("input 2 focus " + hasFocus);
    }

    @OnFocusChange(R.id.input3_text)
    public void onInput3Focus(boolean hasFocus) {
        log("input 3 focus " + hasFocus);
    }

    @OnFocusChange(R.id.input4_text)
    public void onInput4Focus(boolean hasFocus) {
        log("input 4 focus " + hasFocus);
    }

    @OnFocusChange(R.id.radioGroup) // bug in android when group is horizontal?
    public void onRadioGroupChanged(boolean hasFocus) {
        log("group focus " + hasFocus);
    }

    @OnFocusChange(R.id.rb1)
    public void onRadioFocus1(boolean hasFocus) {
        log("rb1 focus " + hasFocus);
    }

    @OnFocusChange(R.id.rb2)
    public void onRadioFocus2(boolean hasFocus) {
        log("rb2 focus " + hasFocus);
    }

    @OnFocusChange(R.id.rb3)
    public void onRadioFocus3(boolean hasFocus) {
        log("rb3 focus " + hasFocus);
    }

    private void log(String msg) {
        Toast.makeText(FocusActivity.this, msg, Toast.LENGTH_SHORT).show();
        Log.e("SAMPLE", msg);
    }
}
