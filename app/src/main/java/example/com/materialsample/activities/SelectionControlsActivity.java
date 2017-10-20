package example.com.materialsample.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SwitchCompat;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import example.com.materialsample.R;

public class SelectionControlsActivity extends AppCompatActivity {

    private CheckBox cb1, cb2;
    private CompoundButton.OnCheckedChangeListener checkButtonsGroupListener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {
            int id = 0;
            switch (compoundButton.getId()) {
                case R.id.cb1:
                    id = 1;
                    break;

                case R.id.cb2:
                    id = 2;
                    break;
            }

            showToast("Checkbox" + id + " " + checked);
            showToast("C1 " + cb1.isChecked() + "; C2 " + cb2.isChecked());
        }
    };

    public static Intent getStartIntent(@NonNull Context from) {
        Intent intent = new Intent(from, SelectionControlsActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection_controls);

        cb1 = ((CheckBox) findViewById(R.id.cb1));
        cb1.setOnCheckedChangeListener(checkButtonsGroupListener);

        cb2 = ((CheckBox) findViewById(R.id.cb2));
        cb2.setOnCheckedChangeListener(checkButtonsGroupListener);

        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int vid) {
                int r = 0;
                switch (radioGroup.getCheckedRadioButtonId()) {
                    case R.id.rb1:
                        r = 1;
                        break;
                    case R.id.rb2:
                        r = 2;
                        break;
                    case R.id.rb3:
                        r = 3;
                        break;
                }
                showToast("Radio selected " + r);
            }
        });

        SwitchCompat compatSwitch = (SwitchCompat) findViewById(R.id.compatSwitch);
        compatSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean selected) {
                showToast("Switch selected?" + selected);
            }
        });
    }

    private void showToast(String msg) {
        Toast.makeText(SelectionControlsActivity.this, msg, Toast.LENGTH_SHORT).show();
    }
}
