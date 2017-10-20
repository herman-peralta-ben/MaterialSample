package example.com.materialsample.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import example.com.materialsample.R;
import example.com.materialsample.fragments.BottomSheetFragment;

/**
 * https://code.tutsplus.com/articles/how-to-use-bottom-sheets-with-the-design-support-library--cms-26031
 * see project/docs/bottomsheets.pdf
 */
public class BottomSheetActivity extends AppCompatActivity
        implements View.OnClickListener {

    private BottomSheetBehavior mBottomSheetBehavior;

    public static Intent getStartIntent(@NonNull Context from) {
        Intent intent = new Intent(from, BottomSheetActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_sheet);

        View bottomSheet = findViewById(R.id.bottom_sheet);
        findViewById(R.id.button_1).setOnClickListener(this);
        findViewById(R.id.button_2).setOnClickListener(this);
        findViewById(R.id.button_3).setOnClickListener(this);
        findViewById(R.id.button_4).setOnClickListener(this);

        mBottomSheetBehavior = BottomSheetBehavior.from(bottomSheet);
        mBottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(View bottomSheet, int newState) {
                if (newState == BottomSheetBehavior.STATE_COLLAPSED) {
                    mBottomSheetBehavior.setPeekHeight(0);
                }
            }

            @Override
            public void onSlide(View bottomSheet, float slideOffset) {
            }
        });
    }

    @Override
    public void onClick(View v) {
        Log.i("Activity", "id " + v.getId());

        switch (v.getId()) {
            case R.id.button_1: {
                mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                Toast.makeText(BottomSheetActivity.this, "Lo maximo a expander depende el height del view/fragment a mostrar", Toast.LENGTH_LONG).show();
                break;
            }

            case R.id.button_2: {
                mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                break;
            }

            case R.id.button_3: {
                mBottomSheetBehavior.setPeekHeight(300);
                mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                break;
            }

            case R.id.button_4: {
                Log.i("Activity", "boton4");
                BottomSheetDialogFragment bottomSheetDialogFragment = new BottomSheetFragment();
                bottomSheetDialogFragment.show(getSupportFragmentManager(), bottomSheetDialogFragment.getTag());
                break;
            }
        }
    }
}
